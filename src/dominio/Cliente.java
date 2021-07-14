package dominio;

public class Cliente {
	
	private static Integer ultimo_id=0;
	private String id_cliente;
	private String nombre;
	private String email;
	
	public Cliente(String nombre, String email) {
		this.id_cliente = "CLI"+(++ultimo_id).toString();
		this.nombre = nombre;
		this.email = email;
	}
	
}
