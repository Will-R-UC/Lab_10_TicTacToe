import java.util.Scanner;

public class SafeInput {

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();

            if (retString.length() == 0) {
                System.out.println("You must enter something.");
            }
        } while(retString.length() == 0);

        return retString;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return an integer
     */
    public static int getInt(Scanner pipe, String prompt) {
        int retInt = 0;
        String userInput = "";
        boolean validInput = false;

        do {
            System.out.print("\n" + prompt + ": ");

            userInput = pipe.nextLine();

            try {
                retInt = Integer.parseInt(userInput);
                validInput = true;
            } catch (NumberFormatException e) {
                if(userInput.length() > 0) {
                    System.out.println("Invalid integer: " + userInput);
                } else {
                    System.out.println("You must enter something.");
                }
            }

        } while (!validInput);

        return retInt;
    }


    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a double
     */
    public static double getDouble(Scanner pipe, String prompt) {
        double retDouble = 0.0;
        String userInput = "";
        boolean validInput = false;

        do {
            System.out.print("\n" + prompt + ": ");

            userInput = pipe.nextLine();

            try {
                retDouble = Double.parseDouble(userInput);
                validInput = true;
            } catch (NumberFormatException e) {
                if(userInput.length() > 0) {
                    System.out.println("Invalid double: " + userInput);
                } else {
                    System.out.println("You must enter something.");
                }
            }

        } while (!validInput);

        return retDouble;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low low value, inclusive
     * @param high high value, inclusive
     * @return an integer
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retInt = 0;
        String userInput = "";
        boolean validInput = false;

        do {
            System.out.print("\n" + prompt + " [" + low + " – " + high + "]: ");

            userInput = pipe.nextLine();

            try {
                retInt = Integer.parseInt(userInput);
                if (low <= retInt && retInt <= high) {
                    validInput = true;
                } else {
                    System.out.println("Integer not in range: " + userInput);
                }
            } catch (NumberFormatException e) {
                if(userInput.length() > 0) {
                    System.out.println("Invalid integer: " + userInput);
                } else {
                    System.out.println("You must enter something.");
                }
            }

        } while (!validInput);

        return retInt;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low low value, inclusive
     * @param high high value, inclusive
     * @return a double
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retDouble = 0.0;
        String userInput = "";
        boolean validInput = false;

        do {
            System.out.print("\n" + prompt + " [" + low + " – " + high + "]: ");

            userInput = pipe.nextLine();

            try {
                retDouble = Double.parseDouble(userInput);
                if (low <= retDouble && retDouble <= high) {
                    validInput = true;
                } else {
                    System.out.println("Double not in range: " + userInput);
                }
            } catch (NumberFormatException e) {
                if(userInput.length() > 0) {
                    System.out.println("Invalid double: " + userInput);
                } else {
                    System.out.println("You must enter something.");
                }
            }
        } while (!validInput);

        return retDouble;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a boolean
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean retBool = false;
        boolean validInput = false;
        String userInput = "";

        do {
            System.out.print("\n" + prompt + " [Y/N]: ");

            userInput = pipe.nextLine();

            if (userInput.equalsIgnoreCase("Y")) {
                retBool = true;
                validInput = true;
            } else if (userInput.equalsIgnoreCase("N")) {
                retBool = false;
                validInput = true;
            } else {
                if (userInput.length() > 0) {
                    System.out.println("Invalid value: " + userInput);
                } else {
                    System.out.println("You must enter something.");
                }
            }
        } while (!validInput);

        return retBool;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regEx regex for input to against
     * @return a String that matches the regex
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String retString = "";
        String userInput = "";
        boolean validInput = false;

        do {
            System.out.print("\n" + prompt + ": ");

            userInput = pipe.nextLine();

            if (userInput.matches(regEx)) {
                retString = userInput;
                validInput = true;
            } else {
                System.out.println("Value did not match regex: " + userInput);
            }
        } while (!validInput);

        return retString;
    }

    /**
     *
     * @param message message to print to console, must be 54 characters or fewer
     */
    public static void prettyHeader(String message) {
        int messageLength = message.length();
        int leftPadding = ((60 - messageLength) / 2) - 3;
        int rightPadding = (int) Math.ceil((60 - messageLength) / 2.0) - 3;

        //Throw an error if message length breaks the specified formatting
        //Maybe extend functionality to handle multiple lines in the future
        if (messageLength > 54) {
            throw new RuntimeException("prettyHeader message must be 54 characters or fewer");
        }

        for (int i = 0; i < 60; i++) {
            System.out.print("*");
        }
        System.out.print("\n");

        System.out.printf("***%" + leftPadding + "s%s%" + rightPadding + "s***", "", message, "");
        System.out.print("\n");

        for (int i = 0; i < 60; i++) {
            System.out.print("*");
        }
        System.out.print("\n");
    }
}


