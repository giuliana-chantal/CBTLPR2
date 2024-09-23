public class Staff {
    private String name;
    private String position;

    public Staff(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Staff[name=" + name + ",position=" + position + "]";
    }
}
