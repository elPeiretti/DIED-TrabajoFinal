package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPickerPane extends JPanel{
	
	private static final long serialVersionUID = 9160807192079204636L;
	
	private JColorChooser jcolor_paleta;
	private JButton jb_aceptar;
	private JLabel lbl_seleccionar;
	private JDialog ventana_contenedora;
	
	public ColorPickerPane(JDialog contenedor) {
		setLayout(null);
		this.ventana_contenedora = contenedor;
		
		jcolor_paleta = new JColorChooser();
		jcolor_paleta.setBounds(10, 49, 600, 328);
		
		lbl_seleccionar = new JLabel("Seleccione el color:");
		lbl_seleccionar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_seleccionar.setBounds(10, 11, 207, 27);
		
		jb_aceptar = new JButton();
		jb_aceptar.setText("Aceptar");
		jb_aceptar.setBounds(181, 388, 268, 50);
		
		jb_aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contenedora.setVisible(false);
			}
		});
		
		add(jcolor_paleta);
		add(jb_aceptar);
		add(lbl_seleccionar);
	}
	

	public Color getSelectedColor() {
		return jcolor_paleta.getColor();
	}
	
}
