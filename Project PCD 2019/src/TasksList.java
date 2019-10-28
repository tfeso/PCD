public class TasksList implements PilhaMetodos {
	
	private int position;
	private Object[] pilha;
	
	public TasksList() {
		pilha = new Object[100];
		position = -1;
	}
	
	@Override
	public boolean empty() {
		
		return(position == -1);
	}


	@Override
	public Object peek() {
		
		if(empty())
			throw new IllegalStateException();
		else
			System.out.println("Peek : " + pilha[position]);
		return pilha[position];
	}

	@Override
	public synchronized Object pop() {
		
		if(empty())
			throw new IllegalStateException();
		else {
			Object posPop = pilha[position];
			pilha[position] = 0;
			position--;
			System.out.println("Pop : " + posPop);
			return posPop;
		}
	}

	@Override
	public synchronized void push(int valor) {
		
		if(size() == pilha.length) {
			throw new IllegalStateException();
		}
		position++;
		pilha[position] = valor;
		System.out.println("Push: " + valor + " na posição " + position);
	}



	@Override
	public int size() {

		if(empty())
			return 0;
		else {
			return position + 1;
		}
	}
	

}
