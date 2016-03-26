package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import java.awt.event.ActionListener;

public class Aplicatie {

	private JFrame frmInventory, frame3, frameEnterComponents, frameFindComponents;
	//private Object frame4;
	private JTextField textField;
	private final Action action = new SwingAction();
	private JMenuBar menuBar;
	private JButton btnNewButton_1;
	private JMenu mnNewMenu;
	private JMenuItem mntmListComponents;
	private JMenuItem mntmEnterComponents;
	private JMenuItem mntmFindComponents;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicatie window = new Aplicatie();
					window.frmInventory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Aplicatie() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInventory = new JFrame();
		frmInventory.setTitle("Inventory");
		//frame3 = new JFrame();  //This works
		frame3 = new ViewComponentsFrame();  //This also works
		frameEnterComponents = new EnterComponents();
		frameFindComponents = new FindComponents();
		
		
		//frame4 = new windowTest();
		
		//frame3.getContentPane().setLayout(new MigLayout("", "[][][][grow]", "[][][][][][][]"));
		frmInventory.getContentPane().setLayout(new MigLayout("", "[][][][grow]", "[][][][][][][]"));
		
		JButton btnNewButton = new JButton("Test baza date");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField.setText("Ati apasat butonul");
				
				System.out.println("Ma pregatesc sa ma conectez la baza");
				
				try {
					Class.forName("org.sqlite.JDBC");
					//Class.forName("jdbc:sqlite:/C:/SQLite/Main");
				} catch (ClassNotFoundException e1) {
					
					// TODO Auto-generated catch block
					System.out.println("Eroare de conectare la baza");
					e1.printStackTrace();
				}

			      Connection connection = null;
			      try
			      {
			         // create a database connection
			         //connection = DriverManager.getConnection("jdbc:sqlite:main.db");
			    	 connection = DriverManager.getConnection("jdbc:sqlite:C://SQLite//Main");

			         Statement statement = connection.createStatement();
			         statement.setQueryTimeout(30);  // set timeout to 30 sec.


			         statement.executeUpdate("DROP TABLE IF EXISTS person");
			         statement.executeUpdate("CREATE TABLE person (id INTEGER, name STRING)");

			         int ids [] = {1,2,3,4,5};
			         String names [] = {"Peter","Pallar","William","Paul","James Bond"};

			         for(int i=0;i<ids.length;i++){
			              statement.executeUpdate("INSERT INTO person values(' "+ids[i]+"', '"+names[i]+"')");   
			         }

			         //statement.executeUpdate("UPDATE person SET name='Peter' WHERE id='1'");
			         //statement.executeUpdate("DELETE FROM person WHERE id='1'");

			           ResultSet resultSet = statement.executeQuery("SELECT * from person");
			           while(resultSet.next())
			           {
			              // iterate & read the result set
			              System.out.println("name = " + resultSet.getString("name"));
			              System.out.println("id = " + resultSet.getInt("id"));
			           }
			          }

			     catch(SQLException e){  System.err.println(e.getMessage()); }       
			      finally {         
			            try {
			                  if(connection != null)
			                     connection.close();
			                  }
			            catch(SQLException e) {  // Use SQLException class instead.          
			               System.err.println(e); 
			             }
			      }
				
			}
		});
		
		textField = new JTextField();
		frmInventory.getContentPane().add(textField, "cell 3 1,growx");
		textField.setColumns(10);
		frmInventory.getContentPane().add(btnNewButton, "cell 2 4");
		
		btnNewButton_1 = new JButton("Test Frame");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//windowTest fereastra = new windowTest();
				
				//fereastra.main(null);
				//fereastra.setVisible(true);
				//frame2.show();
				//frame.hide();
				frame3.setVisible(true);  // this works
			}
		});
		frmInventory.getContentPane().add(btnNewButton_1, "cell 2 6");
		
		menuBar = new JMenuBar();
		frmInventory.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Components");
		menuBar.add(mnNewMenu);
		
		mntmListComponents = new JMenuItem("View components");
		mntmListComponents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {  // this does not trigger,why?
				//System.out.println("Menu item View Component sselected");
				//textField.setText("Menu item View Component -Mouse clicked"); 
				//frame3.setVisible(true);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//textField.setText("Menu item View Component -Mouse pressed");  //this works
				frame3.setVisible(true);
			}
		});
		mnNewMenu.add(mntmListComponents);
		
		mntmEnterComponents = new JMenuItem("Enter components");
		mntmEnterComponents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameEnterComponents.setVisible(true);
			}
		});
		mnNewMenu.add(mntmEnterComponents);
		
		mntmFindComponents = new JMenuItem("Find components");
		mntmFindComponents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameFindComponents.setVisible(true);
			}
		});
		mnNewMenu.add(mntmFindComponents);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
