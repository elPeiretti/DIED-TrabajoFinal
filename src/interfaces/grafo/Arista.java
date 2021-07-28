package interfaces.grafo;

import java.awt.*;

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
	
	public void dibujar(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Stroke grosor_anterior = g2d.getStroke();
		g2d.setStroke(new BasicStroke(2));
		g.setColor(color);
		g.drawLine(origen.getX(), origen.getY(), destino.getX(), destino.getY());
		g2d.setStroke(grosor_anterior);
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
