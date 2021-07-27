package interfaces.grafo;

import dominio.Trayecto;

public class Arista {

	private Trayecto trayecto;
	private Nodo origen;
	private Nodo destino;
	
	public Arista(Trayecto trayecto, Nodo origen, Nodo destino) {
		
		this.trayecto = trayecto;
		this.origen = origen;
		this.destino = origen;
		
	}
	
	public Trayecto getTrayecto() {
		return trayecto;
	}
	public Nodo getOrigen() {
		return origen;
	}
	public Nodo getDestino() {
		return destino;
	}
	
	
	
}
