package interfaces.grafo;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.*;
import dominio.Estacion;
import dominio.Trayecto;
import gestores.GestorJDBC;

public class DrawingGraph extends JFrame {

	private ArrayList<Nodo> nodos;
	private ArrayList<Arista> aristas;
	private static int ancho = 30;
	private static int alto = 30;
	
	public DrawingGraph(List<Trayecto> trayectos) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		setSize(1000, 1000);
		setBackground(Color.white);
		nodos = new ArrayList<Nodo>();
		aristas = new ArrayList<Arista>();
		int i = 1, j = 1;
				
		for(Trayecto t : trayectos) {
			if(i == 11) {
				i = 1;
				j++;
			} 
			
			Nodo origen = new Nodo (t.getOrigen(), 200 * i, 200 * j);
			nodos.add(new Nodo (t.getOrigen(), 200 * i, 200 * j));
			i++;
			
			if(i == 11) {
				i = 1;
				j++;
			} 
			Nodo destino = new Nodo (t.getDestino(), 200 * i, 200 * j);
			nodos.add(destino);
			i++;
			aristas.add(new Arista(t, Color.black, origen, destino));
		}
				
	}
	
	public void paint(Graphics g) {
		FontMetrics f = g.getFontMetrics();
		int nodeHeight = Math.max(alto, f.getHeight());
		
		g.setColor(Color.white);
		
		for(Arista a : aristas) {
			g.setColor(a.getColor());
			g.drawLine(a.getOrigen().getX(),a.getOrigen().getY(),
					a.getDestino().getX(),a.getDestino().getY());
		}
		
		for (Nodo n : nodos) {
		    int nodeWidth = f.stringWidth(n.getEstacion().getNombre())+ancho/2;
		    g.setColor(Color.white);
		    g.fillOval(n.getX()-nodeWidth/2, n.getY()-nodeHeight/2, 
			       nodeWidth, nodeHeight);
		    g.setColor(Color.black);
		    g.drawOval(n.getX()-nodeWidth/2, n.getY()-nodeHeight/2, 
			       nodeWidth, nodeHeight);
		    
		    g.drawString(n.getEstacion().getNombre(), n.getX()-f.stringWidth(n.getEstacion().getNombre())/2,
				 n.getY()+f.getHeight()/2);
		}
		
		
		
		
	}
	
}
