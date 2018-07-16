package prog3.grafos.utiles;

import prog3.grafos.Arista;
import prog3.grafos.Grafo;
import prog3.grafos.Vertice;
import prog3.listagenerica.ListaGenerica;

public class Dijkstra<T> {
	
	public static <T> void dijkstraSinHeap (Grafo<T> grafo, Vertice<T> v){ //v = Origen
		
		int[] vDist = new int[grafo.listaDeVertices().tamanio()]; // Costos minimos para acceder al vertice
		int[] vAnt = new int[grafo.listaDeVertices().tamanio()]; // Posiciones de los vertices por los cuales hay que pasar previamente para acceder a un vertice determinado
		boolean[] vConocido = new boolean[grafo.listaDeVertices().tamanio()];
		
		for (int i = 0; i < grafo.listaDeVertices().tamanio(); i++)
			if (i != v.posicion())
				vDist[i] = Integer.MAX_VALUE/2;
		
		for (int j = 0; j < grafo.listaDeVertices().tamanio(); j++) {
			Vertice<T> vAux = getVerticeDesconocidoMenorDist(grafo, vDist, vConocido);
			vConocido[vAux.posicion()] = true;
			ListaGenerica<Arista<T>> adyacentes = grafo.listaDeAdyacentes(vAux);
			for (int i = 0; i < adyacentes.tamanio(); i++)
				if (vDist[adyacentes.elemento(i).verticeDestino().posicion()] > vDist[vAux.posicion()] + adyacentes.elemento(i).peso()) {
					vAnt[adyacentes.elemento(i).verticeDestino().posicion()] = vAux.posicion();
					vDist[adyacentes.elemento(i).verticeDestino().posicion()] = vDist[vAux.posicion()] + adyacentes.elemento(i).peso();
				}
		}
		
		imprimir(grafo, vDist, vAnt, vConocido);
	}
	
	private static <T> Vertice<T> getVerticeDesconocidoMenorDist(Grafo<T> grafo, int[] vDist, boolean[] vConocido) {
		int minDist = Integer.MAX_VALUE/2;
		int minIndex = 0;
		for (int i = 0; i < vDist.length; i++)
			if (!vConocido[grafo.listaDeVertices().elemento(i).posicion()])
				if (minDist > vDist[grafo.listaDeVertices().elemento(i).posicion()]) {
					minDist = vDist[grafo.listaDeVertices().elemento(i).posicion()];
					minIndex = i;
				}
			
		return grafo.listaDeVertices().elemento(minIndex);
	}
	
	private static <T> void imprimir(Grafo<T> grafo, int[] vDist, int[] vAnt, boolean[] vConocido) {
		for (int i = 0; i < vDist.length; i++) 
			System.out.println(grafo.listaDeVertices().elemento(i).dato().toString() + ": " + vDist[i] + " | " + vAnt[i] + " | " + vConocido[i]);
	}
}
