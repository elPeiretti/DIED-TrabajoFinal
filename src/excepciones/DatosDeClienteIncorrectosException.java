package excepciones;

public class DatosDeClienteIncorrectosException extends Exception {
	
	private static final long serialVersionUID = -511792458656604142L;
	
	public String errores;
	public DatosDeClienteIncorrectosException(String errores) {
		super("Los datos brindados para el cliente son invalidos.");
		this.errores = errores;
	}
}
