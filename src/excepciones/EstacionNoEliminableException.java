package excepciones;

public class EstacionNoEliminableException extends Exception {

	public String errores;
	
	public EstacionNoEliminableException(String errores) {
		super("La estacion seleccionada no es elminable.");
		this.errores = "La estacion no puede ser eliminada porque: \n" + errores;
	}
	
	
}
