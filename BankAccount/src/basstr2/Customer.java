package basstr2;

public class Customer {
	
	private String name;
	private String surname;
	private String pNo;
	
	public Customer() {
		
	}
	
	public Customer(String name, String surname, String pNo) {
		
		this.name = name;
		this.surname = surname;
		this.pNo = pNo;
		
	}// End constructor method
	
	public String getFirstName() {
		return name;
	}// End getFirstName query method
	
	public String getLastName() {
		return surname;
	}// End getLastName query method

	public String getPersonalNumber() {
		return pNo;
	} // End getPersonalNumber query method
	
	/*Method getCustomerDetails returns all the Customer details as one string. 
	 * */
	@Override
	public String toString() {
	        return getPersonalNumber() + " " + getFirstName() + " " + getLastName();
    }//End getCustomerDetails method
	 
	

	public void setCustomer(String name, String surname, String pNo) {
		
		this.name = name;
		this.surname = surname;
		this.pNo = pNo;
		
	}// End setCustomer mutator method
}
