package interfaces.boletos;

import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaces.VentanaPrincipal;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuRegistrarComprador extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 457210643685330418L;
	
	private JTextField jtf_nombre_cliente;
	private JTextField jtf_email_cliente;
	private JLabel lbl_nombre_cliente;
	private JLabel lbl_email_cliente;
	private JButton jb_regresar;
	private JButton jb_registrar_venta;
	private JLabel lbl_origen_destino;
	private JLabel lbl_costo_boleto;
	private VentanaPrincipal ventana_contenedora;

	/**
	 * Create the panel.
	 */
	public MenuRegistrarComprador(VentanaPrincipal contenedor) {
		this.ventana_contenedora=contenedor;
		setLayout(null);
		
		jtf_nombre_cliente = new JTextField();
		jtf_nombre_cliente.setBounds(245, 125, 86, 20);
		jtf_nombre_cliente.setColumns(10);
		
		lbl_nombre_cliente = new JLabel("Nombre del cliente:");
		lbl_nombre_cliente.setBounds(116, 128, 99, 14);
		
		jtf_email_cliente = new JTextField();
		jtf_email_cliente.setBounds(245, 156, 86, 20);
		jtf_email_cliente.setColumns(10);
		
		lbl_email_cliente = new JLabel("E-Mail del cliente:");
		lbl_email_cliente.setBounds(116, 159, 99, 14);
		
		jb_regresar = new JButton("Regresar");
		jb_regresar.setBounds(10, 266, 89, 23);
		
		jb_registrar_venta = new JButton("Registrar Venta");
		jb_registrar_venta.setBounds(333, 266, 107, 23);
		
		lbl_origen_destino = new JLabel("Viaje desde: <Estacion Origen> hasta: <Estacion Destino>");
		lbl_origen_destino.setBounds(77, 70, 292, 14);
		
		lbl_costo_boleto = new JLabel("Costo del boleto: $<costo>");
		lbl_costo_boleto.setBounds(77, 88, 156, 14);
		
		this.agregarActionListener();
		add(jtf_nombre_cliente);
		add(lbl_nombre_cliente);
		add(jtf_email_cliente);
		add(lbl_origen_destino);
		add(jb_registrar_venta);
		add(jb_regresar);
		add(lbl_email_cliente);
		add(lbl_costo_boleto);
		
	}
	
	private void agregarActionListener() {
		jb_regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// falta la logica para recuperar el estado anterior
				ventana_contenedora.cambiarPanel(VentanaPrincipal.SELEC_RECORRIDO);
			}
		});
	}
}
