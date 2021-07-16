package gestores;

import java.util.ArrayList;
import excepciones.DatosDeEstacionIncorrectosException;

public class GestorValidaciones {
	
	public static void validarEstacion(String nombre, String h_apertura, String h_cierre) throws DatosDeEstacionIncorrectosException {
		String errores="";
		
		if(nombre.isEmpty()) {
			errores+="Campo 'nombre' Incompleto\n";
		}
		if(h_apertura.isEmpty()) {
			errores+="Campo 'horario apertura' incompleto\n";
		}
		else if(!h_apertura.matches("\\d\\d:\\d\\d")) {
			errores+="Campo 'horario apertura' posee formato invalido (HH:MM)\n";
		}
		if(h_cierre.isEmpty()) {
			errores+="Campo 'horario cierre' incompleto\n";
		}
		else if(!h_cierre.matches("\\d\\d:\\d\\d")) {
			errores+="Campo 'horario cierre' posee formato invalido (HH:MM)";
		}
		
		if(!errores.isEmpty())
			throw new DatosDeEstacionIncorrectosException(errores);
	}
}
