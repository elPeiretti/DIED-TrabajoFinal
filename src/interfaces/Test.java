package interfaces;

import dominio.EstadoEstacion;
import gestores.GestorJDBC;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(EstadoEstacion.EN_MANTENIMIENTO.toString());
		VentanaEjemplo vp = new VentanaEjemplo();
	}

}
