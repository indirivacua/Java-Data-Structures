package prog3.arbol.binario.util;

import prog3.arbol.binario.ArbolBinario;
import prog3.util.ColaGenerica;

public class RecorridoArbolBinario<T> {

	public void imprimirPreOrder(ArbolBinario<T> aBinario) {
		System.out.print(aBinario.getDatoRaiz() + ", ");
		if (!aBinario.getHijoIzquierdo().esVacio())
			imprimirPreOrder(aBinario.getHijoIzquierdo());
		if (!aBinario.getHijoDerecho().esVacio())
			imprimirPreOrder(aBinario.getHijoDerecho());
	}

	public void imprimirInOrder(ArbolBinario<T> aBinario) {
		if (!aBinario.getHijoIzquierdo().esVacio())
			imprimirPreOrder(aBinario.getHijoIzquierdo());
		System.out.print(aBinario.getDatoRaiz() + ", ");
		if (!aBinario.getHijoDerecho().esVacio())
			imprimirPreOrder(aBinario.getHijoDerecho());
	}

	public void imprimirPostOrder(ArbolBinario<T> aBinario) {
		if (!aBinario.getHijoIzquierdo().esVacio())
			imprimirPreOrder(aBinario.getHijoIzquierdo());
		if (!aBinario.getHijoDerecho().esVacio())
			imprimirPreOrder(aBinario.getHijoDerecho());
		System.out.print(aBinario.getDatoRaiz() + ", ");
	}
	
	public static <T> void recorridoPorNiveles(ArbolBinario<T> aBinario) {
		ArbolBinario<T> arbol= null;
		ColaGenerica<ArbolBinario<T>> cola = new ColaGenerica<ArbolBinario<T>>();
		cola.encolar(aBinario);
		cola.encolar(null);
		while(!cola.esVacia()) {
			arbol= cola.desencolar();
			if(arbol!= null) {
				System.out.print(arbol.getDatoRaiz() + ", ");
				if (!arbol.getHijoIzquierdo().esVacio())
					cola.encolar(arbol.getHijoIzquierdo());
				if(!arbol.getHijoDerecho().esVacio())
					cola.encolar(arbol.getHijoDerecho());
			} else if(!cola.esVacia()) {
				System.out.println();
				cola.encolar(null);
			}
		}
	}
}
