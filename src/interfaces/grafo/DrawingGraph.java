package interfaces.grafo;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;

import dominio.Estacion;
import dominio.Trayecto;
import gestores.GestorJDBC;

public class DrawingGraph extends JFrame {

	ArrayList<Nodo> nodos;
	ArrayList<Arista> aristas;
	
	public DrawingGraph(Estacion estaciones, Trayecto trayectos) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		for(Arista arista)
	}
	
}
