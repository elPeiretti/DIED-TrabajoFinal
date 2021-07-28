package dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	
	public Boleto(String id_boleto, String fecha_venta, Camino camino, Cliente cliente, Estacion origen, Estacion destino) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.id_boleto = id_boleto;
		this.fecha_venta = LocalDate.parse(fecha_venta, dtf);
		this.cliente = cliente;
		this.origen = origen;
		this.destino = destino;
		this.camino = camino;
	}
	
	public static void setUltimo_id(Integer id) {
		ultimo_id=id;
	}

	public String getId_boleto() {
		return id_boleto;
	}

	public LocalDate getFecha_venta() {
		return fecha_venta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Estacion getOrigen() {
		return origen;
	}

	public Estacion getDestino() {
		return destino;
	}

	public Camino getCamino() {
		return camino;
	}

	public void setOrigen(Estacion estacion) {
		this.origen = estacion;
	}

	public void setDestino(Estacion estacion) {
		this.destino = estacion;
	}
	
	
}
