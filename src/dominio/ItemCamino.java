package dominio;

import java.util.List;

public class ItemCamino {

	private static Integer ultimo_id=0;
	private String id_item;
	private LineaDeTransporte linea;
	private List<Trayecto> trayectos;
	
	public ItemCamino(LineaDeTransporte linea, List<Trayecto> trayectos) {
		this.id_item = "ITEMC"+(++ultimo_id).toString();
		this.linea = linea;
		this.trayectos = trayectos;
	}
}
