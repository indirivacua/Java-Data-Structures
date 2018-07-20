package prog3.heap;

public class Heap<T extends Comparable<T>> {
	private T[] datos;
	private int cantElementos = 0;

	@SuppressWarnings("unchecked")
	public Heap() {
		this.datos = (T[]) new Comparable[100];
	}

	public Heap(T[] datos) {
		this.datos = datos;
	}

	public T minimo() {
		return this.datos[0];
	}

	public boolean agregar(T elem) {
		if (this.cantElementos < 100) {
			this.cantElementos++;
			this.datos[cantElementos] = elem;
			percolate_up();
			return true;
		} else
			return false;
	}

	private void percolate_up() {
		int i = this.cantElementos;
		if (i > 1) {
			while (((i / 2) >= 1) && ((this.datos[i / 2]).compareTo(this.datos[i]) > 0)) {
				T aux = this.datos[i / 2];
				this.datos[i / 2] = this.datos[i];
				this.datos[i] = aux;
				i = i / 2;
			}
		}
	}
	
	public T eliminar() {
		if (this.cantElementos > 0) {
			T elemento = this.datos[1];
			this.datos[1] = this.datos[this.cantElementos];
			this.cantElementos--;
			this.percolate_down(1);
			return elemento;
		}
		return null;
	}
	
	private void percolate_down(int i) {
		T elem = datos[i];
		boolean stop = false;
		while (((i * 2) <= cantElementos) && (!stop)) {
			int hijoMaximo = i * 2;
			if (hijoMaximo != this.cantElementos)
				if (datos[hijoMaximo + 1].compareTo(datos[hijoMaximo]) < 0)
					hijoMaximo++;
			if (elem.compareTo(datos[hijoMaximo]) > 0) {
				datos[i] = datos[hijoMaximo];
				i = hijoMaximo;
			} else
				stop = true;
		}
		this.datos[i] = elem;
	}

	public boolean esVacia() {
		return this.cantElementos == 0;
	}

	public void imprimir() {
		for (int i = 1; i <= this.cantElementos; i++)
			if (i != this.cantElementos)
				System.out.print(this.datos[i] + ", ");
			else
				System.out.print(this.datos[i]);
		System.out.println();
	}
}
