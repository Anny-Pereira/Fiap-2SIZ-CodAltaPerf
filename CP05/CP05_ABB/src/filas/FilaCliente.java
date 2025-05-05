package filas;

import aplicacao.Cliente;

public class FilaCliente {

	public final int N = 20;
	Cliente dados[] = new Cliente[N];
	int ini, fim, cont;

	public void init() {
		ini = fim = cont = 0;
	}

	public boolean isEmpty() {
		return (cont == 0);
	}

	public boolean isFull() {
		return (cont == N);
	}

	public void enqueue(Cliente elem) {
		if (isFull())
			System.out.println("Fila cheia");
		else {
			dados[fim] = elem;
			fim = (fim + 1) % N;
			cont++;
		}
	}

	public Cliente dequeue() {
		Cliente elem = dados[ini];
		ini = (ini + 1) % N;
		cont--;
		return elem;
	}

	public Cliente first() {
		return (dados[ini]);
	}

}
