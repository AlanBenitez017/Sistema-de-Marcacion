import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame1 extends JFrame {
	
	static String driver = "org.postgresql.Driver";
	static String ruta = "jdbc:postgresql://10.99.99.74:5432/erp_comermex_pruebas";
	static String user = "abenitez";
	static String password = "dtmx.2020";
	static JLabel lClock = new JLabel();
	
	public Frame1() {

	}
	
	public static void botonIngreso(JButton ingreso, Timestamp ts){
		try {
			Class.forName(driver);
			Connection conne = DriverManager.getConnection(ruta, user, password);
			Statement consulta = conne.createStatement();
			Date date = new Date();  
            ts=new Timestamp(date.getTime());  
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			consulta.executeUpdate("insert into dbo.registromarcacionlegajo(idtipomarca, ultmodificacion) values('" + 
					ingreso.getText() + "','" + ts.toString()+ "')");
			
			
			/*Connection con = DriverManager.getConnection(ruta, user, password);
			PreparedStatement pst1 = con.prepareStatement("insert into dbo.registromarcacionlegajo(idtipomarca, ultmodificacion) values('" + 
			ingreso.getActionCommand() + "','" + ts.toString()+ "')");
			Date date = new Date();  
            ts = new Timestamp(date.getTime());  
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            lClock.setText(formatter.format(date));
			ResultSet rs = pst1.executeQuery();
			//while (rs.next()) {
				//ingreso.setAction(null);
			System.out.println(rs);*/
			
		        
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "El error esta en: " + e.getMessage());
		}
       
	}
	
	public static void botonEgreso(JButton ingreso, Timestamp ts) {
		try {
			Connection con = DriverManager.getConnection(ruta, user, password);
			PreparedStatement pst1 = con.prepareStatement("insert into dbo.registromarcacionlegajo(idtipomarca, ultmodificacion) values('" + 
			ingreso.getText() + "','" + ts.toString()+ "')");
			Date date = new Date();  
            ts=new Timestamp(date.getTime());  
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            lClock.setText(formatter.format(date));
			pst1.executeQuery();
			
		        
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El error esta en: " + e.getMessage());
		}
       
	}
	
	/*public static void botonIngreso(JButton ingreso, Timestamp ts) {
		try {
			Connection con = DriverManager.getConnection(ruta, user, password);
			PreparedStatement pst1 = con.prepareStatement("insert into dbo.registromarcacionlegajo(idtipomarca, ultmodificacion) values('" + 
			ingreso.getText() + "','" + ts.toString()+ "')");
			Date date = new Date();  
            ts=new Timestamp(date.getTime());  
            //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			pst1.executeQuery();
			
		        
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El error esta en: " + e.getMessage());
		}
       
	}
	
	public static void botonIngreso(JButton ingreso, Timestamp ts) {
		try {
			Connection con = DriverManager.getConnection(ruta, user, password);
			PreparedStatement pst1 = con.prepareStatement("insert into dbo.registromarcacionlegajo(idtipomarca, ultmodificacion) values('" + 
			ingreso.getText() + "','" + ts.toString()+ "')");
			Date date = new Date();  
            ts=new Timestamp(date.getTime());  
            //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			pst1.executeQuery();
			
		        
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "El error esta en: " + e.getMessage());
		}
       
	}*/
	
	/*private void txtLegajoKeyTyped(KeyEvent evt) {
		char cTeclaPresionada = evt.getKeyChar();
		if(cTeclaPresionada == KeyEvent.VK_ENTER) {
			aceptar.doClick();
		}
	}*/
	
	public static void buscarLegajo(JTextField nrolegajo, JLabel datos) {
		
		try {
			Connection con = DriverManager.getConnection(ruta, user, password);
		    PreparedStatement pst1 = con.prepareStatement("SELECT nombre, apellido FROM dbo.legajo WHERE nrolegajo =" + nrolegajo.getText() + " ");
            ResultSet rs = pst1.executeQuery();
    
            nrolegajo.setText(null);
            while (rs.next()) {
            	datos.setText(rs.getString(1) + " " + rs.getString(2));
                System.out.print(rs.getString(1));
                System.out.print(" ");
                System.out.println(rs.getString(2));
            }
            
        } catch (SQLException e) {
        	e.getMessage();
        	JOptionPane.showMessageDialog(null, "El error esta en: " + e.getMessage());
        }

	}
	
	public static void main(String[] args) {
	
		JFrame frame = new JFrame("Sistema de Marcacion");
		JPanel panel = new JPanel();
		
		JLabel lLegajo = new JLabel("Legajo");
		JTextField legajo = new JTextField(10);
		JButton aceptar = new JButton("ACEPTAR");
		JLabel datosLegajo = new JLabel();
		JButton ingreso = new JButton("INGRESO");
		JButton egreso = new JButton("EGRESO");
		JButton salidaAlmuerzo = new JButton("SALIDA ALMUERZO");
		JButton retornoAlmuerzo = new JButton("REGRESO ALMUERZO");
		Date date = new Date();  
        Timestamp ts = new Timestamp(date.getTime());  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        lClock.setText(formatter.format(date));
		
		panel.add(lLegajo);
		panel.add(legajo);
		panel.add(aceptar);
		panel.add(datosLegajo);
		
		frame.add(panel);
	

		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Execute when button is pressed
				buscarLegajo(legajo, datosLegajo);
				panel.add(ingreso);
				panel.add(egreso);
				panel.add(salidaAlmuerzo);
				panel.add(retornoAlmuerzo);
					/*if(e.getSource() == ingreso) {
						
					}
					else if(e.getSource() == egreso) {
						
					}
					else if(e.getSource() == salidaAlmuerzo) {
						
					}
					else if(e.getSource() == retornoAlmuerzo) {
						
					}*/
				JOptionPane.showMessageDialog(null, "Al fin me salio no te puedo creer");
			}

		});
		
		aceptar.addKeyListener(new KeyListener() {


			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					aceptar.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		ingreso.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				botonIngreso(ingreso, ts);
				frame.add(lClock, BorderLayout.SOUTH);
				
			}
		
		
		});
		
		ingreso.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					ingreso.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		egreso.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					egreso.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		salidaAlmuerzo.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					salidaAlmuerzo.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		retornoAlmuerzo.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char cTeclaPresionada = e.getKeyChar();
				if(cTeclaPresionada == KeyEvent.VK_ENTER) {
					retornoAlmuerzo.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		
		
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
}
