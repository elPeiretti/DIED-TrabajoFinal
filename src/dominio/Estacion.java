package dominio;

import java.util.List;

public class Estacion {
	
	private String id_estacion;
	private String nombre;
	private String horario_apertura;
	private String horario_cierre;
	private EstadoEstacion estado;
	private List<TareaDeMantenimiento> mantenimientos;
	
}
