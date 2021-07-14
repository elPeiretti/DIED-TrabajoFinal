package dominio;

import java.time.LocalDate;

public class Boleto {
	
	private static Integer ultimo_id=0;
	private String id_boleto;
	private LocalDate fecha_venta;
	private Cliente cliente;
	private Estacion origen;
	private Estacion destino;
	private Camino camino;
	
	public Boleto(Cliente cliente, Estacion origen, Estacion destino, Camino camino) {
		this.id_boleto = "BOL"+(++ultimo_id).toString();
		this.fecha_venta = LocalDate.now();
		this.cliente = cliente;
		this.origen = origen;
		this.destino = destino;
		this.camino = camino;
	}
	
}
