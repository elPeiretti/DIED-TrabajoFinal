package excepciones;

public class RecorridoLineaExistenteException extends Exception {
	
	private static final long serialVersionUID = -8575789660700495570L;

	public RecorridoLineaExistenteException() {
		super("La linea ya tiene un recorrido registrado.");
	}
	
}
