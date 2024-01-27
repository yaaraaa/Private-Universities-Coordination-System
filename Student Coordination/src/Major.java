public class Major {
    private String major_name;
    private String university_name;
    private int university_id;
    private int application_fees;
    private int department_id;

    Major() {}

    Major(String major_name, String university_name, int application_fees, int department_id, int university_id)
    {
        this.major_name = major_name;
        this.university_name = university_name;
        this.application_fees = application_fees;
        this.department_id = department_id;
        this.university_id = university_id;
    }

    public void setApplication_fees(int application_fees) {
        application_fees = application_fees;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public int getApplication_fees() {
        return application_fees;
    }

    public String getMajor_name() {
        return major_name;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public int getUniversity_id() {
        return university_id;
    }
}
