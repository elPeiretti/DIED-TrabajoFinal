package dominio;

import java.util.Vector;

public class Trayecto {

	private static Integer ultimo_id=0;
	private String id_trayecto;
	private Integer distancia;
	private Integer duracion;
	private Integer cant_max_pasajeros;
	private EstadoTrayecto estado;
	private Double costo;
	private Estacion origen;
	private Estacion destino;
	

	public Trayecto(Integer distancia, Integer duracion, Integer cant_max_pasajeros, Double costo, Estacion origen, Estacion destino, EstadoTrayecto estado) {
		this.id_trayecto = "TRAY"+(++ultimo_id).toString();
		this.distancia = distancia;
		this.duracion = duracion;
		this.cant_max_pasajeros = cant_max_pasajeros;
		this.estado = estado;
		this.costo = costo;
		this.origen = origen;
		this.destino = destino;
	}
	
	public Trayecto(String id_trayecto, Integer dist, Integer duracion, Integer capacidad, Double costo, Estacion origen, Estacion destino, EstadoTrayecto estado) {
		this.id_trayecto = id_trayecto;
		this.distancia = dist;
		this.duracion = duracion;
		this.cant_max_pasajeros = capacidad;
		this.estado = estado;
		this.costo = costo;
		this.origen = origen;
		this.destino = destino;
	}

	public String toString() {
		return origen.getNombre()+" --> "+destino.getNombre()
				+" | $"+costo.toString()
				+", "+distancia.toString()+" [km], "
				+duracion.toString()+" [min], "
				+cant_max_pasajeros.toString()+" pasajeros";
	}
	
	public static void setUltimo_id(Integer id) {
		ultimo_id=id;
	}

	public String getId_trayecto() {
		return id_trayecto;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public Integer getCant_max_pasajeros() {
		return cant_max_pasajeros;
	}

	public EstadoTrayecto getEstado() {
		return estado;
	}

	public Double getCosto() {
		return costo;
	}

	public Estacion getOrigen() {
		return origen;
	}

	public Estacion getDestino() {
		return destino;
	}

	public void setEstado(EstadoTrayecto estado) {
		this.estado = estado;
	}

	public void setOrigen(Estacion origen) {
		this.origen = origen;
	}
	
	public void setDestino(Estacion destino) {
		this.destino = destino;
	}
	
	public Boolean equals(Trayecto t) {
		return this.id_trayecto.equals(t.id_trayecto);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_trayecto == null) ? 0 : id_trayecto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trayecto other = (Trayecto) obj;
		if (id_trayecto == null) {
			if (other.id_trayecto != null)
				return false;
		} else if (!id_trayecto.equals(other.id_trayecto))
			return false;
		return true;
	}

	public Vector<String> asVector() {
		Vector<String> data = new Vector<String>();
		data.add(origen.getNombre());
		data.add(destino.getNombre());
		data.add(costo.toString());
		data.add(distancia.toString());
		data.add(duracion.toString());
		data.add(cant_max_pasajeros.toString());
		return data;
	}
	
	
}
