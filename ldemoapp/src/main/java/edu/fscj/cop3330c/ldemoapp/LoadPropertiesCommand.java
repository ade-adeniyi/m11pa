package edu.fscj.cop3330c.ldemoapp;

// Command to load properties using try-with-resources
class LoadPropertiesCommand implements Command {
    private String filepath;

    public LoadPropertiesCommand(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void execute() {
        try (var input = getClass().getClassLoader().getResourceAsStream(filepath)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + filepath);
                return;
            }
            java.util.Properties props = new java.util.Properties();
            props.load(input);
            System.out.println("Properties loaded successfully!");

            props.load(input);
            System.out.println("Properties loaded successfully: " + props.size() + " keys.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
