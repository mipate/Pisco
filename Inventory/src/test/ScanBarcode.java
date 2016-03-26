package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ScanBarcode extends JFrame {

	private JPanel contentPane;
	private JTextField txtBarcode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScanBarcode frame = new ScanBarcode();
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
	public ScanBarcode() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow]", "[][]"));
		
		txtBarcode = new JTextField();
		txtBarcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("actionPerformed event trigerred");
				
				
				
			}
		});
		contentPane.add(txtBarcode, "cell 2 1,growx");
		txtBarcode.setColumns(10);
	}

}
