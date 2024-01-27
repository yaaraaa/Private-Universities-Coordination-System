public class Application {
    private String first_name;
    private String last_name;
    private String highschool_grade;
    private String university_name;
    private String department_name;
    private int national_id;
    private int university_id;
    private int department_id;
    private String major_name;
    private String enrollment_date;
    private String enrollmentStatus;

    Application() {}

    Application(String first_name,
                String last_name,
                String highschool_grade,
                String university_name,
                String department_name,
                int national_id,
                int university_id,
                int department_id,
                String major_name,
                String enrollment_date,
                String enrollmentStatus) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.highschool_grade = highschool_grade;
        this.university_name = university_name;
        this.department_name = department_name;
        this.national_id = national_id;
        this.university_id = university_id;
        this.department_id = department_id;
        this.major_name = major_name;
        this.enrollment_date = enrollment_date;
        this.enrollmentStatus = enrollmentStatus;

    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public void setNational_id(int national_id) {
        this.national_id = national_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public void setEnrollment_date(String enrollment_date) {
        this.enrollment_date = enrollment_date;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setHighschool_grade(String highschool_grade) {
        this.highschool_grade = highschool_grade;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getMajor_name() {
        return major_name;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public int getNational_id() {
        return national_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public int getUniversity_id() {
        return university_id;
    }

    public String getEnrollment_date() {
        return enrollment_date;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public String getHighschool_grade() {
        return highschool_grade;
    }

}
