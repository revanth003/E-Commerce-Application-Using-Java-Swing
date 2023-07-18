import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class userRegistrationForm extends JFrame implements ActionListener {

	private JTextField firstNameField;
	private JTextField pincodeField;
	private JTextField MobileNoField;
	private JTextField AddressField;
	private JTextField DateField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JButton registerButton;

	public userRegistrationForm() {
		setTitle("User Registration Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(8, 2));

		JLabel firstNameLabel = new JLabel("Username:");
		firstNameField = new JTextField();

		JLabel lastNameLabel = new JLabel("Pincode:");
		pincodeField = new JTextField();

		JLabel MobileNoLabel = new JLabel("Mobile number:");
		MobileNoField = new JTextField();

		JLabel addressLabel = new JLabel("Address:");
		AddressField = new JTextField();

		JLabel dateLabel = new JLabel("DOB:");
		DateField = new JTextField();

		JLabel emailLabel = new JLabel("Email:");
		emailField = new JTextField();

		JLabel passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField();

		registerButton = new JButton("Register");
		registerButton.addActionListener(this);

		add(firstNameLabel);
		add(firstNameField);
		add(passwordLabel);
		add(passwordField);
		add(emailLabel);
		add(emailField);
		add(MobileNoLabel);
		add(MobileNoField);
		add(dateLabel);
		add(DateField);
		add(addressLabel);
		add(AddressField);
		add(lastNameLabel);
		add(pincodeField);

		add(registerButton);

		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new userRegistrationForm();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(registerButton)) {

			String firstName = firstNameField.getText();
			String pin = pincodeField.getText();
			int pincode = Integer.parseInt(pin);
			String mobile = MobileNoField.getText();
			int mobileNo = Integer.parseInt(mobile);
			String address = AddressField.getText();
			String Dob = DateField.getText();
			String email = emailField.getText();
			String password = new String(passwordField.getPassword());

			// Perform registration logic here
			// You can print or process the values as needed

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gitam", "root", "Denuka");
				PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?,?,?,?,?)");
				ps.setInt(1, 0);
				ps.setString(2, firstName);
				ps.setString(3, password);
				ps.setString(4, email);
				ps.setInt(5, mobileNo);
				ps.setString(6, Dob);
				ps.setString(7, address);
				ps.setInt(8, pincode);

				ps.execute();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println("Username: " + firstName);
			System.out.println("Password: " + password);
			System.out.println("Email: " + email);
			System.out.println("Mobile No: " + mobileNo);
			System.out.println("DOB: " + Dob);
			System.out.println("Address: " + address);
			System.out.println("Pincode: " + pin);
			this.dispose();
			new loginframe();
//             if (checkRegistrationFields()) {
//                 System.out.println("Signup completed");
//                 showSignupSucessMessage();
//             } else {
//                 showEmptyFieldsMessage();
//             }

			// Clear the form fields after registration
			firstNameField.setText("");
			pincodeField.setText("");
			MobileNoField.setText("");
			AddressField.setText("");
			DateField.setText("");
			emailField.setText("");
			passwordField.setText("");
		}
	}

//	private void showSignupSucessMessage() {
//		// TODO Auto-generated method stub
//		JOptionPane.showMessageDialog(this, "Signup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
//		
//	}
//	private void showEmptyFieldsMessage() {
//        JOptionPane.showMessageDialog(this, "Username and Password are mandatory", "Error", JOptionPane.ERROR_MESSAGE);
//    }
//	private boolean checkRegistrationFields() {
//        String username = firstNameField.getText();
//        String password = passwordField.getText();
//
//        return !username.isEmpty() && !password.isEmpty();
//    }

}