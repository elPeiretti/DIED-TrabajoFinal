package excepciones;

public class DatosDeLineaDeTransporteIncorrectosException extends Exception{

	private static final long serialVersionUID = -2393013826879775264L;
	
	public String errores;
	public DatosDeLineaDeTransporteIncorrectosException(String errores) {
		super("Los datos brindados para la estacion son invalidos");
		this.errores=errores;
	}
}
