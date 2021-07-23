package gestores;

<<<<<<< HEAD
public class GestorAlgoritmos {

	
=======
import java.util.ArrayList;
import java.util.List;

import dominio.Camino;
import dominio.Estacion;
import dominio.EstadoTrayecto;
import dominio.Trayecto;

public class GestorAlgoritmos {
	
public static List<Camino> getRecorridosDesdeHasta(Estacion origen, Estacion destino) {
		
		List<List<Trayecto>> caminos = new ArrayList<List<Trayecto>>();
		getRecorridosDesdeHastaAux(origen, destino, caminos, new ArrayList<Trayecto>());
		
		List<Camino> recorridos = new ArrayList<Camino>();
		for(List<Trayecto> c : caminos) {
			Object data[] = calcularCostoDuracionYLongitud(c);
			Camino cam = new Camino(c,origen,destino,(Double)data[0],(Integer)data[1],(Integer)data[2]);
			recorridos.add(cam);
		}
		return recorridos;
	}
	
	private static Object[] calcularCostoDuracionYLongitud(List<Trayecto> camino) {
		Double costo=0d;
		Integer dist = 0;
		Integer durac = 0;
		for(Trayecto t : camino) {
			costo+=t.getCosto();
			dist+=t.getDistancia();
			durac+=t.getDuracion();
		}
		return new Object[] {costo,durac,dist};
	}
	
	private static void getRecorridosDesdeHastaAux(Estacion origen, Estacion destino, List<List<Trayecto>> caminos, List<Trayecto> camino) {
		
		if(origen.equals(destino)) {
			caminos.add(camino);
			return;
		}
		
		List<Trayecto> trayectos = GestorJDBC.buscarTrayecto("","",origen.getId_estacion(),"", EstadoTrayecto.ACTIVO);
	
		for(Trayecto t : trayectos) {
			List<Trayecto> copia = new ArrayList<Trayecto>(camino);
			if(!copia.contains(t)) {
				copia.add(t);
				getRecorridosDesdeHastaAux(t.getDestino(),destino,caminos,copia);
			}
		}
	}

	public static Integer calcularFlujoMaximo(Estacion origen, Estacion destino) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static List<Estacion> calcularPageRank() {
		// TODO Auto-generated method stub
		return new ArrayList<Estacion>() {};
	}

	public static Estacion calcularProximoMantenimiento(List<Estacion> estaciones) {
		// TODO Auto-generated method stub
		return null;
	}
>>>>>>> 21b4037cd1b718db62028bbce09e97b2d901d67f
	
}
