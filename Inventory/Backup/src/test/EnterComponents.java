package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class EnterComponents extends JFrame {

	private JPanel contentPane;
	private JTextField txtPart_No;
	private JTextField txtLocation;
	private JTextField txtQty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterComponents frame = new EnterComponents();
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
	public EnterComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][]", "[][][][grow][][][][]"));
		
		JLabel lblEnterComponents = new JLabel("Enter Components");
		lblEnterComponents.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblEnterComponents, "cell 1 0");
		
		JLabel lblPartNumber = new JLabel("Part Number:");
		contentPane.add(lblPartNumber, "cell 0 2,alignx trailing");
		
		txtPart_No = new JTextField();
		contentPane.add(txtPart_No, "cell 1 2,growx");
		txtPart_No.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		contentPane.add(lblDescription, "cell 0 3,alignx trailing");
		
		JTextArea txtAreaDescription = new JTextArea();
		txtAreaDescription.setBorder(new LineBorder(Color.GRAY));
		contentPane.add(txtAreaDescription, "cell 1 3,grow");
		
		JLabel lblLocation = new JLabel("Location:");
		contentPane.add(lblLocation, "cell 0 5,alignx trailing");
		
		txtLocation = new JTextField();
		contentPane.add(txtLocation, "cell 1 5,growx");
		txtLocation.setColumns(10);
		
		JLabel lblQty = new JLabel("Qty:");
		contentPane.add(lblQty, "cell 0 6,alignx trailing");
		
		txtQty = new JTextField();
		contentPane.add(txtQty, "cell 1 6,growx,aligny top");
		txtQty.setColumns(10);
		
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection connection = null;
				try
			      {
					
					connection = DriverManager.getConnection("jdbc:sqlite:C://SQLite//Main");
	
			        Statement statement = connection.createStatement();
			        statement.setQueryTimeout(30);  // set timeout to 30 sec.
			        
			        String strPart_No = txtPart_No.getText();
			        String strDescription = txtAreaDescription.getText();
			        String strLocation = txtLocation.getText();
			        String strQty = txtQty.getText();
			        int intQty = 0;
			        boolean blOK = true;
			        
			        if (strQty.length() != 0)
			        	intQty = Integer.parseInt(strQty);
			        
			        if (strPart_No.length() == 0)//(strPart_No == "")
			        	{
			        			JOptionPane.showConfirmDialog(null, "Part No can not be empty", "Result", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
			        			blOK = false;
			        	}
			        if (blOK)
			        {
			        	statement.executeUpdate("insert into components(Part_No, Description, Location, Qty) values('"+strPart_No+"','"+strDescription+"','"+strLocation+"',"+intQty+")");
			        	JOptionPane.showConfirmDialog(null, "Your Data Has been Inserted", "Result", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
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
		contentPane.add(btnSave, "cell 2 6");
	}

}
