package Mastery;

// staff class
public class Staff extends UEmployee {
    private String title;

    public Staff(String n, double s, String t) {
super(n, s); // call main constructor
        title = t;
    }

    public String getJobTitle() {
        return title;
    }

    public void setJobTitle(String t) {
                title = t;
    }

    public String toString() {
return super.toString() + " Title: " + title;
    }
}
