package dominio;

import java.util.List;

public class Camino {

	private static Integer ultimo_id=0;
	private String id_camino;
	private List<Trayecto> combinacion;
	
	public Camino(List<Trayecto> combinacion) {
		this.id_camino = "PATH"+(++ultimo_id).toString();
		this.combinacion = combinacion;
	}
}
