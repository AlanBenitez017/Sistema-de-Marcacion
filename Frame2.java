import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame2 extends JFrame {
	
	public Frame2() {

	}
	
	public static void main(String[] args) {
	
		JFrame frame = new JFrame("Segunda Pagina");
		JPanel panel = new JPanel();
		
		
		JButton ingreso = new JButton("INGRESO");
		JButton egreso = new JButton("EGRESO");
		JButton salidaAlmuerzo = new JButton("SALIDA ALMUERZO");
		JButton vueltaAlmuerzo = new JButton("VUELTA ALMUERZO");
		
		
		
		panel.add(ingreso);
		panel.add(egreso);
		panel.add(salidaAlmuerzo);
		panel.add(vueltaAlmuerzo);
		
		frame.add(panel);
		
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
