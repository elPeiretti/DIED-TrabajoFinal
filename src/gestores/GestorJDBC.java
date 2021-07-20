package gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import dominio.*;

public class GestorJDBC {

	
public static void updateUltimosId () {
	
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	try {
		//Defino motor de base de datos
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Carga el Driver Manager
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/died","root","velero100");
		
		String armadoStm = "SELECT * FROM ultimos_id";
				
		pstm = conn.prepareStatement(armadoStm);
			
		rs = pstm.executeQuery();
		
		while(rs.next()) {
			if(rs.getString("nombre").equals("id_estacion")) {
				Estacion.setUltimo_id(rs.getInt("valor"));
			} else if(rs.getString("nombre").equals("id_boleto")) {
				Boleto.setUltimo_id(rs.getInt("valor"));
			} else if(rs.getString("nombre").equals("id_camino")) {
				Camino.setUltimo_id(rs.getInt("valor"));
			} else if(rs.getString("nombre").equals("id_cliente")) {
				Cliente.setUltimo_id(rs.getInt("valor"));
			} else if(rs.getString("nombre").equals("id_linea")) {
				LineaDeTransporte.setUltimo_id(rs.getInt("valor"));
			} else if(rs.getString("nombre").equals("id_tarea")) {
				TareaDeMantenimiento.setUltimo_id(rs.getInt("valor"));
			} else if(rs.getString("nombre").equals("id_trayecto")) {
				Trayecto.setUltimo_id(rs.getInt("valor"));
			}
		}
				
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// conn.rollback();
		e.printStackTrace();
	} finally {
		
		if(rs!=null) try { rs.close(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		if(pstm!=null) try { pstm.close(); }
		catch (SQLException e) {e.printStackTrace(); }
		
		if(conn!=null) try { conn.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	
}
	
public static List<Estacion> buscarEstacion (String id_estacion, String nombre, String horario_apertura, String horario_cierre, EstadoEstacion estado) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Estacion> resultado = new ArrayList<Estacion>();
		
		try {
			//Defino motor de base de datos
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Carga el Driver Manager
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/died","root","velero100");
			
			String armadoStm = "SELECT * FROM estacion WHERE "
					+ "id_estacion LIKE ? AND "
					+ "nombre LIKE ? AND "
					+ "horario_apertura LIKE ? AND "
					+ "horario_cierre LIKE ?";
			
			if(estado !=  null) {
				armadoStm += (" AND estado = ?");
			}
				
			
			pstm = conn.prepareStatement(armadoStm);
		
			//conn.setAutoCommit(false);
			pstm.setString(1, (id_estacion.isEmpty()? "%" : id_estacion));
			pstm.setString(2, (nombre.isEmpty()? "%" : "%"+nombre.toUpperCase().replace(' ','%')+"%"));
			pstm.setString(3,(horario_apertura.isEmpty()? "%" : horario_apertura));
			pstm.setString(4, (horario_cierre.isEmpty()? "%" : horario_cierre));
			
			if(estado !=  null) {
				pstm.setString(5,estado.toString());
			}
			
			
			//int cantidad = pstm.executeUpdate();
			//conn.commit()
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Estacion aux = GestorEntidades.crearEstacion(rs.getString("id_estacion"),rs.getString("nombre"),rs.getString("horario_apertura"),rs.getString("horario_cierre"),rs.getString("estado"));
				resultado.add(aux);
			}
					
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			
			if(rs!=null) try { rs.close(); }
			catch (SQLException e) { e.printStackTrace(); }
			
			if(pstm!=null) try { pstm.close(); }
			catch (SQLException e) {e.printStackTrace(); }
			
			if(conn!=null) try { conn.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
		
		return resultado;
		
	}
	

public static List<TareaDeMantenimiento> buscarTareaDeMantenimiento (String id_tarea, String fecha_inicio, String fecha_fin, String id_estacion) {
	
	
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	List<TareaDeMantenimiento> resultado = new ArrayList<TareaDeMantenimiento>();
	
	
	try {
		//Defino motor de base de datos
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Carga el Driver Manager
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/died","root","velero100");
		
		String armadoStm = "SELECT * FROM tarea_mantenimiento WHERE "
				+ "id_tarea LIKE ? AND "
				+ "id_estacion LIKE ?";
		
		if (!fecha_inicio.isEmpty()) {
			armadoStm += (" AND fecha_inicio = " + "'" + fecha_inicio + "'");
		}
		
		
		if (!fecha_fin.isEmpty()) {
			if(fecha_fin.equalsIgnoreCase("null")) {
				armadoStm += (" AND fecha_fin IS NULL");	
			} else {
			armadoStm += (" AND fecha_fin = " + "'" +fecha_fin + "'");
			}
		}
			
		pstm = conn.prepareStatement(armadoStm);
		
		//conn.setAutoCommit(false);
		pstm.setString(1, (id_tarea.isEmpty()? "%" : id_tarea));
		pstm.setString(2, (id_estacion.isEmpty()? "%" : id_estacion));
		
		//int cantidad = pstm.executeUpdate();
		//conn.commit()
		
		rs = pstm.executeQuery();
		
		while(rs.next()) {
			TareaDeMantenimiento aux = new TareaDeMantenimiento(rs.getString("id_tarea"),rs.getString("fecha_inicio"),rs.getString("fecha_fin"),rs.getString("observaciones"));
			resultado.add(aux);
			
		}
					
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// conn.rollback();
		e.printStackTrace();
	} finally {
		
		if(rs!=null) try { rs.close(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		if(pstm!=null) try { pstm.close(); }
		catch (SQLException e) {e.printStackTrace(); }
		
		if(conn!=null) try { conn.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	return resultado;	
}


public static List<LineaDeTransporte> buscarLineaDeTransporte (String id_linea, String nombre, String color, EstadoLinea estado) {
	
	
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	List<LineaDeTransporte> resultado = new ArrayList<LineaDeTransporte>();
	
	try {
		//Defino motor de base de datos
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Carga el Driver Manager
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/died","root","velero100");
		
		String armadoStm = "SELECT * FROM linea_transporte WHERE "
				+ "id_linea LIKE ? AND "
				+ "nombre LIKE ? AND "
				+ "color LIKE ?" ;
		
		if (estado != null) {
			armadoStm += (" AND estado = " + estado.toString());
		}
				
		pstm = conn.prepareStatement(armadoStm);
		
		//conn.setAutoCommit(false);
		pstm.setString(1, (id_linea.isEmpty()? "%" : id_linea));
		pstm.setString(2, (nombre.isEmpty()? "%" : nombre));
		pstm.setString(3, (color.isEmpty()? "%" : color));
		
		//int cantidad = pstm.executeUpdate();
		//conn.commit()
		
		rs = pstm.executeQuery();
		
		while(rs.next()) {
			LineaDeTransporte aux = GestorEntidades.crearLineaDeTransporte(rs.getString("id_linea"),rs.getString("nombre"),rs.getString("color"),rs.getInt("estado"));
			resultado.add(aux);
		}
					
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// conn.rollback();
		e.printStackTrace();
	} finally {
		
		if(rs!=null) try { rs.close(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		if(pstm!=null) try { pstm.close(); }
		catch (SQLException e) {e.printStackTrace(); }
		
		if(conn!=null) try { conn.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	return resultado;	
}


public static List<Trayecto> buscarTrayecto (String id_trayecto, String id_linea, String id_origen, String id_destino) {
	
	
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	List<Trayecto> resultado = new ArrayList<Trayecto>();
	List<String> origenes = new ArrayList<String>();
	List<String> destinos = new ArrayList<String>();
	
	try {
		//Defino motor de base de datos
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Carga el Driver Manager
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/died","root","velero100");
		
		String armadoStm = "SELECT * FROM trayecto WHERE "
				+ "id_trayecto LIKE ? AND "
				+ "id_linea LIKE ? AND "
				+ "id_origen LIKE ? AND "
				+ "id_destino LIKE ?";
		
				
		pstm = conn.prepareStatement(armadoStm);
		
		//conn.setAutoCommit(false);
		pstm.setString(1, (id_trayecto.isEmpty()? "%" : id_trayecto));
		pstm.setString(2, (id_linea.isEmpty()? "%" : id_linea));
		pstm.setString(3, (id_origen.isEmpty()? "%" : id_origen));
		pstm.setString(4, (id_destino.isEmpty()? "%" : id_destino));
		
		//int cantidad = pstm.executeUpdate();
		//conn.commit()
		
		rs = pstm.executeQuery();
		
		while(rs.next()) {
			Trayecto aux = GestorEntidades.crearTrayecto(rs.getString("id_trayecto"),rs.getInt("distancia"),rs.getInt("duracion"),rs.getInt("cant_max_pasajeros")
					,rs.getInt("estado"),rs.getDouble("costo"),null,null); //las estaciones se agregan mas adelante
			resultado.add(aux);
			
			origenes.add(rs.getString("id_origen"));
			destinos.add(rs.getString("id_destino"));
		}
					
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// conn.rollback();
		e.printStackTrace();
	} finally {
		
		if(rs!=null) try { rs.close(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		if(pstm!=null) try { pstm.close(); }
		catch (SQLException e) {e.printStackTrace(); }
		
		if(conn!=null) try { conn.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	//Cargo estaciones Origen y Destino de los Trayectos
	
	for(int i = 0; i < resultado.size(); i++) {
		resultado.get(i).setOrigen(buscarEstacion(origenes.get(i),"","","",null).get(0));
		resultado.get(i).setDestino(buscarEstacion(destinos.get(i),"","","",null).get(0));
	}

	return resultado;
}

public static void agregarCliente(Cliente nuevo_cliente) {
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	
	try {
		//Defino motor de base de datos
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Carga el Driver Manager
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/died","root","velero100");
		
		String armadoStm = "INSERT INTO cliente VALUES (?,?,?);";
		
				
		pstm = conn.prepareStatement(armadoStm);
		
		conn.setAutoCommit(false);
		
		pstm.setString(1, nuevo_cliente.getId_cliente());
		pstm.setString(2, nuevo_cliente.getNombre());
		pstm.setString(3, nuevo_cliente.getEmail());		
		
		pstm.executeUpdate();
		
		conn.commit();
		
		conn.setAutoCommit(true);
					
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		
		if(pstm!=null) try { pstm.close(); }
		catch (SQLException e) {e.printStackTrace(); }
		
		if(conn!=null) try { conn.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
		
}


public static void agregarEstacion(Estacion nueva_estacion) {
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	
	try {
		//Defino motor de base de datos
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Carga el Driver Manager
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/died","root","velero100");
		
		String armadoStm = "INSERT INTO estacion VALUES (?,?,?,?,?);";
		
				
		pstm = conn.prepareStatement(armadoStm);
		
		conn.setAutoCommit(false);
		
		pstm.setString(1, nueva_estacion.getId_estacion());
		pstm.setString(2, nueva_estacion.getNombre());
		pstm.setString(3, nueva_estacion.getHorario_apertura());
		pstm.setString(4, nueva_estacion.getHorario_cierre());
		pstm.setString(5, nueva_estacion.getEstado().toString());
		
		pstm.executeUpdate();
		
		conn.commit();
		
		conn.setAutoCommit(true);
					
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		
		if(pstm!=null) try { pstm.close(); }
		catch (SQLException e) {e.printStackTrace(); }
		
		if(conn!=null) try { conn.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
		
}

public static void agregarTrayecto(String id_linea, Trayecto nuevo_trayecto) {
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	
	try {
		//Defino motor de base de datos
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Carga el Driver Manager
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/died","root","velero100");
		
		String armadoStm = "INSERT INTO trayecto VALUES (?,?,?,?,?,?,?,?,?);";
		
				
		pstm = conn.prepareStatement(armadoStm);
		
		conn.setAutoCommit(false);
			
		pstm.setString(1, nuevo_trayecto.getId_trayecto());
		pstm.setDouble(2, nuevo_trayecto.getDistancia());
		pstm.setInt(3, nuevo_trayecto.getDuracion());
		pstm.setInt(4, nuevo_trayecto.getCant_max_pasajeros());
		pstm.setInt(5, nuevo_trayecto.getEstado().equals(EstadoTrayecto.ACTIVO)? 0:1);
		pstm.setDouble(6, nuevo_trayecto.getCosto());
		pstm.setString(7, id_linea);
		pstm.setString(8, nuevo_trayecto.getOrigen().getId_estacion());
		pstm.setString(9, nuevo_trayecto.getDestino().getId_estacion());
		
		pstm.executeUpdate();
		
		conn.commit();
		
		conn.setAutoCommit(true);
					
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		
		if(pstm!=null) try { pstm.close(); }
		catch (SQLException e) {e.printStackTrace(); }
		
		if(conn!=null) try { conn.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
		
}


public static void agregarTareaDeMantenimiento(String id_estacion, TareaDeMantenimiento nueva_tarea) {
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	
	try {
		//Defino motor de base de datos
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Carga el Driver Manager
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/died","root","velero100");
		
	
		
		String armadoStm;
		
		if(nueva_tarea.getFecha_fin() != null) {
			armadoStm = "INSERT INTO tarea_mantenimiento VALUES (?,?,?,?,?)";
		} else {
			armadoStm = "INSERT INTO tarea_mantenimiento (id_tarea, fecha_inicio, id_estacion) VALUES (?,?,?)";
		}
				
		pstm = conn.prepareStatement(armadoStm);
		
		conn.setAutoCommit(false);
		
		if(nueva_tarea.getFecha_fin() != null) {
			
			pstm.setString(1, nueva_tarea.getId_tarea());
			pstm.setString(2, nueva_tarea.getFecha_inicio().toString());
			pstm.setString(3, nueva_tarea.getFecha_fin().toString());
			pstm.setString(4, nueva_tarea.getObservaciones());
			pstm.setString(5, id_estacion);
		
		} else {
		
			pstm.setString(1, nueva_tarea.getId_tarea());
			pstm.setString(2, nueva_tarea.getFecha_inicio().toString());
			pstm.setString(3, id_estacion);
		
		}
		pstm.executeUpdate();
		
		conn.commit();
		
		conn.setAutoCommit(true);
					
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		
		if(pstm!=null) try { pstm.close(); }
		catch (SQLException e) {e.printStackTrace(); }
		
		if(conn!=null) try { conn.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
		
}


public static void agregarLineaDeTransporte(LineaDeTransporte nueva_linea) {
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	
	try {
		//Defino motor de base de datos
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Carga el Driver Manager
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/died","root","velero100");
		
		String armadoStm = "INSERT INTO linea_transporte VALUES (?,?,?,?);";
		
				
		pstm = conn.prepareStatement(armadoStm);
		
		conn.setAutoCommit(false);
			
		pstm.setString(1, nueva_linea.getId_linea());
		pstm.setString(2, nueva_linea.getNombre());
		pstm.setString(3, nueva_linea.getColor());
		pstm.setInt(4, nueva_linea.getEstado().equals(EstadoLinea.ACTIVA)? 0:1);
				
		pstm.executeUpdate();
		
		conn.commit();
		
		conn.setAutoCommit(true);
					
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		
		if(pstm!=null) try { pstm.close(); }
		catch (SQLException e) {e.printStackTrace(); }
		
		if(conn!=null) try { conn.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
		
}


public static void agregarBoleto(Boleto nuevo_boleto) {
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	
	try {
		//Defino motor de base de datos
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Carga el Driver Manager
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/died","root","velero100");
		
		String armadoStm = "INSERT INTO linea_transporte VALUES (?,?,?,?,?,?);";
		
				
		pstm = conn.prepareStatement(armadoStm);
		
		conn.setAutoCommit(false);
			
		pstm.setString(1, nuevo_boleto.getId_boleto());
		pstm.setString(2, nuevo_boleto.getFecha_venta().toString());
		pstm.setString(3, nuevo_boleto.getCamino().getId_camino());
		pstm.setString(4, nuevo_boleto.getOrigen().getId_estacion());
		pstm.setString(5, nuevo_boleto.getCliente().getId_cliente());
		pstm.setString(6, nuevo_boleto.getDestino().getId_estacion());
				
		pstm.executeUpdate();
		
		conn.commit();
		
		conn.setAutoCommit(true);
					
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		
		if(pstm!=null) try { pstm.close(); }
		catch (SQLException e) {e.printStackTrace(); }
		
		if(conn!=null) try { conn.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
		
}


public static void agregarCamino(Camino nuevo_camino) {
	
	Connection conn = null;
	PreparedStatement pstm = null;
	
	
	try {
		//Defino motor de base de datos
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Carga el Driver Manager
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/died","root","velero100");
		
		String armadoStm = "INSERT INTO camino VALUES (?,?,?,?,?,?);";
				
		pstm = conn.prepareStatement(armadoStm);
		
		conn.setAutoCommit(false);
			
		pstm.setString(1, nuevo_camino.getId_camino());
		pstm.setString(2, nuevo_camino.getOrigen().getId_estacion());
		pstm.setString(3, nuevo_camino.getDestino().getId_estacion());
		pstm.setDouble(4, nuevo_camino.getCosto());
		pstm.setInt(5, nuevo_camino.getDuracion());
		pstm.setDouble(6, nuevo_camino.getDistancia());
				
		pstm.executeUpdate();
		
		for(Trayecto tray : nuevo_camino.getCombinacion()) {
			armadoStm = "INSERT INTO trayecto_camino VALUES (?,?);";
			
			pstm = conn.prepareStatement(armadoStm);
			
			pstm.setString(1, tray.getId_trayecto());
			pstm.setString(2, nuevo_camino.getId_camino());
		}
		
		conn.commit();
		
		conn.setAutoCommit(true);
					
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		
		if(pstm!=null) try { pstm.close(); }
		catch (SQLException e) {e.printStackTrace(); }
		
		if(conn!=null) try { conn.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
		
}

public static void agregarRecorrido(LineaDeTransporte linea_seleccionada, Trayecto[] recorrido) {
	// TODO Auto-generated method stub
	for(Trayecto t : recorrido) {
		agregarTrayecto(linea_seleccionada.getId(),t);
	}
	
}
	
	
}