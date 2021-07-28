package excepciones;

public class RecorridoLineaExistenteException extends Exception {
	
	public RecorridoLineaExistenteException() {
		super("La linea ya tiene un recorrido registrado.");
	}
	
}
