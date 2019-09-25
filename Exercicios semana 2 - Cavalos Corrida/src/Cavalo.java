public class Cavalo extends Thread {

	private Race race;
	private int identificador;
	private int moviments;
	
	public Cavalo(int identificador, Race race) {
		this.identificador = identificador;
		this.race = race;
		moviments = 90;
	}
	
	public int getIdentificador() {
		return identificador;
	}
	
	public int getMoviments() {
		return moviments;
	}
	
	@Override
	public void run() {	
		try {
			for(int i = moviments; i > 0; i--)  {
				System.out.println("Moviments " + moviments + " do cavalo " + identificador);
				moviments--;
				race.decrement(this);
				sleep(10 + (int)(Math.random() * (20 - 10)));	
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Cavalo " + identificador + " acabou!");
	}
}