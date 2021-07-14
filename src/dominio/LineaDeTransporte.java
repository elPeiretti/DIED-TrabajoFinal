package dominio;

import java.util.List;

public class LineaDeTransporte {
	
	private String id_linea;
	private String nombre;
	private String color;
	private  EstadoLinea estado;
	private List<Trayecto> recorrido;
}
