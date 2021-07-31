package excepciones;



public class NoHayDatosDeEstacionesException extends Exception {

	private static final long serialVersionUID = 2000332165158658933L;

	public NoHayDatosDeEstacionesException () {
		super("No existen estaciones registradas en el sistema.");
	}
	
	
}
