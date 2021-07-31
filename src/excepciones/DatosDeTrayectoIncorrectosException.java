package excepciones;


public class DatosDeTrayectoIncorrectosException extends Exception{

	private static final long serialVersionUID = 3753286819543352480L;
	
	public String errores;
	public DatosDeTrayectoIncorrectosException(String errores) {
		super("Los datos brindados para el trayecto son invalidos");
		this.errores=errores;
	}

}
