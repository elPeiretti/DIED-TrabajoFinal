package gestores;

import java.util.List;
import java.util.Vector;

import dominio.*;

public class GestorEntidades {
	
	
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

	public static List<Camino> getTrayectosDesdeHasta(Estacion origen, Estacion destino) {
		
		//todos los trayectos
		//List<Trayecto> = GestorJDBC.buscarTrayectos();
		
		// dfs para encontrar todos los caminos de origen a destino
		// TODO
		
		return null;
	}

	public static Cliente crearCliente(String nombre, String email) {
		return new Cliente(nombre, email);
	}

	
	public static Trayecto crearTrayecto(String id_trayecto, Integer dist, Integer duracion, Integer capacidad, Integer estado, Double costo, Estacion origen, Estacion destino) {
		
		return new Trayecto(id_trayecto,dist,duracion,capacidad,costo,origen,destino, estado==1? EstadoTrayecto.INACTIVO : EstadoTrayecto.ACTIVO);
		
	}

	public static LineaDeTransporte crearLineaDeTransporte(String id_linea, String nombre, String color, Integer estado) {
		return new LineaDeTransporte(id_linea,nombre,color, estado==1? EstadoLinea.NO_ACTIVA : EstadoLinea.ACTIVA);
	}

	public static Estacion crearEstacion(String id_estacion, String nombre, String horario_apertura, String horario_cierre, Integer estado) {
		return new Estacion(id_estacion,nombre,horario_apertura,horario_cierre, estado==1? EstadoEstacion.EN_MANTENIMIENTO : EstadoEstacion.OPERATIVA);
	}
}
