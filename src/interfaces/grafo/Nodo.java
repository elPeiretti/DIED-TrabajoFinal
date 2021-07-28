package interfaces.grafo;

import dominio.Estacion;
import java.awt.*;
import java.util.List;

public class Nodo {

	private Estacion estacion;
	private Integer x;
	private Integer y;
	private Rectangle b = new Rectangle();
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estacion == null) ? 0 : estacion.hashCode());
		return result;
	}

	public Nodo(Estacion estacion, Integer x, Integer y) {
		this.estacion = estacion;
		this.x = x;
		this.y = y; 
	}
	
	public void dibujar(Graphics g) {
		FontMetrics f = g.getFontMetrics();
		int alto = PanelDeGrafo.alto;
		int ancho = PanelDeGrafo.ancho;
		int nodeHeight = Math.max(alto, f.getHeight());
		int nodeWidth = f.stringWidth(estacion.getNombre())+ancho/2;
		setLimites(nodeWidth, nodeHeight);
		
		g.setColor(Color.white);
	    g.fillOval(x-nodeWidth/2, y-nodeHeight/2, nodeWidth, nodeHeight);
	    g.setColor(Color.black);
	    g.drawOval(x-nodeWidth/2, y-nodeHeight/2, nodeWidth, nodeHeight);
	    g.drawString(estacion.getNombre(), x-f.stringWidth(estacion.getNombre())/2, y+f.getHeight()/2);
	}
	
	private void setLimites(int ancho, int alto) {
		b.setBounds(x-ancho/2, y-alto/2, ancho, alto);
	}
	
	public Boolean contiene(Point p) {
		return b.contains(p);
	}
	
	public Estacion getEstacion() {
		return estacion;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nodo other = (Nodo) obj;
		if (estacion == null) {
			if (other.estacion != null)
				return false;
		} else if (!estacion.equals(other.estacion))
			return false;
		return true;
	}

	public static Nodo haySeleccionado(List<Nodo> nodos, Point mousePt) {
		for(Nodo n : nodos) {
			if(n.contiene(mousePt)) {
				return n;
			}
		}
		return null;
	}

	public void actualizarPosicion(Point point) {
		x += point.x;
		y += point.y;
		setLimites(b.width,b.height);
	}
	
	
}
