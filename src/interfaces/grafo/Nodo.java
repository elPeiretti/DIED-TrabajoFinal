package interfaces.grafo;

import dominio.Estacion;

public class Nodo {

	private Estacion estacion;
	private Integer x;
	private Integer y;
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estacion == null) ? 0 : estacion.hashCode());
		return result;
	}

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nodo other = (Nodo) obj;
		if (estacion == null) {
			if (other.estacion != null)
				return false;
		} else if (!estacion.equals(other.estacion))
			return false;
		return true;
	}
	
	
}
