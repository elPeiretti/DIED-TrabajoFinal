package excepciones;


public class DatosDeEstacionIncorrectosException extends Exception {

	private static final long serialVersionUID = -1775161793952873057L;
	
	public String errores;
	public DatosDeEstacionIncorrectosException(String errores) {
		super("Los datos brindados para la estacion son invalidos");
		this.errores = errores;
	}
}
