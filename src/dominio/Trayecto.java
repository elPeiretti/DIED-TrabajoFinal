package dominio;

public class Trayecto {

	private static Integer ultimo_id=0;
	private String id_trayecto;
	private Double distancia;
	private Integer duracion;
	private Integer cant_max_pasajeros;
	private EstadoTrayecto estado;
	private Double costo;
	private Estacion origen;
	private Estacion destino;
	
	public Trayecto(Double distancia, Integer duracion, Integer cant_max_pasajeros, Double costo, Estacion origen, Estacion destino) {
		this.id_trayecto = "TRAY"+(++ultimo_id).toString();
		this.distancia = distancia;
		this.duracion = duracion;
		this.cant_max_pasajeros = cant_max_pasajeros;
		this.estado = EstadoTrayecto.ACTIVO;
		this.costo = costo;
		this.origen = origen;
		this.destino = destino;
	}
	
}
