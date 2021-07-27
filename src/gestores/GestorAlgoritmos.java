package gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

import dominio.Camino;
import dominio.Estacion;
import dominio.EstadoTrayecto;
import dominio.Trayecto;
import excepciones.NoHayDatosDeEstacionesException;

public class GestorAlgoritmos {
	
public static List<Camino> getRecorridosDesdeHasta(Estacion origen, Estacion destino) {
		
		List<List<Trayecto>> caminos = new ArrayList<List<Trayecto>>();
		getRecorridosDesdeHastaAux(origen, destino, caminos, new ArrayList<Trayecto>());
		
		List<Camino> recorridos = new ArrayList<Camino>();
		for(List<Trayecto> c : caminos) {
			Object data[] = calcularCostoDuracionYLongitud(c);
			Camino cam = new Camino(c,origen,destino,data);
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
		HashMap <String, Integer> arcos = incializarArcos();
		Integer flujo_maximo = 0;
		List<List<Trayecto>> caminos = new ArrayList<List<Trayecto>>();
		getRecorridosDesdeHastaAux(origen, destino, caminos, new ArrayList<Trayecto>());
		
		for (List<Trayecto> c : caminos) {
			if(verficarCamino(c,arcos)) {
				flujo_maximo += getMininoYReducir(c, arcos);
			}
		}		
		
		return flujo_maximo;
	}
	
	private static Boolean verficarCamino(List<Trayecto> camino, HashMap<String, Integer> arcos) {
		
		Boolean correcto = true;
		
		for(Trayecto t : camino) {
			if(arcos.get(t.getId_trayecto()) == 0) {
				correcto = false;
			}
		}
		
		return correcto;
		
	}

	private static Integer getMininoYReducir(List<Trayecto> camino, HashMap<String, Integer> arcos) {

		Integer minimo = -1;
		
		for(Trayecto t : camino) {
			if(minimo == -1) {
				minimo = arcos.get(t.getId_trayecto());
			} else {
				if(minimo > arcos.get(t.getId_trayecto())) {
					minimo = arcos.get(t.getId_trayecto());
				}
			}
		}
		
		for(Trayecto t : camino) {
			arcos.put(t.getId_trayecto(), arcos.get(t.getId_trayecto()) - minimo);
		}
		
		return minimo;
		
	}

	private static HashMap<String, Integer> incializarArcos() {
		List<Trayecto> trayectos = GestorJDBC.buscarTrayecto("", "", "", "", null);
		HashMap <String, Integer> arcos = new HashMap<String, Integer>();
		
		for(Trayecto t : trayectos) {
			arcos.put(t.getId_trayecto(), t.getCant_max_pasajeros());
		}
		
		return arcos;
	}

	public static List<Estacion> calcularPageRank() throws NoHayDatosDeEstacionesException {
		
		HashMap<String, Double> pageRanks = new HashMap<String, Double>();
		HashMap<String, List<Estacion>> estacionesEntrantes  = new HashMap<String, List<Estacion>>();
		HashMap<String, Integer> enlacesSalientes = new HashMap<String, Integer>();
		
		List<Estacion> estaciones = GestorJDBC.buscarEstacion("", "", "", "", null);
		
		if(estaciones.isEmpty()) throw new NoHayDatosDeEstacionesException();
		
		Double variacion;
		Double probabilidad = 0.85;
		
		for(Estacion est : estaciones) {
			pageRanks.put(est.getId_estacion(), 1.0);
			enlacesSalientes.put(est.getId_estacion(),GestorJDBC.buscarTrayecto("", "", est.getId_estacion(), "", null).size());
			estacionesEntrantes.put(est.getId_estacion(), 
					GestorJDBC.buscarTrayecto("", "", "", est.getId_estacion(), null).stream()
					.map((Trayecto t) -> {return t.getOrigen();}).collect(Collectors.toList()));
		}
				
		do {
			variacion = 0d;
			HashMap<String, Double> auxPageRanks  = new HashMap<String,Double>();
			for(Estacion est : estaciones) {
				Double nuevoPR = 1-probabilidad;
				
				for(Estacion t : estacionesEntrantes.get(est.getId_estacion())) {
					nuevoPR += probabilidad * pageRanks.get(t.getId_estacion())/enlacesSalientes.get(t.getId_estacion());
				}
				Double auxVariacion = Math.abs(pageRanks.get(est.getId_estacion()) - nuevoPR);
				if(auxVariacion > variacion) variacion = auxVariacion;
				auxPageRanks.put(est.getId_estacion(), nuevoPR);
			}
			
			pageRanks = auxPageRanks;
			
		} while(variacion > 0.01);
		
		estaciones.sort(new GestorAlgoritmos.EstacionPageRankComparator(pageRanks));
		
		return estaciones;
	}

	public static List<Estacion> calcularPrioridadMantenimiento(List<Estacion> estaciones) throws NoHayDatosDeEstacionesException {
		
		if(estaciones.isEmpty()) throw new NoHayDatosDeEstacionesException();
		
		PriorityQueue<Estacion> monticulo = new PriorityQueue<Estacion>(new Estacion.PriorityMantenimientoComparator()); 
		
		monticulo.addAll(estaciones);
		
		List<Estacion> resultado = new ArrayList<Estacion>();
		
		int tamano = monticulo.size();
		
		for(int i = 0; i < tamano; i++) {
			resultado.add(monticulo.remove());
		}
		
		return resultado;
	}

	private static class EstacionPageRankComparator implements Comparator<Estacion> {

		private HashMap<String,Double> pageRanks;
		
		private EstacionPageRankComparator (HashMap<String,Double> pageRanks) {
			this.pageRanks = pageRanks;
		}
		
		public int compare(Estacion e1, Estacion e2) {
			return pageRanks.get(e1.getId_estacion()).compareTo(pageRanks.get(e2.getId_estacion()))*-1;
		}
		
	}
	
}
