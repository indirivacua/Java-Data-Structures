package prog3.listagenerica.test;

import prog3.listagenerica.ListaGenericaEnlazada;

public class TestRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {23,45,6,87,3};
		ListaGenericaEnlazada<Integer> l = recursion(arr, 0);
		System.out.print("Array: ");
		for (int i = 0; i < arr.length; i++)
			if (i != arr.length-1)
				System.out.print(arr[i] + " -> ");
			else
				System.out.print(arr[i]);
		System.out.println();
		System.out.println("Lista: " + l);
	}
	
	private static ListaGenericaEnlazada<Integer> recursion(int[] arr, int index) {
		// TODO Auto-generated method stub
		if (index == arr.length) {
			ListaGenericaEnlazada<Integer> res = new ListaGenericaEnlazada<Integer>();
			return res;
		} else {
			ListaGenericaEnlazada<Integer> res = recursion(arr, index+1);
			res.agregarInicio(arr[index]);
			return res;
		}
	}

}
