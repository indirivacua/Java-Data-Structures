package prog3.grafos.utiles;


import prog3.grafos.Arista;
import prog3.grafos.Grafo;
import prog3.grafos.Vertice;
import prog3.listagenerica.ListaGenerica;
import prog3.listagenerica.ListaGenericaEnlazada;
import prog3.util.ColaGenerica;

public class RecorridosGrafos<T> {
	
	private void dfs (Grafo<T> grafo, Vertice<T> vertice, boolean[] marca, ListaGenerica<Vertice<T>> l) {
		//Acá se procesan los datos
		//System.out.println(vertice.dato());
		l.agregarFinal(vertice);
		marca[vertice.posicion()] = true;
		ListaGenerica<Arista<T>> adyacentes = grafo.listaDeAdyacentes(vertice);
		for (int i = 0; i < adyacentes.tamanio(); i++)
			if (marca[adyacentes.elemento(i).verticeDestino().posicion()] == false) {
				Vertice<T> v = adyacentes.elemento(i).verticeDestino();
				dfs(grafo, v, marca, l);
				//Acá generalmente se elimina de una lista (aux.eliminarEn(aux.tamanio()-1);)
			}
		/* Acá generalmente se desmarca si es necesario.
		 * Hay que tener en cuenta si se quiere desmarcar el "origen" o no.
		 * Si no se desea, se debería pasar el dato o el vertice de "origen" y hacer lo siguiente: 
		 * 		if (!vertice.dato().equals(islaO)) marca[vertice.posicion()] = false;
		*/
	}
	
	
	public ListaGenerica<Vertice<T>> dfs (Grafo<T> grafo) {
		ListaGenerica<Vertice<T>> vertices = new ListaGenericaEnlazada<Vertice<T>>();
		
		boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()];
		
		for (int i = 0; i < grafo.listaDeVertices().tamanio(); i++) {
			if (marca[i] == false) {
				Vertice<T> v = grafo.listaDeVertices().elemento(i);
				dfs(grafo, v, marca, vertices);
				//Si es necesario recorrer el grafo desde todos los vertices, 
				//acá se deben desmarcar todos con Arrays.fill(marca, false);
			}
		}
		
		return vertices;
	}
	
	private void bfs(Grafo<T> grafo, Vertice<T> vertice, boolean[] marca, ListaGenerica<Vertice<T>> l) {
		Vertice<T> v = null;
		ColaGenerica<Vertice<T>> cola = new ColaGenerica<Vertice<T>>();
		cola.encolar(vertice);
		cola.encolar(null);
		marca[vertice.posicion()] = true;
		while (!cola.esVacia()) {
			v = cola.desencolar();
			if (v != null) {
				l.agregarFinal(v);
				ListaGenerica<Arista<T>> adyacentes = grafo.listaDeAdyacentes(v);
				for (int i = 0; i < adyacentes.tamanio(); i++) {
					Vertice<T> vaux = adyacentes.elemento(i).verticeDestino();
					if (marca[vaux.posicion()] == false) {
						cola.encolar(vaux);
						marca[vaux.posicion()] = true;
					}
				}
			} else if (!cola.esVacia()) {
				cola.encolar(null);
			}
		}
	}
	
	//Es utilizado normalmente para resolver problemas con circunstancias que se expanden a lo largo del grafo, 
	//y no es posible resolverlo avanzando de manera lineal (como lo haría un DFS)
	public ListaGenerica<Vertice<T>> bfs(Grafo<T> grafo){
		ListaGenerica<Vertice<T>> vertices = new ListaGenericaEnlazada<Vertice<T>>();
		
		boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()];
		
		for (int i = 0; i < grafo.listaDeVertices().tamanio(); i++)
			if (marca[i] == false)
				bfs(grafo, grafo.listaDeVertices().elemento(i), marca, vertices);
		
		return vertices;
	}
}
