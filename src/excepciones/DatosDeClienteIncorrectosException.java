package excepciones;

public class DatosDeClienteIncorrectosException extends Exception {
	public String errores;
	public DatosDeClienteIncorrectosException(String errores) {
		super("Los datos brindados para el cliente son invalidos");
		this.errores = errores;
	}
}
