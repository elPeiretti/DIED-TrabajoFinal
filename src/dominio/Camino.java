package dominio;

import java.util.List;

public class Camino {

	private static Integer ultimo_id=0;
	private String id_camino;
	private List<ItemCamino> combinacion;
	
	public Camino(List<ItemCamino> combinacion) {
		this.id_camino = "PATH"+(++ultimo_id).toString();
		this.combinacion = combinacion;
	}
}
