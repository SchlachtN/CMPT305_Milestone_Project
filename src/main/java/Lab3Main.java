import java.io.IOException;
import java.util.Scanner;

public class Lab3Main {
    public static void main(String[] args) {
        Scanner fileChoice = new Scanner(System.in);
        System.out.print("CSV filename: ");
        String fileName = fileChoice.nextLine();
        String filePath = "src/main/resources/" + fileName;
        PropertyAssessments propertyAssessments = new PropertyAssessments();
        try {
            propertyAssessments = new PropertyAssessments(filePath);
        } catch (IOException e) {
            System.err.println("Error: can't open file: ");
        }

        //Testing
        int n = propertyAssessments.getSize();
        System.out.println("Number of property assessments: " + n);
    }
}
