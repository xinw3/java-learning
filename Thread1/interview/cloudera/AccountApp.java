package cloudera;

public class AccountApp {

	/*
	 * Simple sequential version
	 */
	public static void main(String[] args) {
		Account acc1 = new Account("Xin");
		Account acc2 = new Account("Wang");
		acc1.deposit(10000000);
		acc2.deposit(1000);
		
		acc1.withdraw(200);
		acc2.withdraw(1001);
		
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
	}
}
