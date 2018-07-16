package prog3.arbol.general.util;

import prog3.arbol.general.ArbolGeneral;
import prog3.listagenerica.ListaGenericaEnlazada;
import prog3.util.ColaGenerica;

public class RecorridoArbolGeneral<T> {

	public static <T> void recorridoPreOrder(ArbolGeneral<T> a) {
		if (!a.esVacio()) {
			System.out.print(a.getDatoRaiz() + ", ");
			ListaGenericaEnlazada<ArbolGeneral<T>> lisHijos = (ListaGenericaEnlazada<ArbolGeneral<T>>) a.getHijos();
			for (int i = 0; i < lisHijos.tamanio(); i++) {
				ArbolGeneral<T> hijo = lisHijos.elemento(i);
				recorridoPreOrder(hijo);
			}
		}
	}
	
	public static <T> void recorridoPostOrder(ArbolGeneral<T> a) {
		if (!a.esVacio()) {
			ListaGenericaEnlazada<ArbolGeneral<T>> lisHijos = (ListaGenericaEnlazada<ArbolGeneral<T>>) a.getHijos();
			for (int i = 0; i < lisHijos.tamanio(); i++) {
				ArbolGeneral<T> hijo = lisHijos.elemento(i);
				recorridoPostOrder(hijo);
			}
			System.out.print(a.getDatoRaiz() + ", ");
		}
	}
	
	public static <T> void recorridoInOrder(ArbolGeneral<T> a) {
		if (!a.esVacio()) {
			ListaGenericaEnlazada<ArbolGeneral<T>> lisHijos = (ListaGenericaEnlazada<ArbolGeneral<T>>) a.getHijos();
			if (lisHijos.tamanio() != 0)
				recorridoInOrder(lisHijos.elemento(0));
			System.out.print(a.getDatoRaiz() + ", ");
			for (int i = 1; i < lisHijos.tamanio(); i++) {
				ArbolGeneral<T> hijo = lisHijos.elemento(i);
				recorridoInOrder(hijo);
			}
		}
	}
	
	public static <T> void recorridoPorNiveles(ArbolGeneral<T> a) {
		ArbolGeneral<T> arbol = null;
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		cola.encolar(a);
		cola.encolar(null);
		while (!cola.esVacia()) {
			arbol = cola.desencolar();
			if (arbol != null) {
				System.out.print(arbol.getDatoRaiz() + ", ");
				for (int i = 0; i < arbol.getHijos().tamanio(); i++)
					cola.encolar(arbol.getHijos().elemento(i));
			} else {
				System.out.println();
				if (!cola.esVacia()) 
					cola.encolar(null);
			}
		}
	}
}
