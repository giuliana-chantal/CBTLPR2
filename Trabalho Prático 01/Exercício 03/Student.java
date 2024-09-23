public class Student {
    private String name;
    private String major;

    public Student(String name, String major) {
        this.name = name;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String toString() {
        return "Student[name=" + name + ",major=" + major + "]";
    }
}
