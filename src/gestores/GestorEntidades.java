package gestores;

import java.util.List;
import java.util.Vector;
import dominio.*;

public class GestorEntidades {
	
	public static Cliente crearCliente(String nombre, String email) {
		return new Cliente(nombre, email);
	}
	
	public static Trayecto crearTrayecto(Estacion origen, Estacion destino, Integer dist, Integer capacidad, Integer duracion, String costo) {
		return new Trayecto(dist,duracion,capacidad,Double.parseDouble(costo),origen,destino);
	}
	
	public static Estacion crearEstacion(String nombre, String horario_apertura, String horario_cierre, EstadoEstacion estado) {
		return new Estacion(nombre,horario_apertura,horario_cierre,estado);
	}
	
	public static void actualizarLinea(LineaDeTransporte linea, String nombre, String color, EstadoLinea estado) {
		linea.setNombre(nombre);
		linea.setColor(color);
		linea.setEstado(estado);
	}
	
	public static LineaDeTransporte crearLineaDeTransporte(String nombre, String color, EstadoLinea estado) {
		return new LineaDeTransporte(nombre,color,estado);
	}

	public static void actualizarEstacion(Estacion e,String nombre, String apertura, String cierre, EstadoEstacion estado) {
		e.setNombre(nombre);
		e.setHorario_apertura(apertura);
		e.setHorario_cierre(cierre);
		e.setEstado(estado);
	}

	public static Vector<String> getVectorDeDatosDeCamino(Camino c) {
		Vector<String> data = new Vector<String>();
		data.add(c.getDuracion().toString());
		data.add(c.getDistancia().toString());
		data.add(c.getCosto().toString());
		return data;
	}

	public static Cliente crearCliente(String id_cliente, String nombre, String email) {
		return new Cliente(id_cliente, nombre, email);
	}
	
	public static Trayecto crearTrayecto(String id_trayecto, Integer dist, Integer duracion, Integer capacidad, String estado, Double costo, Estacion origen, Estacion destino) {
		
		return new Trayecto(id_trayecto,dist,duracion,capacidad,costo,origen,destino, estado.equals(EstadoTrayecto.INACTIVO.toString())? EstadoTrayecto.INACTIVO : EstadoTrayecto.ACTIVO);
		
	}

	public static LineaDeTransporte crearLineaDeTransporte(String id_linea, String nombre, String color, String estado) {
		return new LineaDeTransporte(id_linea,nombre,color, estado.equals(EstadoLinea.NO_ACTIVA.toString())? EstadoLinea.NO_ACTIVA : EstadoLinea.ACTIVA);
	}

	public static Estacion crearEstacion(String id_estacion, String nombre, String horario_apertura, String horario_cierre, String estado) {
		return new Estacion(id_estacion,nombre,horario_apertura,horario_cierre, estado.equals(EstadoEstacion.EN_MANTENIMIENTO.toString())? EstadoEstacion.EN_MANTENIMIENTO : EstadoEstacion.OPERATIVA);
	}

	public static Boleto crearBoleto(Cliente c, Estacion origen, Estacion destino, Camino camino_seleccionado) {
		return new Boleto(c, origen, destino, camino_seleccionado);
	}

	public static void actualizarEstadoTrayectosQueIncluyen(Estacion estacion) {
		//todos los trayectos activos que lleguen o salgan de 'estacion', deben inhabilitarse
		List<Trayecto> trayectos = GestorJDBC.buscarTrayecto("","",estacion.getId_estacion(),"", EstadoTrayecto.ACTIVO); //salientes
		trayectos.addAll(GestorJDBC.buscarTrayecto("","","",estacion.getId_estacion(), EstadoTrayecto.ACTIVO));  //entrantes
		
		for(Trayecto t : trayectos) {
			t.setEstado(EstadoTrayecto.INACTIVO);
			GestorJDBC.actualizarTrayecto(t);
		}
		
	}

	public static TareaDeMantenimiento crearTareaDeMantenimiento() {
		return new TareaDeMantenimiento();
	}

}
