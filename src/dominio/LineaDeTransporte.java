package dominio;

import java.util.ArrayList;
import java.util.List;

public class LineaDeTransporte {

	private static Integer ultimo_id=0;
	private String id_linea;
	private String nombre;
	private String color;
	private EstadoLinea estado;
	private List<Trayecto> recorrido;
	
	public LineaDeTransporte(String nombre, String color) {
		this.id_linea = "LIN"+(++ultimo_id).toString();
		this.nombre = nombre;
		this.color = color;
		this.estado = EstadoLinea.ACTIVA;
		this.recorrido = new ArrayList<Trayecto>();
	}
}
