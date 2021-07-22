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
	
	public LineaDeTransporte(String id_linea, String nombre, String color, EstadoLinea estado) {
		this.id_linea = id_linea;
		this.nombre = nombre;
		this.color = color;
		this.estado = estado;
		this.recorrido = new ArrayList<Trayecto>();
	}

	public Vector<String> asVector(){
		Vector<String> data = new Vector<String>();
		data.add(id_linea);
		data.add(nombre);
		data.add(color);
		data.add(estado.toString());
		return data;
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
	
	public static void setUltimo_id(Integer id) {
		ultimo_id=id;
	}

	public String getId_linea() {
		return id_linea;
	}

	public String getNombre() {
		return nombre;
	}

	public String getColor() {
		return color;
	}

	public EstadoLinea getEstado() {
		return estado;
	}

	public List<Trayecto> getRecorrido() {
		return recorrido;
	}
	
}
