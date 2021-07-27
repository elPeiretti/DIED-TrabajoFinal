package interfaces.grafo;

import java.awt.Color;

import dominio.Trayecto;

public class Arista {

	private Trayecto trayecto;
	private Color color;
	private Nodo origen;
	private Nodo destino;
	
	public Arista(Trayecto trayecto, Color color, Nodo origen, Nodo destino) {
		
		this.trayecto = trayecto;
		this.color = color;
		this.origen = origen;
		this.destino = destino;
	}
	
	public void setOrigen(Nodo origen) {
		this.origen = origen;
	}

	public void setDestino(Nodo destino) {
		this.destino = destino;
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
	public Color getColor() {
		return color;
	}
	
	
	
}
