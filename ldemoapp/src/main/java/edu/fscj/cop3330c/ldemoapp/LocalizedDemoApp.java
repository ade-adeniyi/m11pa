/**
 * Name: Adeniyi Adeyemi
 * Course: COP3330C
 * Assignment: Module 11 Program
 */
package edu.fscj.cop3330c.ldemoapp;

import java.util.Locale;

public class LocalizedDemoApp {
    public static void main(String[] args){
        Command display = new DisplayMessagesCommand(new Locale("de", "DE"));
        display.execute();
        Command loadProps = new LoadPropertiesCommand("MessagesBundle_de_DE.properties");
        loadProps.execute();
    }
}
