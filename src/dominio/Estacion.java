package dominio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
		this.id_estacion = "EST" + ++ultimo_id;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_estacion == null) ? 0 : id_estacion.hashCode());
		return result;
	}
	
	public void agregarMantenimiento(TareaDeMantenimiento tarea) {
		mantenimientos.add(tarea);
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estacion other = (Estacion) obj;
		if (id_estacion == null) {
			if (other.id_estacion != null)
				return false;
		} else if (!id_estacion.equals(other.id_estacion))
			return false;
		return true;
	}

	public TareaDeMantenimiento getUltimoMantenimiento() {
		return this.mantenimientos.isEmpty() ? null : mantenimientos.get(this.mantenimientos.size()-1);
	}
	
		
	public static class PriorityMantenimientoComparator implements Comparator<Estacion> {
		
		@Override
		public int compare(Estacion est1, Estacion est2) {
			//Positivo si est1 > est2
			//Cero si son iguales
			//Negativo si est2 > est1
			//Si es negativo no cambia dentro de la queue
			
			if(est1.mantenimientos.isEmpty()) return -1;
			
			if(est2.mantenimientos.isEmpty()) return 1;
			
			if(est1.getUltimoMantenimiento().getFecha_fin() == null && est2.getUltimoMantenimiento().getFecha_fin() == null)
				return 0;
			
			if(est1.getUltimoMantenimiento().getFecha_fin() != null && est2.getUltimoMantenimiento().getFecha_fin() == null)
				return -1;
			
			if(est1.getUltimoMantenimiento().getFecha_fin() == null && est2.getUltimoMantenimiento().getFecha_fin() != null)
				return 1;
			
			return est1.getUltimoMantenimiento().getFecha_fin().compareTo(est2.getUltimoMantenimiento().getFecha_fin());
			
		}
		
	}
	
}
