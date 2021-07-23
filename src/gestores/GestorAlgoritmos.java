package gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	public static List<Estacion> calcularPageRank() {
		// TODO Auto-generated method stub
		return new ArrayList<Estacion>() {};
	}

	public static Estacion calcularProximoMantenimiento(List<Estacion> estaciones) throws NoHayDatosDeEstacionesException {
		
		if(estaciones.isEmpty()) throw new NoHayDatosDeEstacionesException();
		
		Estacion prox =  estaciones.get(0);
		
		for(Estacion est : estaciones) {
			if(est.getUltimoMantenimiento() == null || prox.getUltimoMantenimiento() == null) continue;
			LocalDate fecha_est = est.getUltimoMantenimiento().getFecha_fin();
			LocalDate fecha_prox = prox.getUltimoMantenimiento().getFecha_fin();
			if((fecha_est != null && fecha_prox != null && fecha_prox.isAfter(fecha_est)) || (fecha_est !=  null && fecha_prox == null)) {
				prox = est;
			}
		}
		
		return prox;
	}

	
}
