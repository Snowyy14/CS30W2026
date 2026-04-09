package Mastery;

// test the accounts
public class TestAccounts {
    public static void main(String[] args) {
PersonalAcct p = new PersonalAcct(150.0, "john", "doe", "123 elm", "city", "IL", "62704");
System.out.println(p);
        
        System.out.println("\nwithdrawing 60...");
p.withdrawal(60.0);
        System.out.println(p); 
        
        BusinessAcct b = new BusinessAcct(600.0, "jane", "smith", "456 oak", "ny", "NY", "10001");
        System.out.println("\n" + b);
        
        System.out.println("\ntaking out 150 from business...");
        b.withdrawal(150.0);
        System.out.println(b); 
        
    }
}
