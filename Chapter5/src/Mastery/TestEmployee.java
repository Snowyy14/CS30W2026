package Mastery;

// test class for employees
public class TestEmployee {

public static void main(String[] args) {
Faculty f1 = new Faculty("dr smith", 85000, "CS");
Faculty f2 = new Faculty("jane doe", 92000, "Math");

Staff s1 = new Staff("bob", 45000, "admissions");
Staff s2 = new Staff("alice", 40000, "janitor"); // shes a janitor

System.out.println("--- faculty ---");
System.out.println(f1.toString());
System.out.println(f2.toString());

        System.out.println("\n--- staff ---");
        System.out.println(s1.toString());
        System.out.println(s2.toString());
    }
}
