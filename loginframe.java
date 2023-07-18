import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class loginframe extends JFrame implements ActionListener {
    JTextField t_us;
    JTextField t_pas;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JRadioButton userRad;
    JRadioButton adminRad;
    JButton submit;
    JButton signup_but;
    JLabel message;
    public loginframe() {
        setTitle("E-commerce Login");
        JLabel headlineLabel = new JLabel("LOGIN DASHBOARD");
        headlineLabel.setBounds(110, 10, 200, 30);
        headlineLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headlineLabel);
        
        
       // Create username label and field
        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100,50,100,50);
        t_us = new JTextField();
        t_us.setBounds(200,65,70,20);
        add(usernameLabel);
        add(t_us);

        // Create password label and field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100,90,100,50);
        t_pas = new JTextField();
        t_pas.setBounds(200,105,70,20);
        add(passwordLabel);
        add(t_pas);

        // Create submit button
        submit = new JButton("Submit");
        submit.setBounds(150,160,80,30);
        add(submit);
        submit.addActionListener(this);
        
        // Create signup button
        signup_but= new JButton("Sign Up");
        signup_but.setBounds(240,240,80,30);
        
        add(signup_but);
        signup_but.addActionListener(this) ;
        
        message=new JLabel("Dont have an account ?");
        message.setBounds(100,230,200,50);
        add(message);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
    	loginframe loginpage = new loginframe();
        
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==submit) {
			String user = t_us.getText();
			String pass = t_pas.getText();
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gitam", "root", "Denuka");
				PreparedStatement ps = con.prepareStatement("insert into login values(?,?,?)");
				ps.setInt(1, 0);
				ps.setString(2, user);
				ps.setString(3, pass);

				ps.execute();
				//System.out.println("Login Successful");
				JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
				UserDashboard userdashboard=new UserDashboard();
				userdashboard.setVisible(true);
				this.dispose();
			
			} 
			catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "invalid login details!", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		else if (e.getSource()==signup_but) {
			
			new userRegistrationForm();
			System.out.println("signup completed");
			this.dispose();
		}
	}
	}