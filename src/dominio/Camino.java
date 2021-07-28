package dominio;

import java.util.ArrayList;
import java.util.List;

public class Camino {

	private static Integer ultimo_id=0;
	private String id_camino;
	private Estacion origen;
	private Estacion destino;
	private Double costo;
	private Integer duracion;
	private Integer distancia;
	private List<Trayecto> combinacion;
	
	public Camino(List<Trayecto> combinacion, Estacion origen, Estacion destino, Double costo, Integer duracion, Integer distancia) {
		this.id_camino = "PAT"+(++ultimo_id).toString();
		this.combinacion = combinacion;
		this.origen = origen;
		this.destino = destino;
		this.distancia = distancia;
		this.duracion = duracion;
		this.costo = costo;
	}
	
	public Camino(List<Trayecto> c, Estacion origen, Estacion destino, Object[] data) {
		this.combinacion = c;
		this.origen = origen;
		this.destino = destino;
		this.costo = (Double)data[0];
		this.duracion = (Integer)data[1];
		this.distancia = (Integer)data[2];
	}

	public Camino(String id_camino, Integer distancia, Integer duracion, Double costo, Estacion origen, Estacion destino) {
		this.combinacion = new ArrayList<Trayecto>();
		this.id_camino = id_camino;
		this.origen = origen;
		this.destino = destino;
		this.costo = costo;
		this.duracion = duracion;
		this.distancia = distancia;
	}
	
	public Estacion getOrgien() {
		return this.origen;
	}
	public Estacion getDestino() {
		return this.destino;
	}

	public Double getCosto() {
		return costo;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public Integer getDistancia() {
		return distancia;
	}
	public String getId_camino() {
		return id_camino;
	}
	
	public static void setUltimo_id(Integer id) {
		ultimo_id=id;
	}

	public Estacion getOrigen() {
		return origen;
	}

	public List<Trayecto> getCombinacion() {
		return combinacion;
	}

	public void asignarUltimoId() {
		this.id_camino = "PAT"+(++ultimo_id).toString();		
	}

	public void setOrigen(Estacion estacion) {
		this.origen = estacion;		
	}
	
	public void setDestino(Estacion estacion) {
		this.destino = estacion;
	}
	
	
}
