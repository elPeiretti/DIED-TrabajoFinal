package excepciones;



public class NoHayDatosDeEstacionesException extends Exception {

	public NoHayDatosDeEstacionesException () {
		super("No existen estaciones registradas en el sistema.");
	}
	
	
}
