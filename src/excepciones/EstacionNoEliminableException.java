package excepciones;

public class EstacionNoEliminableException extends Exception {

	private static final long serialVersionUID = 8922842155668327699L;
	
	public String errores;
	
	public EstacionNoEliminableException(String errores) {
		super("La estacion seleccionada no es elminable.");
		this.errores = "La estacion no puede ser eliminada porque: \n" + errores;
	}
	
	
}
