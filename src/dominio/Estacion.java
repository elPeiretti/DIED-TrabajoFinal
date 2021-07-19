package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Estacion {

	private static Integer ultimo_id=0;
	private String id_estacion;
	private String nombre;
	private String horario_apertura;
	private String horario_cierre;
	private EstadoEstacion estado;
	private List<TareaDeMantenimiento> mantenimientos;
	
	public Estacion(String nombre, String horario_apertura, String horario_cierre) {
		this.id_estacion = "EST"+(++ultimo_id).toString();
		this.nombre = nombre;
		this.horario_apertura = horario_apertura;
		this.horario_cierre = horario_cierre;
		this.estado = EstadoEstacion.OPERATIVA;
		this.mantenimientos = new ArrayList<TareaDeMantenimiento>();
	}
	
	public void cambiarEstado() {
		
		if(this.estado == EstadoEstacion.OPERATIVA) {
			TareaDeMantenimiento tarea = new TareaDeMantenimiento();
			this.mantenimientos.add(tarea);
			this.estado = EstadoEstacion.EN_MANTENIMIENTO;
			return;
		}
	}

	public String getNombre() {
		return nombre;
	}
	
	public Vector<String> asVector(){
		Vector<String> data = new Vector<String>();
		data.add(id_estacion);
		data.add(nombre);
		data.add(estado.toString());
		data.add(horario_apertura);
		data.add(horario_cierre);
		return data;
	}
	
	public String toString() {
		return this.nombre;
	}
}
