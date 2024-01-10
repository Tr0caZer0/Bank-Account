/*
 * @Author: Carl-Bastian Åström, basstr-2   
 * 
 */

package basstr2;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BankLogic {
	
	// Stores objects from subclasses Customer and Account. 
	private ArrayList<Customer> customers = new ArrayList<>();
	private ArrayList<Account> accounts = new ArrayList<>();
	private static HashMap<Integer, String> checkForSimilarities = new HashMap<>();
	
	Account account = new Account();
	
	/*Method getAllCustomers steps through the Customer object and adds each customer through getCustoomerDetails to the List.
	 * */
	public List<String> getAllCustomers() {
		List<String> allCustomers = new ArrayList<>();
		
		for(Customer temp : customers) {
			
			allCustomers.add(temp.toString());
		}
		
		return allCustomers;
	}// End getAllCustomers query method
	
	/*Method deletes a customer based on their personal number. 
	 * With the help of an Iterator and while-loop we iterate through the Customer object. If the personal number exists 
	 * we add that customer to our afterDelete List and at the last stage remove it from the class object Customer. 
	 * With the help of a dictionary we have stored associated accountId's. With the help of the closeAccount method we can have the 
	 * account details removed as well and then stored in the afterDelt List.
	 * */
	public List<String> deleteCustomer(String pNo){
		
		
		List<String> afterDelete = new ArrayList<>();
		
		Iterator<Customer> iterator = customers.iterator();
		while(iterator.hasNext()) {
			
			Customer member = iterator.next();
			
			if(member.getPersonalNumber().equals(pNo)) {

				afterDelete.add(member.toString());
				for(Map.Entry<Integer, String> tempM : checkForSimilarities.entrySet()) {
					
					if(pNo.equals(tempM.getValue())) {
						String toCloseAccount = closeAccount(pNo, tempM.getKey());
						if(toCloseAccount != null) {
							afterDelete.add(toCloseAccount);
						}
					}
				}
				customers.remove(member);
				return afterDelete;
			}// End if statement
			
		}// End for-loop
		
		
		return null;
	}// End deleteCustomer query method.	


	/*Method createCutomer creates a customer as long as their is no existing customer associated with the personal number suggested. 
	 * */
	public boolean createCustomer(String name, String surname, String pNo) {
		
		for(Customer pNoExists : customers) {
			
			//If the customer is not in the list, the new customer will be created and return true. Otherwise false.
			if(pNoExists.getPersonalNumber().equals(pNo)) {
				
				return false;
			}
		}
		
		Customer newCustomer = new Customer(name, surname, pNo);
		customers.add(newCustomer);
		return true;
		
	}// End createCustomer queryMethod
	
	//********
	
	/*Method getCustomer calls on the sought for customer if the personal number exists.
	 * Through a nested for-loop, if the accountId is greater than 1000 we will check for similarities in our dictionary and see if 
	 * there are any account details to be added. 
	 * */
	public List<String> getCustomer(String pNo) {
		List<String> allCustomers = new ArrayList<>();
		
		for(Customer temp : customers) {
					
			if(temp.getPersonalNumber().equals(pNo)) {
						 
				allCustomers.add(temp.toString());
							
				for(Account tempA : accounts) {

					if(tempA.getAccountNumber() > 1000) {
						
						for(Map.Entry<Integer, String> tempM: checkForSimilarities.entrySet()) {
							
							if(temp.getPersonalNumber().equals(tempM.getValue()) && (tempA.getAccountNumber() == tempM.getKey()) ) {
								allCustomers.add(tempA.getAccountDetails());
								
							}//End && if-Statement
						}//End Map for-loop
					}//End > if-statement
				}//End Account for-loop
				return allCustomers;
				
			}//End equals if-statement
		}// End Customer for-loop	
		return null;
	}// End getCustomer query method
	
	/* Method changeCustomer turn true if the sought for personal number is part of the Customer object and if surname and name isn't blank.
	 * Depending what we're going to change we use the ternary operator to confirm which or both names should be changed through the mutator method
	 * setCustomer.  
	 * */
	public boolean changeCustomerName(String name, String surname, String pNo) {

		for(Customer customer : customers) {
			
			if(customer.getPersonalNumber().equals(pNo) && (!(surname.isEmpty() && name.isBlank()))) {
				
				String firstName = name.isEmpty() ? customer.getFirstName() : name;
				String lastName = surname.isEmpty() ? customer.getLastName() : surname;
				customer.setCustomer(firstName, lastName, customer.getPersonalNumber());

					return true;
			}
		}// End for-loop
		
		return false;
		
	}// End changeCustomerName query method
	
	/* Method createSavingsAccount creates a saving account if there is a customer associated with the personal number.
	 * And if so, returns the associated accountId. 
	 * */
	public int createSavingsAccount(String pNo) {
		//Account newAccount = new Account(0, new BigDecimal(0), "", 0.0);
		//listOfAccountDetails
		for(Customer temp : customers) {
			
			if(temp.getPersonalNumber().equals(pNo)) {
				//Account accountNumber = new Account(accountNumber.getAccountNumber(),accountNumber.getSaldo(), accountNumber.getAccountType(), accountNumber.getInterestRate()); 
				Account newAccount = new Account(0, new BigDecimal(0), "", 0.0);
				
				checkForSimilarities.put(newAccount.getAccountNumber(), pNo);
				accounts.add(newAccount);
				return newAccount.getAccountNumber(); 
			}
		}
		return -1;
	}// End createSavingsAccount query method
	
	/* Method getAccount checks through a nested for-loop if the requested personal number exists. If it does it checks if there is an 
	 * accointId as per request. To make sure that the personal number doesn't match with a accountId not correlated with it a dictionary 
	 * will help out filtering our the correct account. 
	 * If accountId in the Account object equals the accountId in the method signature, logical AND, accountId  equals the key in the dictorany 
	 * - Separated - Logical AND, 
	 *  the personal number in the Customer Object equals the personal number in the method, logical AND, the personal number equals the value in
	 *  the dictionary. 
	 *  
	 *  Then we get the account details by storing it in the toGetAccount Variable. 
	 * */
	public String getAccount(String pNo, int accountId) {
		String toGetAccount = "";
		for(Customer tempC : customers) {
			if(tempC.getPersonalNumber().equals(pNo)) {
				for(Account tempA : accounts) {
					if(tempA.getAccountNumber() == accountId) {
						for(Map.Entry<Integer, String> tempM : checkForSimilarities.entrySet()) {
							if((tempA.getAccountNumber() == tempM.getKey()) && (tempC.getPersonalNumber().equals(tempM.getValue()))) {
							
								toGetAccount = (tempA.getAccountDetails());
								
							}
						}
					}
					
				}
			}
		}
		if(!toGetAccount.equals("")) {
			return toGetAccount;
		}else {
			return null;
		}
		
	}// End getAccount query method
	
	//********

	// Method deposit & withdraw depends on the method transaction. Were boolean true represents the deposit and the boolean false the withdraw  
	public boolean deposit(String pNo, int accountId, int amount) {
		return transaction(pNo, accountId, amount, true);
	}//End deposit queary method
	public boolean withdraw(String pNo, int accountId, int amount) {
		return transaction(pNo, accountId, amount, false);
	}//End withdraw queary method
	
	/* Method transaction is a function representing two methods, deposit and withdraw. 
	 * In the nested for-loops it checks if Customer and account details matches the one of the method signature. 
	 * If it does, depending on its boolean the balance will be updated through the variable updateBalance and set with the help of the
	 * setAccount mutator method.  
	 * */
	private boolean transaction(String pNo, int accountId, int amount, boolean withdrawalOrDeposit) {
		
		for(Customer tempC : customers) {
			if(tempC.getPersonalNumber().equals(pNo)) {
				for(Account tempA : accounts) {
					if(tempA.getAccountNumber() == accountId) {
						for(Map.Entry<Integer, String> tempM : checkForSimilarities.entrySet()) {
							//Check if should be under account. 
							if((tempA.getAccountNumber() == tempM.getKey()) && ((tempC.getPersonalNumber().equals(tempM.getValue())))) {
								if(amount > 0) {
									if(account.getDepositOrWithdrawal(amount, tempA, withdrawalOrDeposit)){
										return true;
									}//End if-statement
								}//End if-statement
							}//End if-statement
						}// End inner for-loop
					}//End if-statement
					
				}// End inner for-loop
			}//End if-statement
		}// End outer for-loop
		return false;
	}// End transaction method
	
	//********
	
	/* Method closeAccount closes the account of the customer if the signature variables matches the one of the customer pNo and its accountId.
	 * With the help of two Iterators we iterate through the Customer and Account Object and check for a match. If they match and they are part of the Dictionay
	 * we will calculate the interest for the customers account, then remove it and return it it's value. 
	 * */
	public String closeAccount(String pNo, int accountId) {
		
		Iterator<Customer> customerIterator  = customers.iterator();
		
		while(customerIterator.hasNext()) {
			Customer member = customerIterator.next();
			
			if(member.getPersonalNumber().equals(pNo)) {
				Iterator<Account> accountIterator = accounts.iterator();
				
				while(accountIterator.hasNext()) {
					account = accountIterator.next();
					
					if(account.getAccountNumber() == accountId) {
						for(Map.Entry<Integer, String> tempM : checkForSimilarities.entrySet()) {
							
							if(((account.getAccountNumber() == tempM.getKey())) && (member.getPersonalNumber().equals(tempM.getValue()))) {
								
								BigDecimal interestStepOne = account.getUnFormatedSaldo().multiply(BigDecimal.valueOf(account.getUnFormatedInterestRate()));
								BigDecimal interestStepTwo = interestStepOne.divide(BigDecimal.valueOf(100));
								
								account.setAccount(accountId, account.getUnFormatedSaldo(), account.getAccountType(), interestStepTwo.doubleValue());
								accounts.remove(account);
								
								return (account.toString());
							}// End if statement
						}// End for-loop
					}// End if statement
				}// End inner while-loop
			}// End if statement
		}// End while-loop
		return null;
	}//End closeAccount query method

}// End BankLogic class



