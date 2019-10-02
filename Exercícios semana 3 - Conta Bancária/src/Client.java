import java.util.Random;

public class Client extends Thread{

	
	private int identifier;
	private ContaBancaria account;
	private int totalDeposits;
	
	public Client(int identifier, ContaBancaria account) {

		this.identifier = identifier;
		this.account = account;
		totalDeposits = 0;
	}
	
	@Override
	public void run() {

		System.out.println("Client " + identifier + " is running!");
		while(!interrupted()) {
			int deposit = new Random().nextInt(101);
			totalDeposits += deposit;
			account.deposit(deposit);
			System.out.println("Client " + identifier + " deposited " + deposit + " €");
		}
	}
	
	public int getTotalDeposits() {
		return totalDeposits;
	}
	
	@Override
	public String toString() {

		return "Client " + identifier + " Total " + totalDeposits + " €";
	}
	
}
