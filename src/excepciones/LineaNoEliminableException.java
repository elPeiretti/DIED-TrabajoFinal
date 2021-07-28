package excepciones;

public class LineaNoEliminableException extends Exception {

	public String errores;
	
	public LineaNoEliminableException(String errores) {
		super("La linea no es elminable.");
		this.errores = "El trayecto no puede ser eliminado porque: \n" + errores;
	}
	
}
