package interfaces;

import java.awt.Color;

import dominio.EstadoEstacion;
import gestores.GestorJDBC;
import interfaces.grafo.DrawingGraph;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DrawingGraph grafo = new DrawingGraph(GestorJDBC.buscarColoresTrayectos(GestorJDBC.buscarTrayecto("", "", "", "", null)));
	}

}
