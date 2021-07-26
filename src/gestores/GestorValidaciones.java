package gestores;

import dominio.Estacion;
import dominio.EstadoEstacion;
import dominio.EstadoLinea;
import excepciones.*;

public class GestorValidaciones {
	
	public static void validarEstacion(String nombre, String h_apertura, String h_cierre, EstadoEstacion estado) throws DatosDeEstacionIncorrectosException {
		String errores="";
		
		if(estado == null)
			errores+="Campo 'Estado' Incompleto\n";
		
		if(estado == EstadoEstacion.EN_MANTENIMIENTO)
			errores+="El Campo 'Estado' debe estar en 'OPERTIVA' para la creacion\n";
		
		if(nombre.isEmpty()) {
			errores+="Campo 'nombre' Incompleto\n";
		}
		errores=validarHorariosEstacion(h_apertura,h_cierre,errores);
				
		if(!errores.isEmpty())
			throw new DatosDeEstacionIncorrectosException(errores);
	}
	
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
	
	public static void validarFormatoHorariosEstacion(String h_apertura, String h_cierre) throws DatosDeEstacionIncorrectosException {
		String errores="";
		if(!h_apertura.matches("(\\d\\d:\\d\\d)?")) {
			errores+="Campo 'horario apertura' posee formato invalido (HH:MM)\n";
		}
		if(!h_cierre.matches("(\\d\\d:\\d\\d)?")) {
			errores+="Campo 'horario cierre' posee formato invalido (HH:MM)";
		}
		
		if(!errores.isEmpty())
			throw new DatosDeEstacionIncorrectosException(errores);
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
	
	public static void validarLineaDeTransporte(String nombre, String color, EstadoLinea estado) throws DatosDeLineaDeTransporteIncorrectosException {
		String errores = "";
		if(nombre.isEmpty())
			errores+="Campo 'nombre' Incompleto\n";
		if(color.isEmpty())
			errores+="Campo 'color' Incompleto\n";
		if(estado == null)
			errores+="Campo 'estado' Incompleto";
		
		
		if(!errores.isEmpty())
			throw new DatosDeLineaDeTransporteIncorrectosException(errores);
	}
	public static void validarTrayecto(Estacion origen, Estacion destino, String costo) throws DatosDeTrayectoIncorrectosException {
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
	public static void validarCliente(String nombre, String email) throws DatosDeClienteIncorrectosException {
		String errores="";
		if(nombre.isEmpty())
			errores+="El campo 'nombre' no puede estar vacio\n";
		if(email.isEmpty())
			errores+="El campo 'E-mail' no puede estar vacio\n";
		else if(!email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			errores+="El campo 'E-mail' posee un formato invalido\n";
		}
		
		if(!errores.isEmpty())
			throw new DatosDeClienteIncorrectosException(errores);
	}

	public static void validarEstaciones(Estacion origen, Estacion destino) throws DatosDeRecorridoIncorrectosException {
		
		String errores = "";
		
		if(origen == null) errores += "La estacion origen no puede estar vacia.\n";
		
		if(destino == null) errores += "La estacion destino no puede estar vacia.\n";
		
		if(origen.equals(destino)) errores += "La estacion origen no puede ser la misma que la de destino.\n";
		
		if(!errores.isEmpty()) throw new DatosDeRecorridoIncorrectosException(errores);
		
	}

	public static void validarEliminacionEstacion(Estacion estacion) {
		
		
		
	}

}
