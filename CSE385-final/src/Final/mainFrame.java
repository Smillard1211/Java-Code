package Final;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainFrame extends JFrame  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

	public void readDataBase() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//*******************************************************************
			//*******************************************************************
			String username = "root";//change this to your MySQL server user name
			String password = "1234";//change this to your MySQL server password
			//*******************************************************************
			//*******************************************************************
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?"+ "user="+username+"&password="+password);
			
			//this creates a statement object which is the thing that is actually sent to the MySQL database
			//MySQL does not process Strings, or arrays or anything else, it only processes Statement objects
			//So here we are preparing the statement that will eventually store the SQL query that we want to
			//send to the DB
			statement = connect.createStatement(); 
			
			
			resultSet = statement.executeQuery("Select * from classicModels.customers;");
			//writeCustomerData(resultSet); //custom method call. We will write this.
			//writeMetaData(resultSet);
			
			
		}//end try
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}//end catch
		finally {
			//closeResultset(); //custom method that we will write;
		}
	}//end readDataBase
	
	private void writeCustomerData(ResultSet resultSet) throws SQLException{
		while(resultSet.next()) {
		 int customerNumber = resultSet.getInt("customerNumber");
		 String customerName = resultSet.getString("customerName");
		 double creditLimit = resultSet.getDouble("creditLimit");
		 System.out.print("customer number: "+ customerNumber);
		 System.out.print(" cutomer name: "+ customerName);
		 System.out.println(" creditLimit: "+ creditLimit);
		}//end while
	}//end writeResultSet
	
	private void getCustomerData() throws SQLException{
		resultSet.next();
		 int customerNumber = resultSet.getInt("customerNumber");
		 String customerName = resultSet.getString("customerName");
		 double creditLimit = resultSet.getDouble("creditLimit");
		 System.out.print("customer number: "+ customerNumber);
		 System.out.print(" cutomer name: "+ customerName);
		 System.out.println(" creditLimit: "+ creditLimit);
	}//end writeResultSet
	
	private void writeMetaData(ResultSet resultSet) throws SQLException {
		System.out.println("*******************************************************************");
		System.out.println("the columns in the table are: ");
		
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }//end for
		
	}//end writeMetaData
	
	private void closeResultset() {
		try {
			if(resultSet != null) {
				resultSet.close();
			}//end if
			if(resultSet != null) {
				resultSet.close();
			}//end if
			if(resultSet != null) {
				resultSet.close();
			}//end if
			
		}//end try
		catch(Exception e) {
			e.printStackTrace();
		}//end catch
	}//end close
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.readDataBase();					
					//frame.windowManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/*
	private void windowManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton);
	
	}
	*/
	
	public mainFrame()throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(160, 101, 75, 62);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Customer Name");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setText("asdghj");
				try {
					getCustomerData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(146, 10, 129, 28);
		contentPane.add(btnNewButton);
	}

}
