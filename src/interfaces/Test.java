package interfaces;

import java.awt.Color;

import javax.swing.*;

import dominio.EstadoEstacion;
import excepciones.LineaNoEliminableException;
import excepciones.TrayectoNoEliminableException;
import gestores.GestorJDBC;
import gestores.GestorValidaciones;
import interfaces.grafo.DrawingGraph;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PRUEBAS VALIDACIONES
		try {
			GestorValidaciones.validarEliminacionTrayecto(GestorJDBC.buscarTrayecto("TRAY2", "", "", "", null).get(0));
			GestorValidaciones.validarEliminacionLineaDeTransporte(GestorJDBC.buscarLineaDeTransporte("LIN9", "", "", null).get(0));
		} catch (TrayectoNoEliminableException e) {
			// TODO Auto-generated catch block
			System.out.println(e.errores);
		} catch (LineaNoEliminableException e) {
			// TODO Auto-generated catch block
			System.out.println(e.errores);
		}
				
		DrawingGraph grafo = new DrawingGraph(GestorJDBC.buscarColoresTrayectos(GestorJDBC.buscarTrayecto("", "", "", "", null)));
		JFrame ventana = new JFrame();
		ventana.setSize(1280, 720);
		JScrollPane scroll = new JScrollPane(grafo);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ventana.setContentPane(scroll);
		
		scroll.setSize(1200,700);
		
		ventana.setVisible(true);
	}

}
