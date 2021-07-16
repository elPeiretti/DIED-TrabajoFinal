package excepciones;

import java.util.ArrayList;

public class DatosDeEstacionIncorrectosException extends Exception {
	public String errores;
	public DatosDeEstacionIncorrectosException(String errores) {
		super("Los datos brindados para la estacion son invalidos");
		this.errores = errores;
	}
}
