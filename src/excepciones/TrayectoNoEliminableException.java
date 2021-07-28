package excepciones;

public class TrayectoNoEliminableException extends Exception {

	public String errores;
	
	public TrayectoNoEliminableException(String errores) {
		super("El trayecto no es elminable.");
		this.errores = "El trayecto no puede ser eliminado porque: \n" + errores;
	}
	
}
