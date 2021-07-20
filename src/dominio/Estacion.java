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
	
	public Estacion(String nombre, String horario_apertura, String horario_cierre, EstadoEstacion estado) {
		this.nombre = nombre;
		this.horario_apertura = horario_apertura;
		this.horario_cierre = horario_cierre;
		this.estado = estado;
		this.mantenimientos = new ArrayList<TareaDeMantenimiento>();
	}

	public Estacion(String id_estacion, String nombre, String horario_apertura, String horario_cierre, EstadoEstacion estado) {
		this.id_estacion = id_estacion;
		this.nombre = nombre;
		this.horario_apertura = horario_apertura;
		this.horario_cierre = horario_cierre;
		this.estado = estado;
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

	public void setNombre(String nombre) {
		this.nombre = nombre;		
	}

	public void setHorario_apertura(String horario_apertura) {
		this.horario_apertura = horario_apertura;
	}

	public void setHorario_cierre(String horario_cierre) {
		this.horario_cierre = horario_cierre;
	}

	public void setEstado(EstadoEstacion estado) {
		this.estado = estado;
	}

	public static void setUltimo_id(Integer id) {
		ultimo_id=id;
	}

	public String getId_estacion() {
		return id_estacion;
	}

	public String getHorario_apertura() {
		return horario_apertura;
	}

	public String getHorario_cierre() {
		return horario_cierre;
	}

	public EstadoEstacion getEstado() {
		return estado;
	}

	public List<TareaDeMantenimiento> getMantenimientos() {
		return mantenimientos;
	}
	
	
}
