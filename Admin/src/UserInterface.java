import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    public static void homePage() throws SQLException {

        System.out.println("Choose an Option by Number: \n" +
                "1. Admit Students\n" +
                "2. Generate Reports\n" +
                "3. Exit\n");

        Scanner sc= new Scanner(System.in);
        int x = sc.nextInt();


        switch (x)
        {
            case 1:
                StudentAdmission.fetchApplications("pending");
                ArrayList<Application> pendingApplications = StudentAdmission.getPendingApplications();

                System.out.println("Admission requests: ");
                printApplications(pendingApplications);

                System.out.println("Choose a student to admit by number: ");
                int i = sc.nextInt();
                StudentAdmission.admitStudent(i-1);
                System.out.println("Student admitted successfully");
                homePage();
                break;
            case 2:
                reportOptions();
                homePage();
                break;
            case 3:
                System.out.println("System exited");
                break;
            default:
                System.out.println("Enter a valid choice");
                homePage();
                break;
        }

    }

    public static void reportOptions() throws SQLException {
        System.out.println("Choose an Option by Number: \n" +
                "1. pending admission requests report\n" +
                "2. accepted admissions report\n" +
                "3. Exit");

        Scanner sc= new Scanner(System.in);
        int x = sc.nextInt();

       switch (x)
        {
            case 1:
                StudentAdmission.getPendingApplications().clear();
                StudentAdmission.fetchApplications("pending");
                ArrayList<Application> pendingApplications = StudentAdmission.getPendingApplications();
                System.out.println("Admission requests");
                printApplications(pendingApplications);
                reportOptions();
                break;
            case 2:
                StudentAdmission.getAcceptedApplications().clear();
                StudentAdmission.fetchApplications("accepted");
                ArrayList<Application> acceptedApplications = StudentAdmission.getAcceptedApplications();
                System.out.println("Accepted applications");
                printApplications(acceptedApplications);
                reportOptions();
                break;

            case 3:
                System.out.println("System Exited");
                break;
            default:
                System.out.println("Enter a valid choice");
                reportOptions();
                break;
        }

    }

    public static void printApplications(ArrayList<Application> applications) throws SQLException {
        System.out.println("_______________________________________________________________________________________________________________");
        for (int j = 0; j < applications.size(); j++) {
            System.out.println(j + 1 + ". " + "First name: " +
                    applications.get(j).getFirst_name() +
                    " | Last name: " +
                    applications.get(j).getLast_name() +
                    " | Highschool grade: " +
                    applications.get(j).getHighschool_grade() +
                    " | University name: " +
                    applications.get(j).getUniversity_name() +
                    " | Major_name: " +
                    applications.get(j).getMajor_name() +
                    " | Enrollment date: " +
                    applications.get(j).getEnrollment_date());
            System.out.println("_______________________________________________________________________________________________________________");
        }
    }
}

