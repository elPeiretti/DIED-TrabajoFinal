package interfaces.grafo;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import dominio.Trayecto;

public class PanelDeGrafo extends JPanel {

	private static final long serialVersionUID = 514328595308885933L;
	
	private List<Nodo> nodos;
	private List<Arista> aristas;
	static int ancho = 30;
	static int alto = 30;
	private Boolean seleccionando = false;
	private Point mousePt;
	private Nodo moviendo;
	
	public PanelDeGrafo(Map<Trayecto, String> trayectos) {
		
		this.agregarCapturadoresDeEventos();
		
		setSize(2000, 2000);
				
		int i = 1, j = 1;
		int espaciado = 80;

		nodos = new ArrayList<Nodo>();
		aristas = new ArrayList<Arista>();
		
		for(Trayecto t : trayectos.keySet()) { // O(N^2)
						
			Nodo inicio = new Nodo(t.getOrigen(),espaciado * i, espaciado * j);
		
			if(!nodos.contains(inicio)) {
				nodos.add(inicio); 
				i++;
			}				
			else {
				inicio = nodos.get(nodos.indexOf(inicio));	
			}
			
			Nodo fin = new Nodo(t.getDestino(),espaciado * i, espaciado * j);
			
			if(!nodos.contains(fin)) {
				nodos.add(fin);
				i++;
			}
			else {
				fin = nodos.get(nodos.indexOf(fin));
			}
			
			aristas.add(new Arista(t, Color.decode(trayectos.get(t)), inicio, fin));
			
			if(i == 8) {
				j++; i = 1;
			}
		}
		this.setVisible(true);
				
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.white);
		
		for(Arista a : aristas) {
			a.dibujar(g);
		}
		
		for (Nodo n : nodos) {
			n.dibujar(g);
		}
	}
	
	private void agregarCapturadoresDeEventos() {
		
		this.addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent e) {
					mousePt = e.getPoint();
					
					if((moviendo = Nodo.haySeleccionado(nodos,mousePt))!= null) {
						seleccionando = true;
						//System.out.println("tocando el "+moviendo.getEstacion().getNombre());
					}
					e.getComponent().repaint();
				}
				
				public void mouseReleased(MouseEvent e) {
					seleccionando = false;
					moviendo = null;
					e.getComponent().repaint();
				}
				
				
			});
		
		this.addMouseMotionListener(new MouseAdapter() {
						
			public void mouseDragged(MouseEvent e) {
				Point delta = new Point();
				delta.x = (int) (e.getX() - mousePt.getX());
				delta.y = (int) (e.getY() - mousePt.getY());
				if(seleccionando) {
					moviendo.actualizarPosicion(delta);
				}
				mousePt = e.getPoint();
				e.getComponent().repaint();
			}
		});
		
	}

		
}
