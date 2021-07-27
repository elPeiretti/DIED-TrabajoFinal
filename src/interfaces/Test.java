package interfaces;

import java.awt.Color;

import javax.swing.*;

import dominio.EstadoEstacion;
import gestores.GestorJDBC;
import interfaces.grafo.DrawingGraph;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
