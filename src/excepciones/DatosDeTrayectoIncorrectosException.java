package excepciones;


public class DatosDeTrayectoIncorrectosException extends Exception{
	public String errores;
	public DatosDeTrayectoIncorrectosException(String errores) {
		super("Los datos brindados para el trayecto son invalidos");
		this.errores=errores;
	}

}
