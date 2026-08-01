/**
 * Name: Adeniyi Adeyemi
 * Course: COP3330C
 * Assignment: Module 11 Program
 */
package edu.fscj.cop3330c.ldemoapp;

import java.util.Locale;
import java.util.ResourceBundle;

// Command Interface
interface Command{
    void execute();
}
// Concrete Command to display localized messages
class DisplayMessagesCommand implements Command{
    private ResourceBundle messages;

    public DisplayMessagesCommand(Locale locale){
        this.messages = ResourceBundle.getBundle("MessagesBundle", locale);
    }
    @Override
    public void execute(){
        System.out.println(messages.getString("greeting"));
        System.out.println(messages.getString("farewell"));

    }
}
// Command to load properties using try-with-resources
class LoadPropertiesCommand implements Command{
    private String filepath;

    public LoadPropertiesCommand(String filepath){
        this.filepath = filepath;
    }
    @Override
    public void execute(){
        try (var input = getClass().getClassLoader().getResourceAsStream(filepath)){
            if (input == null){
                System.out.println("Sorry, unable to find " + filepath);
                return;
            }
            java.util.Properties props  = new java.util.Properties();
            props.load(input);
            System.out.println("Properties loaded successfully!");

            props.load(input);
            System.out.println("Properties loaded successfully: " + props.size() + " keys.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

public class LocalizedDemoApp {
    public static void main(String[] args){
        Command display = new DisplayMessagesCommand(new Locale("de", "DE"));
        display.execute();
        Command loadProps = new LoadPropertiesCommand("MessagesBundle_de_DE.properties");
        loadProps.execute();
    }
}
