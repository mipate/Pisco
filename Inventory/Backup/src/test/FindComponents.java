package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FindComponents extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindComponents frame = new FindComponents();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DefaultTableModel  DisplayRecords(String p_strPart_No, String p_strDescription, String p_strLocation)
	{
		
		String ids;
        JTable table;
        String[] columnNames = {"Part No", "Description", "Location", "Qty"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
		
        String strWhere = "";
        
        if (p_strPart_No.length() != 0)
        	strWhere = strWhere + "WHERE Part_No LIKE '" + p_strPart_No + "' ";
        
        if (p_strDescription.length() != 0)
        	if (strWhere.length() != 0)
        		strWhere = strWhere + "AND Description LIKE '" + p_strDescription + "' ";
        	else 
        		strWhere = strWhere + "WHERE Description LIKE '" + p_strDescription + "' ";
        
        if (p_strLocation.length() != 0)
        	if (strWhere.length() != 0)
        		strWhere = strWhere + "AND Location LIKE '" + p_strLocation + "' ";
        	else 
        		strWhere = strWhere + "WHERE Location LIKE '" + p_strLocation + "' ";
        
        strWhere = strWhere + ";";
		
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
	         
	         System.out.println("strWhere = " + strWhere);

	           ResultSet resultSet = statement.executeQuery("SELECT * from COMPONENTS " + strWhere);
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
		
	     //getContentPane().add(scroll, "cell 1 7");
	     
	     return model;
	
	}
	
	/**
	 * Create the frame.
	 */
	public FindComponents() {
		setTitle("Find components");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][]", "[][][]"));
		
		JTable table = new JTable();
		
		JLabel lblPartNumber = new JLabel("Part Number:");
		contentPane.add(lblPartNumber, "cell 0 0,alignx trailing");
		
		JTextField txtPart_No = new JTextField();
		txtPart_No.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				System.out.println("Key typed event with no compponents:" + getContentPane().countComponents());
				
				
				
				//if (getContentPane().countComponents() > 6)
				//getContentPane().remove(6); //this should remove the scroll because we want to add it again
				
				
				
			}// end of keyTyped
		});
		contentPane.add(txtPart_No, "cell 1 0,growx");
		txtPart_No.setColumns(10);
		
		//JTextField txtJoaca = new JTextField();
		//contentPane.add(txtJoaca, "cell 1 6,grow");
		//txtJoaca.setVisible(false);
		
		
		
		JLabel lblDescription = new JLabel("Description:");
		contentPane.add(lblDescription, "cell 0 1,alignx trailing");
		
		JTextField txtDescription = new JTextField();
		contentPane.add(txtDescription, "cell 1 1,grow");
		
		JLabel lblLocation = new JLabel("Location:");
		contentPane.add(lblLocation, "cell 0 2,alignx trailing");
		
		JTextField txtLocation = new JTextField();
		contentPane.add(txtLocation, "cell 1 2,growx");
		txtLocation.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("Find button is pressed");
				
				table.setModel(DisplayRecords(txtPart_No.getText(), txtDescription.getText(), txtLocation.getText()));
				
			}  // this is from ActionPerformed
		});
		contentPane.add(btnFind, "cell 5 0");
		
        //table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        getContentPane().add(scroll, "cell 1 6");
		
		/*
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
		
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new MigLayout("", "[][]", "[][][][]"));
		
		getContentPane().add(scroll, "cell 1 6");  //this method belongs to the JFrame component
		*/
		
	}

}
