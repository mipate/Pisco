package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewComponentsFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewComponentsFrame frame = new ViewComponentsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewComponentsFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				System.out.println("windowClosed event raised ");
				dispose(); //this does not make the frame be released from memory, its data remain!
				
			}
		});
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 321);
		
		String ids;
        JTable table;
        String[] columnNames = {"Part No", "Description", "Location", "Qty"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		Connection connection = null;
	      try
	      {
	         // create a database connection
	         //connection = DriverManager.getConnection("jdbc:sqlite:main.db");
	    	 connection = DriverManager.getConnection("jdbc:sqlite:C://SQLite//Main");

	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(30);  // set timeout to 30 sec.


	         
	         
	         String strPartNo = "";
	         String strDescription = "";
	         String strLocation = "";
	         int intQty = 0;
	         int i = 0;
	         


	           ResultSet resultSet = statement.executeQuery("SELECT * from COMPONENTS");
	           while(resultSet.next())
	           {
	              // iterate & read the result set
	        	   strPartNo = resultSet.getString("Part_No");
	        	   strDescription = resultSet.getString("Description");
	        	   strLocation = resultSet.getString("Location");
	        	   intQty = resultSet.getInt("Qty");
	               model.addRow(new Object[]{strPartNo, strDescription, strLocation, intQty});
	               i++;
	        
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
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][]", "[][][][]"));
		
		JLabel lblThisIsAnother = new JLabel("View Components Frame");
		//contentPane.add(lblThisIsAnother, "cell 1 1");
		setTitle("View Components Frame");

		//add(lblThisIsAnother);
		getContentPane().add(scroll);  //this method belongs to the JFrame component
		//contentPane.add(scroll);
		
	}

}
