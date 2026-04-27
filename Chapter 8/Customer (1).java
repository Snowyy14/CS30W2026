/**
 * Customer class.
 */

public class Customer {
	private String firstName, lastName;

	//create String variables street, city, province, postal code	

	
	/**
	 * constructor
	 * pre: none
	 * post: A Customer object has been created. 
	 * Customer data has been initialized with parameters.
	 */
	public Customer(String fName, String lName) //modify constructor to include street, city, province, postal code
	{
		firstName = fName;
		lastName = lName;

		//reflect the changes in the parameter
	}
	

	//create changeCity method that asks the user their city and records city in a variable above

	//create changeStreet method that asks the user their street and records street in a variable above

	//create changeProvince method that asks the user their province and records province in a variable above

	//create changePostalCode method that asks the user their postal code and records postal code in a variable above



	/** 
	 * Returns a String that represents the Customer object.
	 * pre: none
	 * post: A string representing the Customer object has 
	 * been returned.
	 */
	public String toString() {
		String custString;

		//update this string so that it contains the street, city, province, and postal code
		custString = firstName + " " + lastName + "\n";
	 	return(custString);
	}

}
