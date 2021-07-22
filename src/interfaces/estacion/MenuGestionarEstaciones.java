package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dominio.Estacion;
import dominio.EstadoEstacion;
import excepciones.DatosDeEstacionIncorrectosException;
import gestores.GestorEntidades;
import gestores.GestorJDBC;
import gestores.GestorValidaciones;
import interfaces.VentanaPrincipal;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class MenuGestionarEstaciones extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4997700988942456105L;
	
	private JTextField jtf_nombre;
	private JTextField jtf_horario_apertura;
	private JTextField jtf_horario_cierre;
	private JButton jb_buscar;
	private JButton jb_alta;
	private JButton jb_regresar;
	private JButton jb_eliminar;
	private JComboBox<EstadoEstacion> jcb_estado;
	private JButton jb_modificar;
	private JLabel lbl_horario_cierre;
	private JLabel lbl_nombre;
	private JLabel lbl_horario_apertura;
	private JLabel lbl_estado;
	private JTable jtable_estaciones;
	private VentanaPrincipal ventana_contenedora;
	private JTextPane jtp_errores;
	private DefaultTableModel jtable_estaciones_contenido;
	private List<Estacion> objetos_en_tabla;
	protected static Estacion estacion_seleccionada;
	private JScrollPane jspane_tabla;

	/**
	 * Create the panel.
	 */
	public MenuGestionarEstaciones(VentanaPrincipal contenedor) {
		this.ventana_contenedora = contenedor;
		setLayout(null);
		
		jb_buscar = new JButton("Buscar");
		jb_buscar.setBounds(359, 71, 67, 23);

		
		jtf_nombre = new JTextField();
		jtf_nombre.setBounds(115, 8, 89, 20);
		jtf_nombre.setColumns(10);
		
		
		jb_alta = new JButton("Dar de Alta");
		jb_alta.setBounds(260, 71, 89, 23);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 266, 77, 23);
		
		jb_modificar = new JButton("Modificar");
		jb_modificar.setBounds(360, 266, 77, 23);
		
		jb_eliminar = new JButton("Eliminar");
		jb_eliminar.setBounds(277, 266, 73, 23);
		
		jtf_horario_cierre = new JTextField();
		jtf_horario_cierre.setBounds(331, 39, 95, 20);
		jtf_horario_cierre.setColumns(10);
		
		jtf_horario_apertura = new JTextField();
		jtf_horario_apertura.setBounds(115, 39, 89, 20);
		jtf_horario_apertura.setColumns(10);
		
		jcb_estado = new JComboBox<EstadoEstacion>();
		jcb_estado.setModel(new DefaultComboBoxModel<EstadoEstacion>(new EstadoEstacion[] {null,EstadoEstacion.OPERATIVA, EstadoEstacion.EN_MANTENIMIENTO}));
		jcb_estado.setMaximumRowCount(3);
		jcb_estado.setBounds(331, 6, 95, 24);
		
		lbl_horario_cierre = new JLabel("Horario de Cierre:");
		lbl_horario_cierre.setBounds(214, 42, 106, 14);
		
		lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(10, 11, 48, 14);
		
		lbl_horario_apertura = new JLabel("Horario de Apertura:");
		lbl_horario_apertura.setBounds(10, 42, 106, 14);
		
		lbl_estado = new JLabel("Estado:");
		lbl_estado.setBounds(214, 11, 48, 14);
		
		
		
		jtable_estaciones = new JTable();
		jtable_estaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable_estaciones.setBorder(null);
		jtable_estaciones.setFillsViewportHeight(true);
		jtable_estaciones.setBounds(26, 105, 400, 150);
		
		jtable_estaciones_contenido = new DefaultTableModel();
		jtable_estaciones_contenido.addColumn("ID");
		jtable_estaciones_contenido.addColumn("Nombre");
		jtable_estaciones_contenido.addColumn("Estado");
		jtable_estaciones_contenido.addColumn("Horario de apertura");
		jtable_estaciones_contenido.addColumn("Horario de cierre");
		
		jtable_estaciones.setModel(jtable_estaciones_contenido);
		
		jspane_tabla = new JScrollPane(jtable_estaciones);
		jspane_tabla.setSize(430, 150);
		jspane_tabla.setLocation(10, 100);
		objetos_en_tabla = new ArrayList<Estacion>();
		
		jtp_errores = new JTextPane();
		jtp_errores.setEditable(false);
		jtp_errores.setForeground(Color.RED);
		jtp_errores.setBackground(UIManager.getColor("Button.background"));
		jtp_errores.setBounds(10, 323, 416, 66);
		
		this.agregarActionListener();
		add(lbl_estado);
		add(lbl_horario_apertura);
		add(lbl_nombre);
		add(lbl_horario_cierre);
		add(jcb_estado);
		add(jb_modificar);
		add(jtf_horario_apertura);
		add(jtf_horario_cierre);
		add(jb_eliminar);
		add(jb_regresar);
		add(jb_alta);
		add(jb_buscar);
		add(jtf_nombre);
		add(jspane_tabla);
		add(jtp_errores);
	
	}
	
	private void agregarActionListener() {
		jb_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				ventana_contenedora.cambiarPanel(VentanaPrincipal.MENU_PPAL);
			}
		});
		
		jb_modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jtable_estaciones.getSelectedRow()!=-1) {
					estacion_seleccionada = objetos_en_tabla.get(jtable_estaciones.getSelectedRow());
					ventana_contenedora.cambiarPanel(VentanaPrincipal.EDIT_ESTACION);
				}
			}
		});
		
		jb_eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Integer i = jtable_estaciones.getSelectedRow();
				if(i != -1) {
					
					Integer opcion = VentanaPrincipal.popupConfirmar("Esta seguro que desea eliminar la estacion " + objetos_en_tabla.get(i).getNombre() + "?", "Confirmar Baja");
					
					if(opcion == JOptionPane.YES_OPTION) {
						//GestorJDBC.eliminarEstacion(objetos_en_tabla.get(i)) TO DO
						VentanaPrincipal.popupInfo("Se elimino la Estacion con Exito", "Baja Estacion Exitosa");	
						objetos_en_tabla.remove(objetos_en_tabla.get(i));
						jtable_estaciones_contenido.removeRow(i);
					}
				}
			}
		});
		
		jb_alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = jtf_nombre.getText();
				String apertura = jtf_horario_apertura.getText();
				String cierre = jtf_horario_cierre.getText();
				EstadoEstacion estado = (EstadoEstacion)jcb_estado.getSelectedItem();
				
				try {
					GestorValidaciones.validarEstacion(nombre,apertura,cierre,estado);
					jtp_errores.setText("");
					GestorJDBC.agregarEstacion(GestorEntidades.crearEstacion(nombre,apertura,cierre,estado));
					VentanaPrincipal.popupInfo("Se agrego la Estacion con Exito", "Alta Estacion Exitosa");	
					limpiarCampos();
				}
				catch(DatosDeEstacionIncorrectosException exp) {
					jtp_errores.setText(exp.errores);
				}
			}
		});
		
		jb_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objetos_en_tabla.clear();
				jtable_estaciones_contenido.setRowCount(0);
				try {
					GestorValidaciones.validarFormatoHorariosEstacion(jtf_horario_apertura.getText(),jtf_horario_cierre.getText());
					jtp_errores.setText("");
					
					//llenar la tabla
					for(Estacion est : GestorJDBC.buscarEstacion("",jtf_nombre.getText(),jtf_horario_apertura.getText(),jtf_horario_cierre.getText(),(EstadoEstacion) jcb_estado.getSelectedItem())) {
						jtable_estaciones_contenido.addRow(est.asVector());
						objetos_en_tabla.add(est);
					}
				}
				catch(DatosDeEstacionIncorrectosException exp) {
					jtp_errores.setText(exp.errores);
				}
			}
		});
		
	}
	
	public void limpiarCampos() {
		jtf_nombre.setText("");
		jtf_horario_apertura.setText("");
		jtf_horario_cierre.setText("");
		jcb_estado.setSelectedItem(null);
	}
	
	public void limpiarTabla() {
		objetos_en_tabla.clear();
		jtable_estaciones_contenido.setRowCount(0);
	}
	
}
