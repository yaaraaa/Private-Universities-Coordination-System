import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Student {
    private int national_id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String password;
    private String birthday;
    private int highschool_grade;
    private String street;
    private String area;
    private String city;
    private String department_id;

    private ArrayList<Major> availableMajors = new ArrayList<Major>();
    private ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();

    Student() {
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setHighschool_grade(int highschool_grade) {
        this.highschool_grade = highschool_grade;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setNational_id(int national_id) {
        this.national_id = national_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setEnrollments(ArrayList<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void setAvailableMajors(ArrayList<Major> availableMajors) {
        this.availableMajors = availableMajors;
    }

    public int getHighschool_grade() {
        return highschool_grade;
    }

    public int getNational_id() {
        return national_id;
    }

    public String getArea() {
        return area;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCity() {
        return city;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getStreet() {
        return street;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }

    public ArrayList<Major> getAvailableMajors() {
        return availableMajors;
    }

    public void register() throws SQLException {
        JDBC jdbc = JDBC.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        String st = "INSERT INTO student " +
                "(national_id, first_name, middle_name, last_name, password, birthday, highschool_grade, street, area, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = jdbc.getConnection().prepareStatement(st);
            statement.setString(1, String.valueOf(this.national_id));
            statement.setString(2, this.first_name);
            statement.setString(3, this.middle_name);
            statement.setString(4, this.last_name);
            statement.setString(5, this.password);
            statement.setString(6, this.birthday);
            statement.setString(7, String.valueOf(this.highschool_grade));
            statement.setString(8, this.street);
            statement.setString(9, this.area);
            statement.setString(10, this.city);
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

    public boolean accountExists(int national_id) throws SQLException {
        JDBC jdbc = JDBC.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        String st = "select national_id from student Where national_id = ?";
        try {
            statement = jdbc.getConnection().prepareStatement(st);
            statement.setString(1, String.valueOf(national_id));
            result = statement.executeQuery();
            if (result.next()) {
                if (String.valueOf(national_id).equals(result.getString(1)))
                    return true;
            } else
                return false;
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
        return false;
    }

    public boolean isValidlogin(int national_id, String password) throws SQLException {

        JDBC jdbc = JDBC.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        String st = "select password from student Where national_id = ?";
        try {
            statement = jdbc.getConnection().prepareStatement(st);
            statement.setString(1, String.valueOf(national_id));
            result = statement.executeQuery();
            if (result.next()) {
                // check if password in argument matches the one in databases
                if (password.equals(result.getString(1)))
                    return true;
            }
            // if password doesn't match or national id doesn't exist in database
            else
                return false;
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
        return false;
    }

    public void getStudentInformation(int national_id) throws SQLException {
        JDBC jdbc = JDBC.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        String st = "select * from student where national_id = ?";
        try {
            statement = jdbc.getConnection().prepareStatement(st);
            statement.setString(1, String.valueOf(national_id));
            result = statement.executeQuery();

            if (result.next()) {
                this.national_id = Integer.parseInt(result.getString(1));
                this.first_name = result.getString(2);
                this.middle_name = result.getString(3);
                this.last_name = result.getString(4);
                this.password = result.getString(5);
                this.birthday = result.getString(6);
                this.highschool_grade = Integer.parseInt(result.getString(7));
                this.street = result.getString(8);
                this.area = result.getString(9);
                this.city = result.getString(10);
                this.department_id = result.getString(11);
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

    public void fetchAvailableMajors() throws SQLException {
        JDBC jdbc = JDBC.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        String st = "SELECT major_name, university_name, application_fees, department.department_id, university.university_id\n" +
                "FROM major inner join department, university\n" +
                "WHERE department.department_id = major.department_id \n" +
                "AND department.acceptance_grade <= ? \n" +
                "AND university.university_id = department.university_id \n" +
                "AND major.available_seats > 0;";
        try {
            statement = jdbc.getConnection().prepareStatement(st);
            statement.setString(1, String.valueOf(this.highschool_grade));
            result = statement.executeQuery();
            while (result.next()) {
                this.availableMajors.add(new Major(
                        result.getString(1),
                        result.getString(2),
                        Integer.parseInt(result.getString(3)),
                        Integer.parseInt(result.getString(4)),
                        Integer.parseInt(result.getString(5))));
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

    public void enroll(int majorIndex) throws SQLException {
        JDBC jdbc = JDBC.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        String st = "INSERT INTO enrollment " +
                "(national_id, university_id, department_id, major_name, enrollment_date, enrollment_status) VALUES (?, ?, ?, ?, ?, ?)";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        try {
            statement = jdbc.getConnection().prepareStatement(st);
            statement.setString(1, String.valueOf(this.national_id));
            statement.setString(2, String.valueOf(this.availableMajors.get(majorIndex).getUniversity_id()));
            statement.setString(3, String.valueOf(this.availableMajors.get(majorIndex).getDepartment_id()));
            statement.setString(4, this.availableMajors.get(majorIndex).getMajor_name());
            statement.setString(5, String.valueOf(now));
            statement.setString(6, "pending");
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

    public void fetchEnrollmentInformation() throws SQLException {
        JDBC jdbc = JDBC.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        String st = "SELECT enrollment.*, university_name " +
                "FROM enrollment " +
                "INNER JOIN university " +
                "WHERE enrollment.university_id = university.university_id " +
                "AND enrollment.national_id = ?";
        try {
            statement = jdbc.getConnection().prepareStatement(st);
            statement.setString(1, String.valueOf(this.national_id));
            result = statement.executeQuery();
            while (result.next()) {
                this.enrollments.add(new Enrollment(
                        Integer.parseInt(result.getString(1)),
                        Integer.parseInt(result.getString(2)),
                        Integer.parseInt(result.getString(3)),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7)));
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
    public void cancelEnrollment(int enrollmentIndex) throws SQLException {
        JDBC jdbc = JDBC.getInstance();
        Connection connection =null;
        PreparedStatement statement = null;
        String st = "DELETE FROM enrollment\n" +
                "WHERE national_id = ?\n" +
                "AND university_id = ?\n" +
                "AND department_id = ?\n" +
                "AND major_name = ?;";
        try {
            statement = jdbc.getConnection().prepareStatement(st);
            statement.setString(1, String.valueOf(this.national_id));
            statement.setString(2, String.valueOf(this.enrollments.get(enrollmentIndex).getUniversity_id()));
            statement.setString(3, String.valueOf(this.enrollments.get(enrollmentIndex).getDepartment_id()));
            statement.setString(4, String.valueOf(this.enrollments.get(enrollmentIndex).getMajor_name()));
            statement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if (statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        }
    }

    public void updateMajorSeats(String department_id, String major_name) throws SQLException {
        JDBC jdbc = JDBC.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        String st = "UPDATE  major \n" +
                "SET available_seats = (SELECT  available_seats - 1\n" +
                "FROM (SELECT * FROM major) AS X \n" +
                "WHERE department_id = ? \n" +
                "AND major_name = ?) \n" +
                "WHERE department_id = ? AND major_name = ?;";

        try {
            statement = jdbc.getConnection().prepareStatement(st);
            statement.setString(1, department_id);
            statement.setString(2, major_name);
            statement.setString(3, department_id);
            statement.setString(4, major_name);
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

    public void allocateStudentToDepartment(String department_id) throws SQLException {
        JDBC jdbc = JDBC.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        String st = "UPDATE  student \n" +
                    "SET department_id = ? \n" +
                    "WHERE national_id = ?;";

        try {
            statement = jdbc.getConnection().prepareStatement(st);
            statement.setString(1, department_id);
            statement.setString(2, String.valueOf(this.national_id));
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



