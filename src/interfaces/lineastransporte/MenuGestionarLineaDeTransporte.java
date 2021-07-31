package interfaces.lineastransporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import dominio.EstadoLinea;
import dominio.LineaDeTransporte;
import excepciones.DatosDeLineaDeTransporteIncorrectosException;
import excepciones.LineaNoEliminableException;
import excepciones.RecorridoLineaExistenteException;
import gestores.*;
import interfaces.VentanaPrincipal;

public class MenuGestionarLineaDeTransporte extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6814597321105357697L;
	
	private JTextField jtf_nombre;
	private JTextField jtf_color;
	private JButton jb_buscar;
	private JButton jb_regresar;
	private JButton jb_alta;
	private JButton jb_eliminar;
	private JComboBox<EstadoLinea> jcb_estado;
	private JButton jb_modificar;
	private JLabel lbl_color;
	private JLabel lbl_nombre;
	private JLabel lbl_estado;
	private JButton jb_registrar_recorrido;
	private JTable jtable_lineas;
	private DefaultTableModel jtable_lineas_contenido;
	private VentanaPrincipal ventana_contenedora;
	private JTextPane jtp_errores;
	private List<LineaDeTransporte> objetos_en_tabla;
	private JScrollPane jspane_lineas;
	private JButton jb_seleccionar_color;
	private JButton jb_quitar_color;
	
	/**
	 * Create the panel.
	 */
	public MenuGestionarLineaDeTransporte(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(515, 61, 95, 23);
		
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(85, 13, 200, 20);
		jtf_nombre.setColumns(10);
		
		jb_alta = new JButton("Dar de Alta");
		jb_alta.setBounds(355, 61, 150, 23);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 266, 95, 23);
		
		jb_modificar = new JButton("Modificar");
		jb_modificar.setBounds(355, 266, 95, 23);
		
		jb_eliminar = new JButton("Eliminar");
		jb_eliminar.setBounds(250, 266, 95, 23);
		
		jtf_color = new JTextField();
		jtf_color.setEditable(false);
		jtf_color.setBounds(85, 62, 100, 20);
		jtf_color.setColumns(10);
		
		jcb_estado = new JComboBox<EstadoLinea>();
		jcb_estado.setModel(new DefaultComboBoxModel<EstadoLinea>(new EstadoLinea[] {null,EstadoLinea.ACTIVA,EstadoLinea.NO_ACTIVA}));
		jcb_estado.setMaximumRowCount(3);
		jcb_estado.setBounds(460, 11, 150, 24);
		
		lbl_color = new JLabel("Color:");
		lbl_color.setBounds(26, 65, 48, 14);
		
		lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(26, 16, 55, 14);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(355, 16, 80, 14);
		
		jb_registrar_recorrido = new JButton("Registrar recorrido");
		jb_registrar_recorrido.setBounds(460, 266, 150, 23);
		
		jtable_lineas = new JTable();
		jtable_lineas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable_lineas.setBorder(null);
		jtable_lineas.setBounds(26, 95, 389, 150);
		
		jtable_lineas_contenido = new DefaultTableModel();
		jtable_lineas_contenido.addColumn("ID");
		jtable_lineas_contenido.addColumn("Nombre");
		jtable_lineas_contenido.addColumn("Color");
		jtable_lineas_contenido.addColumn("Estado");
		
		jtable_lineas.setModel(jtable_lineas_contenido);
		objetos_en_tabla = new ArrayList<LineaDeTransporte>();
		
		jspane_lineas = new JScrollPane(jtable_lineas);
		jspane_lineas.setSize(600, 150);
		jspane_lineas.setLocation(10, 100);
		
		jtp_errores = new JTextPane();
		jtp_errores.setEditable(false);
		jtp_errores.setBackground(UIManager.getColor("Button.background"));
		jtp_errores.setForeground(Color.RED);
		jtp_errores.setBounds(10, 300, 600, 89);
		
		jb_seleccionar_color = new JButton("...");
		jb_seleccionar_color.setToolTipText("Seleccionar Color");
		jb_seleccionar_color.setBounds(190, 62, 20, 20);
		
		jb_quitar_color = new JButton("Quitar");
		jb_quitar_color.setToolTipText("Elimina el color seleccionado");
		jb_quitar_color.setBounds(216, 62, 69, 20);
		
		this.agregarActionListener();
		add(jb_registrar_recorrido);
		add(lbl_estado);
		add(lbl_nombre);
		add(lbl_color);
		add(jcb_estado);
		add(jb_modificar);
		add(jtf_color);
		add(jb_eliminar);
		add(jb_regresar);
		add(jb_alta);
		add(jb_buscar);
		add(jtf_nombre);
		add(jspane_lineas);
		add(jtp_errores);
		add(jb_seleccionar_color);
		add(jb_quitar_color);

	}
	
	private void agregarActionListener() {
		
		jb_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializarBotones(false);
				EstadoLinea estado = (EstadoLinea) jcb_estado.getSelectedItem();

				//buscar datos en la BD
				List<LineaDeTransporte> lineas = GestorJDBC.buscarLineaDeTransporte("",jtf_nombre.getText(),jtf_color.getText(),estado);
				limpiarTabla();
				//llenar tabla
				for(LineaDeTransporte linea : lineas) {
					jtable_lineas_contenido.addRow(linea.asVector());
					objetos_en_tabla.add(linea);
				}
				
			}
		});
		
		jb_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				ventana_contenedora.cambiarPanel(VentanaPrincipal.MENU_PPAL);
			}
		});
		
		jb_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer i = jtable_lineas.getSelectedRow();
				if(i != -1) {
					MenuEdicionLineaDeTransporte.linea_seleccionada = objetos_en_tabla.get(i);
					ventana_contenedora.cambiarPanel(VentanaPrincipal.EDIT_LINEA);
				}
			}
		});
		
		jb_registrar_recorrido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer i = jtable_lineas.getSelectedRow();
				if(i != -1){
					try {
						GestorValidaciones.lineaNoPoseeRecorrido(objetos_en_tabla.get(i));
						MenuRegistrarRecorrido.linea_seleccionada = objetos_en_tabla.get(i);
						ventana_contenedora.cambiarPanel(VentanaPrincipal.REG_RECORRIDO);
					} catch (RecorridoLineaExistenteException exp) {
						jtp_errores.setText(exp.getMessage());
					}
					
				}
			}
		});
		
		jb_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer i = jtable_lineas.getSelectedRow();
				
				if(i != -1) {
					Integer opcion = VentanaPrincipal.popupConfirmar("Esta seguro que desea eliminar la linea " + objetos_en_tabla.get(i).getNombre() + "?", "Confirmar Baja");
					
					if(opcion == JOptionPane.YES_OPTION) {
						try {
							GestorValidaciones.validarEliminacionLineaDeTransporte(objetos_en_tabla.get(i));
							jtp_errores.setText("");
							GestorJDBC.eliminarLineaDeTransporte(objetos_en_tabla.get(i));
							jtable_lineas_contenido.removeRow(i);	
							objetos_en_tabla.remove(objetos_en_tabla.get(i));
							VentanaPrincipal.popupInfo("Se elimino la Linea exitosamente.", "Baja Linea Existosa");
							inicializarBotones(false);
						} catch (LineaNoEliminableException exp) {
							jtp_errores.setText(exp.errores);
						}
						
					}
					
					
				}
					
			}
		});
		
		jb_alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = jtf_nombre.getText();
					String color = jtf_color.getText();
					EstadoLinea estado = (EstadoLinea) jcb_estado.getSelectedItem();
					
					GestorValidaciones.validarLineaDeTransporte(nombre,color,estado);
					jtp_errores.setText("");
					
					GestorJDBC.agregarLineaDeTransporte(GestorEntidades.crearLineaDeTransporte(nombre,color,estado)); 
					VentanaPrincipal.popupInfo("Se agrego la Linea exitosamente.", "Alta Existosa");
					limpiarCampos();
					limpiarTabla();
				}
				catch(DatosDeLineaDeTransporteIncorrectosException exc) {
					jtp_errores.setText(exc.errores);
				}
			}
		});
		
		jtable_lineas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(jtable_lineas.getSelectedRow() != -1) {
					inicializarBotones(true);
				}
			}
		});
		
		jb_seleccionar_color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = VentanaPrincipal.popupSeleccionarColor();
				jtf_color.setText(String.format("#%02X%02X%02X",c.getRed(),c.getGreen(),c.getBlue()));
			}
		});
		
		jb_quitar_color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtf_color.setText("");
			}
		});
		
	}
	
	public void limpiarCampos() {
		jtf_color.setText("");
		jtf_nombre.setText("");
		jcb_estado.setSelectedItem(null);
	}
	
	public void limpiarTabla() {
		jtable_lineas_contenido.setRowCount(0);
		objetos_en_tabla.clear();
	}
	
	public void inicializarBotones(boolean estado) {
		jtp_errores.setText("");
		jb_modificar.setEnabled(estado);
		jb_eliminar.setEnabled(estado);
		jb_registrar_recorrido.setEnabled(estado);
	}
}
