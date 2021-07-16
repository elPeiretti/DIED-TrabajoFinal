package excepciones;

public class DatosDeLineaDeTransporteIncorrectosException extends Exception{
	public String errores;
	public DatosDeLineaDeTransporteIncorrectosException(String errores) {
		super("Los datos brindados para la estacion son invalidos");
		this.errores=errores;
	}
}
