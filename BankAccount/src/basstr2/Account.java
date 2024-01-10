package basstr2;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

//Class Account: takes all the information initiated through the instances under the "AccountDetails" class by the 
//objects called upon by "TestBanking" class and the superior "BankLogic" class.

public class Account {
	// Konto nummer, börjar på 1001.
		private int accountId;
		private static int userAccountNumber = 1000;
		
		// pengar på kontot, Lagras som BigDecimal
		private BigDecimal amount;
		private static BigDecimal newAmount = new BigDecimal(0.0);
		
		
		// Kontotyp, sparkonto
		private String accountType;
		private static String whatAccountType = "Sparkonto";
		

		//Räntesatts, är 1.2%. Ska sättas som KONSTANT?
		private double interestRate;
		private static double bankAccountInterestRate = (1.2);
		
		public Account() {
			
		}
		
		public Account(int accountId, BigDecimal amount, String accountType, double interestRate){
			
			userAccountNumber++;
			if(accountId >= 0) {
				this.accountId = userAccountNumber;
				this.amount = newAmount; 
				this.accountType = whatAccountType;
				this.interestRate = bankAccountInterestRate;
				
				
			}// End if statement
			
			
		}// End constructur Account
		
		public int getAccountNumber() {
			
			return accountId;
		}// End addAccountNumber query method
		
		public String getSaldo() {
			String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(amount);
			return balanceStr;
		}// End setSaldo query method 
		public void setSaldo(BigDecimal amount) {
			this.amount = amount;
		}// End setSaldo query method 
		
		public BigDecimal getUnFormatedSaldo() {
			return amount;
		}// End method getUnFormatedSaldo()
		
		public String getAccountType() {
			
			return accountType;
		}// End setAccountType query method 

		public String getInterestRate() {
			NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv","SE"));
			percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
			String percentStr = percentFormat.format(interestRate/100);
			return percentStr;
		}// End getInterestRate query method
		
		public String getInterest() {
			String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(interestRate);
			return balanceStr;
		}// End setSaldo query method 
		
		public double getUnFormatedInterestRate() {
			return interestRate;
		}// getUnFormatedInterestRate()
		
		/*To return account details. 
		 * */
		public String getAccountDetails() {
			
			return Integer.toString(getAccountNumber()) + " "+ getSaldo() +" "+ getAccountType() +" "+ getInterestRate();
		}
		
		/*To return account details showing interest instead of interest rate. 
		 * */
		@Override
		public String toString() {
			
			return Integer.toString(getAccountNumber()) + " "+ getSaldo() +" "+ getAccountType() +" "+ getInterest();
		}
		
		
		public void setAccount(int accountId, BigDecimal amount, String accountType, double interestRate){
			
			this.accountId = accountId;
			this.amount = amount; 
			this.accountType = accountType;
			this.interestRate = interestRate;
			
		} // End setAccount mutator method.
		
		public boolean getDepositOrWithdrawal( int amount, Account tempA, boolean withdrawalOrDeposit) {
			BigDecimal integerToBigDecimal = new BigDecimal(amount);
			BigDecimal updatedBalance = new BigDecimal(0);
			if(withdrawalOrDeposit || tempA.getUnFormatedSaldo().compareTo(integerToBigDecimal) >= 0) {
				
				if(withdrawalOrDeposit == false) {
					updatedBalance = tempA.getUnFormatedSaldo().subtract(integerToBigDecimal);
				}else {
					updatedBalance = tempA.getUnFormatedSaldo().add(integerToBigDecimal);
				}
				
				tempA.setSaldo(updatedBalance);
				return true;
			}else {
				return false;
			}
		}
		
//		private void accountTransaction(String pNo, int accountId, int amount, boolean withdrawalOrDeposit) {
//			BigDecimal integerToBigDecimal = new BigDecimal(amount);
//			BigDecimal updatedBalance = new BigDecimal(0);
//			
//			for(Customer tempC : listOfCustomerDetails) {
//				if(tempC.getPersonalNumber().equals(pNo)) {
//					for(Account tempA : listOfAccountDetails) {
//						if(tempA.getAccountNumber() == accountId) {
//							for(Map.Entry<Integer, String> tempM : checkForSimilarities.entrySet()) {
//								//Check if should be under account. 
//								if(((tempA.getAccountNumber() == accountId) && (accountId == tempM.getKey())) && ((tempC.getPersonalNumber().equals(pNo)) && pNo.equals(tempM.getValue()))) {
//									if(amount > 0) {
//										if(withdrawalOrDeposit || tempA.getUnFormatedSaldo().compareTo(integerToBigDecimal) >= 0) {
//											
//											if(withdrawalOrDeposit == false) {
//												updatedBalance = tempA.getUnFormatedSaldo().subtract(integerToBigDecimal);
//											}else {
//												updatedBalance = tempA.getUnFormatedSaldo().add(integerToBigDecimal);
//											}
//											
//											tempA.setSaldo(updatedBalance);// Kika om den ska ändras
//											return true;
//										}//End if-statement
//									}//End if-statement
//								}//End if-statement
//							}// End inner for-loop
//						}//End if-statement
//						
//					}// End inner for-loop
//				}//End if-statement
//			}// End outer for-loop
//			return false;
//		}// End transaction method
		
}
