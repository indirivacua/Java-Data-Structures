package prog3.grafos.utiles;

import prog3.grafos.Grafo;
import prog3.grafos.Vertice;

public class Floyd<T> {
	public static <T> void floyd (Grafo<T> grafo) {
		int[][] D = new int[grafo.listaDeVertices().tamanio()][grafo.listaDeVertices().tamanio()];
		for (int i = 0; i < D.length; i++)
			for (int j = 0; j < D.length; j++) 
				if (i != j)
					D[i][j] = Integer.MAX_VALUE/2;
		for (int i = 0; i < grafo.listaDeVertices().tamanio(); i++) { //Se recorre por filas
			Vertice<T> vAux = grafo.listaDeVertices().elemento(i);
			for (int j = 0; j < grafo.listaDeAdyacentes(vAux).tamanio(); j++) {
				int posAdyTovAux = grafo.listaDeAdyacentes(vAux).elemento(j).verticeDestino().posicion(); //Columna respectiva al vertice
				D[i][posAdyTovAux] = grafo.listaDeAdyacentes(vAux).elemento(j).peso();
			}
		}
		int[][] P = new int[grafo.listaDeVertices().tamanio()][grafo.listaDeVertices().tamanio()];
		for (int j = 0; j < P.length; j++) 
			for (int i = 0; i < P.length; i++)
					P[i][j] = j;
		
		for (int k = 0; k < grafo.listaDeVertices().tamanio(); k++)
			for (int i = 0; i < grafo.listaDeVertices().tamanio(); i++)
					for (int j = 0; j < grafo.listaDeVertices().tamanio(); j++)
						if (D[i][j] > D[i][k] + D[k][j]) {
							D[i][j] = D[i][k] + D[k][j];
							P[i][j] = k;
						}
		imprimir(D, P);
	}
	
	private static void imprimir(int[][] D, int[][] P) {
		for (int i = 0; i < D.length; i++) {
			for (int j = 0; j < D.length; j++)
				System.out.print("[" + D[i][j] + "]");
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < P.length; i++) {
			for (int j = 0; j < P.length; j++)
				System.out.print("[" + P[i][j] + "]");
			System.out.println();
		}
	}
}
