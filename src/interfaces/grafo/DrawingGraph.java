package interfaces.grafo;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import dominio.Estacion;
import dominio.Trayecto;
import gestores.GestorJDBC;

public class DrawingGraph extends JPanel {

	private List<Nodo> nodos;
	private List<Arista> aristas;
	private static int ancho = 30;
	private static int alto = 30;
	
	public DrawingGraph(Map<Trayecto, String> trayectos) {
		this.setVisible(true);
		
		setSize(1280, 720);
				
		int i = 1, j = 1;
		int espaciado = 80;
		//N1 -> N2 		H N1 N2 A1
		//N2 -> N3		H -  N3 A2
		nodos = new ArrayList<Nodo>();
		aristas = new ArrayList<Arista>();
		
		for(Trayecto t : trayectos.keySet()) { // O(N^2)
						
			Nodo inicio = new Nodo(t.getOrigen(),espaciado * i, espaciado * j);
			j++;
			Nodo fin = new Nodo(t.getDestino(),espaciado * i, espaciado * j);
			
			if(!nodos.contains(inicio))
				nodos.add(inicio); 
			else {
				inicio = nodos.get(nodos.indexOf(inicio));
			}
			
			if(!nodos.contains(fin))
				nodos.add(fin);
			else {
				fin = nodos.get(nodos.indexOf(fin));
			}
			aristas.add(new Arista(t, Color.decode(trayectos.get(t)), inicio, fin));
			i++;
		}
				
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
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
