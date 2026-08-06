package edu.fscj.cop3330c.ldemoapp;

import java.util.Locale;
import java.util.ResourceBundle;

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
}
