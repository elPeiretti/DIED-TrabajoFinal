package excepciones;

public class LineaNoEliminableException extends Exception {

	private static final long serialVersionUID = 2339531011485705865L;
	
	public String errores;
	
	public LineaNoEliminableException(String errores) {
		super("La linea no es elminable.");
		this.errores = "El trayecto no puede ser eliminado porque: \n" + errores;
	}
	
}
