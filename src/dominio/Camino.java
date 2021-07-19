package dominio;

import java.util.List;

public class Camino {

	private static Integer ultimo_id=0;
	private String id_camino;
	private Estacion origen;
	private Estacion destino;
	private List<Trayecto> combinacion;
	
	public Camino(List<Trayecto> combinacion, Estacion origen, Estacion destino) {
		this.id_camino = "PATH"+(++ultimo_id).toString();
		this.combinacion = combinacion;
		this.origen = origen;
		this.destino = destino;
	}
	
	public Estacion getOrgien() {
		return this.origen;
	}
	public Estacion getDestino() {
		return this.destino;
	}
}
