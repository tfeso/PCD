import java.util.ArrayList;

public class Race {

	private Window GUI;
	private ArrayList<Cavalo> listCavalos;
	
	public Race(Window GUI) {
		this.GUI = GUI; 
		listCavalos = new ArrayList<Cavalo>();
		listCavalos.add(new Cavalo(1001, this));
		listCavalos.add(new Cavalo(1002, this));
		listCavalos.add(new Cavalo(1003, this));
	}
	
	public void decrement(Cavalo cavalo) {
		GUI.decrement(cavalo);
	}
	
	public void RunRace() {
		for(Cavalo c: listCavalos) {
			c.start();
		}
	}
	

}
