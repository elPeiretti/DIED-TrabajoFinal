package dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TareaDeMantenimiento {

	private static Integer ultimo_id=0;
	private String id_tarea;
	private LocalDate fecha_inicio;
	private LocalDate fecha_fin;
	private String observaciones;
	
	public TareaDeMantenimiento() {
		this.id_tarea = "MANT"+(++ultimo_id).toString();
		this.fecha_inicio = LocalDate.now();
	}
	
	
	
	public TareaDeMantenimiento(String id_tarea, String fecha_inicio, String fecha_fin, String obs) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		this.id_tarea = id_tarea;
		this.fecha_inicio = LocalDate.parse(fecha_inicio, dtf);
		this.fecha_fin =  LocalDate.parse(fecha_fin, dtf);
		this.observaciones = obs;
	}


	public void finalizar(String observaciones) {
		this.fecha_fin = LocalDate.now();
		this.observaciones = observaciones;
	}
	
	public static void setUltimo_id(Integer id) {
		ultimo_id=id;
	}

	public String getId_tarea() {
		return id_tarea;
	}

	public LocalDate getFecha_inicio() {
		return fecha_inicio;
	}

	public LocalDate getFecha_fin() {
		return fecha_fin;
	}

	public String getObservaciones() {
		return observaciones;
	}
	
}
