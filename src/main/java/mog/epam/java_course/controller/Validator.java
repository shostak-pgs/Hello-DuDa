package mog.epam.java_course.controller;

public class Validator {

    /**
     * Validates the input parameters. If the first argument(type of method)
     * is not GET or POST returns false. If the second argument(id of the publication)
     * is less than zero returns false. If the third argument(type of client)
     * is not HTTP or URL returns false. With another input returns true.
     * @param args array contains arguments for validation
     * @return boolean value if arguments are valid
     */
    public static boolean validate(String[] args) {
        boolean correct = true;
        if(!(args[0].equals("GET") | args[0].equals("POST"))) {
            correct = false;
        }
        if(Integer.parseInt(args[1]) < 0) {
            correct = false;
        }
        if(!(args[2].equals("HTTP") | args[2].equals("URL") | args[2].equals(null))) {
            correct = false;
        }
        return correct;
    }
}
