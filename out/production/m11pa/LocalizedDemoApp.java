// LocalizedDemoApp.java
// D. Singletary
// 11/3/24
// demonstrates try-with-resources, localization, and command pattern.

package edu.fscj.cop3330c.ldemoapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

// Command Interface
interface Command {
    void execute();
}

// Concrete Command to display localized messages
class DisplayMessagesCommand implements Command {
    private ResourceBundle messages;

    public DisplayMessagesCommand(Locale locale) {
        this.messages = ResourceBundle.getBundle("MessagesBundle", locale);
    }

    @Override
    public void execute() {
        System.out.println(messages.getString("greeting"));
        System.out.println(messages.getString("farewell"));
    }

    public ResourceBundle getMessages() {
        return messages;
    }
}

// Concrete Command to load properties from a file
class LoadPropertiesCommand implements Command {
    private ResourceBundle messages;

    public LoadPropertiesCommand(ResourceBundle messages) {
        this.messages = messages;
    }

    @Override
    public void execute() {
        try (FileInputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String appName = prop.getProperty("app.name");
            System.out.println(messages.getString("appName") + ": " + appName);
        } catch (IOException e) {
            System.err.println(messages.getString("error") + ": " + e.getMessage());
        }
    }


    // Invoker class
    public class LocalizedDemoApp {
        public static void main(String[] args) {
            // Set locale to system default or specify as needed
            //Locale locale = Locale.getDefault();
            Locale locale = new Locale("de", "DE");
            //Locale locale = new Locale("fr", "FR");
            // select de/DE here after adding the properties file

            // Create and execute the commands
            Command displayMessages = new DisplayMessagesCommand(locale);
            displayMessages.execute();

            // Load and use messages from the previous command
            ResourceBundle messages = ((DisplayMessagesCommand) displayMessages).getMessages();
            Command loadProperties = new LoadPropertiesCommand(messages);
            loadProperties.execute();
        }
    }
}
