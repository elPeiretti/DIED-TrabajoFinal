package excepciones;

public class DatosDeRecorridoIncorrectosException extends Exception {

	public String errores;
	
	public DatosDeRecorridoIncorrectosException (String errores) {
		super("Los datos de recorrido son invalidos.");
		this.errores = errores;
	}
	
}
