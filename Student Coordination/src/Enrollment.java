import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Enrollment {
    Enrollment() {}

    Enrollment(int national_id,
               int university_id,
               int department_id,
               String major_name,
               String enrollment_date,
               String enrollment_status,
               String university_name) {

        this.national_id = national_id;
        this.university_id = university_id;
        this.department_id = department_id;
        this.major_name = major_name;
        this.enrollment_date = enrollment_date;
        this.enrollment_status = enrollment_status;
        this.university_name = university_name;
    }
    private int national_id;
    private int university_id;
    private int department_id;
    private String university_name;
    private String major_name;
    private String enrollment_date;
    private String enrollment_status;

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public void setNational_id(int national_id) {
        this.national_id = national_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    public void setEnrollment_date(String enrollment_date) {
        this.enrollment_date = enrollment_date;
    }

    public void setEnrollment_status(String enrollment_status) {
        this.enrollment_status = enrollment_status;
    }

    public int getUniversity_id() {
        return university_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public int getNational_id() {
        return national_id;
    }

    public String getMajor_name() {
        return major_name;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public String getEnrollment_date() {
        return enrollment_date;
    }

    public String getEnrollment_status() {
        return enrollment_status;
    }
}

