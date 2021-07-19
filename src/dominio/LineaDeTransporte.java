package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class LineaDeTransporte {

	private static Integer ultimo_id=0;
	private String id_linea;
	private String nombre;
	private String color;
	private EstadoLinea estado;
	private List<Trayecto> recorrido;
	
	public LineaDeTransporte(String nombre, String color,EstadoLinea estado) {
		this.id_linea = "LIN"+(++ultimo_id).toString();
		this.nombre = nombre;
		this.color = color;
		this.estado = estado;
		this.recorrido = new ArrayList<Trayecto>();
	}
	
	public String getId() {
		return this.id_linea;
	}
	
	public String toString() {
		return nombre + ", "+ color + ", " + estado.toString();
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setEstado(EstadoLinea estado) {
		this.estado=estado;
	}
}
