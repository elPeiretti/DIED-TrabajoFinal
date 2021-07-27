package interfaces.grafo;

import dominio.Estacion;

public class Nodo {

	private Estacion estacion;
	private Integer x;
	private Integer y;
	
	public Nodo(Estacion estacion, Integer x, Integer y) {
		this.estacion = estacion;
		this.x = x;
		this.y = y;
	}
	
	public Estacion getEstacion() {
		return estacion;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}
	
}
