package gestores;

import excepciones.*;

public class GestorValidaciones {
	
	public static void validarEstacion(String nombre, String h_apertura, String h_cierre) throws DatosDeEstacionIncorrectosException {
		String errores="";
		
		if(nombre.isEmpty()) {
			errores+="Campo 'nombre' Incompleto\n";
		}
		errores=validarHorariosEstacion(h_apertura,h_cierre,errores);
		
		if(!errores.isEmpty())
			throw new DatosDeEstacionIncorrectosException(errores);
	}
	public static void validarHorariosEstacion(String h_apertura, String h_cierre) throws DatosDeEstacionIncorrectosException {
		String errores = "";
		errores = validarHorariosEstacion(h_apertura,h_cierre,errores);
		if(!errores.isEmpty()) {
			throw new DatosDeEstacionIncorrectosException(errores);
		}
	}
	
	private static String validarHorariosEstacion(String h_apertura, String h_cierre, String errores) {
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
		return errores;
		
	}
	
	public static void validarLineaDeTransporte(String nombre, String color) throws DatosDeLineaDeTransporteIncorrectosException {
		String errores = "";
		if(nombre.isEmpty())
			errores+="Campo 'nombre' Incompleto\n";
		if(color.isEmpty())
			errores+="Campo 'color' Incompleto\n";
		
		if(!errores.isEmpty())
			throw new DatosDeLineaDeTransporteIncorrectosException(errores);
	}
	public static void validarTrayecto(String origen, String destino, String costo) throws DatosDeTrayectoIncorrectosException {
		String errores="";
		
		if(origen.equals(destino)) {
			errores+="La estacion de destino no puede ser la misma que el origen\n";
		}
		
		if(costo.isEmpty()) {
			errores+="El campo 'costo' no puede estar vacio\n";
		}
		else if(!costo.matches("(\\d)+((.|,)(\\d)+)?")) {
			errores+="El campo 'costo' posee un formato invalido";
		}
		
		if(!errores.isEmpty())
			throw new DatosDeTrayectoIncorrectosException(errores);
	}

}
