package cloudera;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

	private int balance = 0;
	private String name = "";
	private Lock lock = new ReentrantLock();
	public Account(String n) {
		name = n;
	}
	
	// remember to unlock before return or else there would
	// be threads hang up.
	public void deposit(int amount) {
		lock.lock();
		balance += amount;
		if (balance > 1000000) {
			System.out.println("Hi " + name + ", Your balance cannot be over 1000000");
			balance -= amount;
			lock.unlock();
			return;
		}
		lock.unlock();
	}
	
	public void withdraw(int amount) {
		lock.lock();
		balance -= amount;
		if (balance < 0) {
			System.out.println("Hi " + name + ", Your balnace is not enough.");
			balance += amount;
			lock.unlock();
			return;
		}
		lock.unlock();
	}
	
	public void transfer(Account acc1, Account acc2, int amount) {
		acc1.withdraw(amount);
		acc2.deposit(amount);
	}
	
	public int getBalance() {
		return balance;
	}
	public String getName() {
		return name;
	}

}
