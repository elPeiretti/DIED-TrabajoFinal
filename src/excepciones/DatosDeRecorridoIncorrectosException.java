package excepciones;

public class DatosDeRecorridoIncorrectosException extends Exception {

	private static final long serialVersionUID = -4722930223941232872L;
	
	public String errores;
	
	public DatosDeRecorridoIncorrectosException (String errores) {
		super("Los datos de recorrido son invalidos.");
		this.errores = errores;
	}
	
}
