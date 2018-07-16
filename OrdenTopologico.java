package prog3.grafos.utiles;

import prog3.grafos.Grafo;
import prog3.grafos.Vertice;

public class OrdenTopologico {
	public static <T> void ordenTopologico(Grafo<T> grafo){
		int[] grado_in = getGrado_in(grafo);
		for (int i = 0; i < grado_in.length; i++) {
			Vertice<T> v = getGradoCero(grafo, grado_in, i);
			System.out.print(v.dato().toString() + ", ");
			actualizarGrado_in(grafo, v, grado_in);
		}
	}
	
	private static <T> int[] getGrado_in(Grafo<T> grafo) {
		int[] grado_in = new int[grafo.listaDeVertices().tamanio()];
		for (int i = 0; i < grado_in.length; i++) {
			Vertice<T> vAux = grafo.listaDeVertices().elemento(i);
			for (int j = 0; j < grafo.listaDeAdyacentes(vAux).tamanio(); j++)
				grado_in[grafo.listaDeAdyacentes(vAux).elemento(j).verticeDestino().posicion()] += 1;
		}
		return grado_in;
	}
	
	private static <T> Vertice<T> getGradoCero(Grafo<T> grafo, int[] grado_in, int i){
		for (; i < grado_in.length; i++)
			if (grado_in[i] == 0)
				return grafo.listaDeVertices().elemento(i);
		return null;
	}
	
	private static <T> void actualizarGrado_in(Grafo<T> grafo, Vertice<T> v, int[] grado_in) {
		for (int i = 0; i < grafo.listaDeAdyacentes(v).tamanio(); i++)
			grado_in[grafo.listaDeAdyacentes(v).elemento(i).verticeDestino().posicion()] -= 1;
	}
}
