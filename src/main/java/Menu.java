import java.util.Scanner;

public class Menu {
    private PropertyAssessments propertyAssessments;

    public Menu(PropertyAssessments givenPropertyAssessments) {
        this.propertyAssessments = givenPropertyAssessments;
    }

    public void runMenu() {
        boolean keepMenu = true;
        String menu = menuDisplay();
        Scanner userSelection = new Scanner(System.in);
        while (keepMenu) {
            System.out.print(menu);
            String choice = userSelection.nextLine();
            int choiceInt = checkValidInt(choice);
            if (choiceInt == -1) {
                System.out.println("Invalid input");
            }
            else if (choiceInt >= 1 && choiceInt <= 2) {
                handleChoice(choiceInt, propertyAssessments);
            }
            else if (choiceInt == 3) {
                keepMenu = false;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    private String menuDisplay() {
        return """
                \nPlease select an option:
                \t1) Neighbourhood Information
                \t2) Class information
                \t3) Exit
               
                Option:\s""";
    }

    /**
     * Check that user has inputted a valid integer for menu
     * @param choice - the string input given by the user
     * @return choiceInt - Integer conversion of user choice or -1 if unable to parse to int
     */
    private int checkValidInt(String choice) {
        try {
            int choiceInt = Integer.parseInt(choice);
            if (choiceInt == -1) {
                return 0;
            } else {
                return choiceInt;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Display data for the user depending on choice selection
     * @param choice - int menu choice by user
     * @param propertyAssessments - PropertyAssessments object containing PropertyAssessment objects
     */
    private void handleChoice(int choice, PropertyAssessments propertyAssessments) {
        switch (choice) {
            case 1:
                neighbourhoodInfo(propertyAssessments);
                break;
            case 2:
                assessmentClassInfo(propertyAssessments);
                break;
            default:
                break;
        }
    }

    private void neighbourhoodInfo(PropertyAssessments propertyAssessments) {

    }

    private void assessmentClassInfo(PropertyAssessments propertyAssessments) {

    }
}
