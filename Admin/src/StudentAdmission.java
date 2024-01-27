import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentAdmission {
    private static ArrayList<Application> pendingApplications = new ArrayList<Application>();
    private static ArrayList<Application> rejectedApplications = new ArrayList<Application>();
    private static ArrayList<Application> acceptedApplications = new ArrayList<Application>();

    public static ArrayList<Application> getAcceptedApplications() {
        return acceptedApplications;
    }

    public static ArrayList<Application> getPendingApplications() {
        return pendingApplications;
    }

    public static ArrayList<Application> getRejectedApplications() {
        return rejectedApplications;
    }

    public static void fetchApplications(String enrollment_status) throws SQLException {
        JDBC jdbc = JDBC.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        String st = "SELECT student.first_name,\n" +
                "student.last_name,\n" +
                "student.highschool_grade,\n" +
                "university.university_name,\n" +
                "department.department_name,\n" +
                "enrollment.*\n" +
                "FROM enrollment\n" +
                "INNER JOIN student, university, department\n" +
                "WHERE enrollment.national_id = student.national_id\n" +
                "AND enrollment.university_id = university.university_id\n" +
                "AND enrollment.department_id = department.department_id\n" +
                "AND enrollment.enrollment_status = ?;";
        try {
            statement = jdbc.getConnection().prepareStatement(st);
            statement.setString(1, enrollment_status);
            result = statement.executeQuery();

            if(enrollment_status == "pending") {
                while (result.next()) {
                    pendingApplications.add(new Application(
                            result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            Integer.parseInt(result.getString(6)),
                            Integer.parseInt(result.getString(7)),
                            Integer.parseInt(result.getString(8)),
                            result.getString(9),
                            result.getString(10),
                            result.getString(11)));
                }
            }
            else if (enrollment_status == "accepted") {
                while (result.next()) {
                    acceptedApplications.add(new Application(
                            result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            Integer.parseInt(result.getString(6)),
                            Integer.parseInt(result.getString(7)),
                            Integer.parseInt(result.getString(8)),
                            result.getString(9),
                            result.getString(10),
                            result.getString(11)));
                }
            }
            else if (enrollment_status == "rejected") {
                while (result.next()) {
                    rejectedApplications.add(new Application(
                            result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            Integer.parseInt(result.getString(6)),
                            Integer.parseInt(result.getString(7)),
                            Integer.parseInt(result.getString(8)),
                            result.getString(9),
                            result.getString(10),
                            result.getString(11)));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (result != null) {
                result.close();
            }
        }
    }

    public static void admitStudent(int applicationIndex) throws SQLException {
        JDBC jdbc = JDBC.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        String st = "UPDATE enrollment \n" +
                "SET enrollment_status = \"accepted\"\n" +
                "WHERE enrollment.national_id = ?\n" +
                "AND enrollment.university_id = ?\n" +
                "AND enrollment.department_id = ?\n" +
                "AND enrollment.major_name = ?;";

        try {
            statement = jdbc.getConnection().prepareStatement(st);
            statement.setString(1, String.valueOf(pendingApplications.get(applicationIndex).getNational_id()));
            statement.setString(2, String.valueOf(pendingApplications.get(applicationIndex).getUniversity_id()));
            statement.setString(3, String.valueOf(pendingApplications.get(applicationIndex).getDepartment_id()));
            statement.setString(4, pendingApplications.get(applicationIndex).getMajor_name());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
