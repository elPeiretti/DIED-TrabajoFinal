package dominio;

import java.time.LocalDate;

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
	
	public void finalizar(String observaciones) {
		this.fecha_fin = LocalDate.now();
		this.observaciones = observaciones;
	}
}
