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
	public Cliente(String id_cliente, String nombre, String email) {
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.email = email;
	}
	public static void setUltimo_id(Integer id) {
		ultimo_id=id;
	}
	public String getId_cliente() {
		return id_cliente;
	}
	public String getNombre() {
		return nombre;
	}
	public String getEmail() {
		return email;
	}
	
}
