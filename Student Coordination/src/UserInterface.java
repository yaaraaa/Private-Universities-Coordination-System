import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    static Student student = new Student();

    public static void homePage() throws SQLException {

        System.out.println("Choose an Option by Number: \n" +
                "1. Register\n" +
                "2. Login\n" +
                "3. Exit\n");

        Scanner sc= new Scanner(System.in);
        int x = sc.nextInt();

        String s;
        int i;

        switch (x)
        {
            case 1:
                System.out.println("National ID: ");
                i = sc.nextInt();
                boolean alreadyExists = student.accountExists(i);
                if (alreadyExists) {
                    System.out.println("Account already exists in system");
                    homePage();
                }
                student.setNational_id(i);
                sc.nextLine();

                System.out.println("First Name: ");
                s = sc.nextLine();
                student.setFirst_name(s);

                System.out.println("Middle Name: ");
                s = sc.nextLine();
                student.setMiddle_name(s);

                System.out.println("Last Name: ");
                s = sc.nextLine();
                student.setLast_name(s);

                System.out.println("Password: ");
                s = sc.nextLine();
                student.setPassword(s);

                System.out.println("Birthday: ");
                s = sc.nextLine();
                student.setBirthday(s);

                System.out.println("Highschool_grade: ");
                i = sc.nextInt();
                student.setHighschool_grade(i);
                sc.nextLine();

                System.out.println("Street: ");
                s = sc.nextLine();
                student.setStreet(s);

                System.out.println("Area: ");
                s = sc.nextLine();
                student.setArea(s);

                System.out.println("City: ");
                s = sc.nextLine();
                student.setCity(s);

                student.register();

                System.out.println("Account registered successfully!\n");
                studentOptions();
                break;
            case 2:
                System.out.println("National ID: ");
                i = sc.nextInt();
                sc.nextLine();

                System.out.println("Password: ");
                s = sc.nextLine();

                boolean validLogin = student.isValidlogin(i, s);
                if (!validLogin){
                    System.out.println("Invalid login information");
                    homePage();
                }
                student.getStudentInformation(i);
                System.out.println("Account logged in successfully!");
                studentOptions();
                break;
            case 3:
                System.out.println("System exited");
                break;
            default:
                System.out.println("Enter a valid choice");
                studentOptions();
                break;
        }

    }

    public static void studentOptions () throws SQLException {
        System.out.println("Choose an Option by Number: \n" +
                "1. Submit an application\n" +
                "2. Check application(s) status(es)\n" +
                "3. Cancel an application\n" +
                "4. Choose from accepted application\n" +
                "5. Exit");

        Scanner sc= new Scanner(System.in);
        int x = sc.nextInt();

        String s;
        int i;

        switch (x)
        {
            case 1:
                student.getAvailableMajors().clear();
                student.fetchAvailableMajors();
                printAvailableMajors();
                System.out.println("Choose major and university by number: ");
                i = sc.nextInt();
                student.enroll(i-1);
                System.out.println("Enrollment Application submitted successfully!");
                studentOptions();
                break;
            case 2:
                System.out.println("Applications statuses: ");
                student.getEnrollments().clear();
                student.fetchEnrollmentInformation();
                printEnrollmentInformation();
                studentOptions();
                break;
            case 3:
                System.out.println("Your Applications: ");
                student.getEnrollments().clear();
                student.fetchEnrollmentInformation();
                printEnrollmentInformation();
                System.out.println("Choose an application to cancel by number: ");
                i = sc.nextInt();
                student.cancelEnrollment(i-1);
                System.out.println("Application cancelled successfully!");
                studentOptions();
                break;
            case 4:
                if(student.getDepartment_id().equals(null))
                {
                    System.out.println("You have already chosen a major");
                    break;
                }

                System.out.println("Your Accepted Applications: ");
                student.getEnrollments().clear();
                student.fetchEnrollmentInformation();
                printAcceptedApplications();
                System.out.println("Choose a university/major to join: ");
                i = sc.nextInt();
                student.updateMajorSeats(String.valueOf(student.getEnrollments().get(i-1).getDepartment_id()), student.getEnrollments().get(i-1).getMajor_name());
                student.allocateStudentToDepartment(String.valueOf(student.getEnrollments().get(i-1).getDepartment_id()));
                System.out.println("Congratulations! You have been successfully enrolled to a university");
                break;
            case 5:
                System.out.println("System Exited");
                break;
            default:
                System.out.println("Enter a valid choice");
                studentOptions();
                break;
        }

    }
    public static void printAvailableMajors() throws SQLException {
        System.out.println("Available Majors and universities");
        System.out.println("___________________________________________________________________________________");
        for(int j = 0; j < student.getAvailableMajors().size(); j++) {
            System.out.println(j+1 + ". " + "Major: " +
                    student.getAvailableMajors().get(j).getMajor_name() +
                    " | University: " +
                    student.getAvailableMajors().get(j).getUniversity_name() +
                    " | Application fees: " +
                    student.getAvailableMajors().get(j).getApplication_fees());
            System.out.println("___________________________________________________________________________________");
        }
    }

    public static void printEnrollmentInformation() throws SQLException {
        System.out.println("___________________________________________________________________________________");
        for(int j = 0; j < student.getEnrollments().size(); j++) {
            System.out.println(j+1 + ". " + "Major: " +
                    student.getEnrollments().get(j).getMajor_name() +
                    " | University: " +
                    student.getEnrollments().get(j).getUniversity_name() +
                    " | Application Status: " +
                    student.getEnrollments().get(j).getEnrollment_status());
            System.out.println("___________________________________________________________________________________");
        }
    }

    public static void printAcceptedApplications() throws SQLException {

        System.out.println("___________________________________________________________________________________");
        for(int j = 0; j < student.getEnrollments().size(); j++) {
            if(student.getEnrollments().get(j).getEnrollment_status().equals("accepted")) {
                System.out.println(student.getEnrollments().indexOf(student.getEnrollments().get(j)) + 1 + ". " + "Major: " +
                        student.getEnrollments().get(j).getMajor_name() +
                        " | University: " +
                        student.getEnrollments().get(j).getUniversity_name());
                System.out.println("___________________________________________________________________________________");
            }
        }
    }

}
