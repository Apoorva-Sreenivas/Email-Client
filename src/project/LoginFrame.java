package project;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")

public class LoginFrame extends JFrame implements ActionListener {
	JButton login = new JButton("LOGIN");
	JButton exit = new JButton("EXIT");
	JTextField username;
	JPasswordField password;
	String user;
	char[] pass;
	ICredentialsHandler handler;
	UserCredentials u1;

	public LoginFrame() {

		super("Login");
		setLayout(new BorderLayout());
		ImageIcon i1 = new ImageIcon("C:/Users/Apoorva/eclipse-workspace/Email-Client/src/assets/accountcircle.png");
		JLabel image = new JLabel(i1);
		JPanel p0 = new JPanel();
		p0.add(image);
		JPanel p = new JPanel();
		GridLayout l1 = new GridLayout(2, 1);
		p.setLayout(l1);
		p.setBorder(new EmptyBorder(10, 20, 10, 20));
		l1.setVgap(3);
		JPanel p1 = new JPanel();
		JLabel name = new JLabel("Username");
		JLabel passw = new JLabel("Password");
		p.add(name);
		username = new JTextField(45);
		username.setText("apoorvasreenivas.cse.22@gmail.com");
		p.add(username);
		p.add(passw);
		password = new JPasswordField(45);
		password.setText("tyswncocuxprkgaa");
		p.add(password);
		exit.addActionListener(this);
		login.addActionListener(this);
		p1.add(login);
		p1.add(exit);
		add(p, BorderLayout.CENTER);
		add(p0, BorderLayout.NORTH);
		add(p1, BorderLayout.SOUTH);
		setSize(600, 200);
	}

	public void show(ICredentialsHandler handler) {
		this.handler = handler;
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		JButton pb = (JButton) ae.getSource();
		if (pb == login) {
			user = username.getText();
			pass = password.getPassword();
			u1 = new UserCredentials();
			u1.setUserName(user);
			u1.setPwd(pass);

			this.handler.handleLogin(u1);
		} else if (pb == exit) {
			System.exit(0);
		}
	}

}
