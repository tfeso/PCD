import java.util.ArrayList;

public class ContaBancaria {

	private int balance;
	private int identifier;
	
	
	
	public void deposit(int identifier, int balance) {
		this.identifier = identifier;
		this.balance = balance;
	}
	
	private int getBalance() {
		return balance;
	}
	
	public synchronized void deposit(int amount) {
		
		balance += amount;
	}
	
	public static void main(String[] args) {

		ArrayList<Client> clients = new ArrayList<Client>();
		ContaBancaria conta = new ContaBancaria();
		
			try {
				for(int i = 0; i < 10; i++) {
					Client c = new Client(i, conta);
					clients.add(c);
					c.start();
					c.sleep(20000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		for(Client c : clients) {
			c.interrupt();
		}
		
		for(Client c : clients) {
			try {
				c.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int totalAccounts = 0;
		
		for(Client c : clients) {
			totalAccounts += c.getTotalDeposits();
			System.out.println(c.toString());
		}
		System.out.println("Value of balance: " + conta.getBalance() + " €");
		System.out.println("Value of all accounts: " + totalAccounts + " €");
	}

}
