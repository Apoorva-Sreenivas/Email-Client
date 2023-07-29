package project;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")

public class LoginFrame extends JFrame implements ActionListener
{
	JButton login= new JButton("LOGIN");
	JButton exit = new JButton("EXIT");
	JTextField username;
	JPasswordField password;
	String user;
	char[] pass; 
	ICredentialsHandler handler;
	UserCredentials u1;
	public LoginFrame()
	{
		
		super("Login");
		//JFrame  f = new JFrame();
		setLayout(new BorderLayout());

		
		ImageIcon i1 = new ImageIcon("C:/Users/Apoorva/eclipse-workspace/Email-Client/src/assets/accountcircle.png");
//		ClassLoader.getSystemResource("/email client software/src/project/login_FILL0_wght400_GRAD0_opsz48.png");
//		ImageIcon i1 = new ImageIcon("/email client software/src/project/pngtree-avatar-icon-profile-icon-member-login-vector-isolated-png-image_1978396.jpg");
//		Image i2 = i1.getImage().getScaledInstance(20, 20, NORMAL);
		JLabel image = new JLabel(i1);
//		image.setIcon(new ImageIcon(i2));
//		image.setBounds(450, 160, 100, 20);
		//		image.setIcon(new ImageIcon("login_FILL0_wght400_GRAD0_opsz48.png").);
//		image.setSize(50,50);
		JPanel p0 = new JPanel();
		p0.add(image);
//		Image image = Toolkit.getDefaultToolkit().getImage("pngtree-avatar-icon-profile-icon-member-login-vector-isolated-png-image_1978396.jpg");
//		p0.add(new JLabel(new ImageIcon(image)));
		JPanel p = new JPanel();
		GridLayout l1 = new GridLayout(2,1);
		p.setLayout(l1);
		p.setBorder(new EmptyBorder(10, 20, 10, 20));
		//l1.setHgap(20);
		l1.setVgap(3);
		JPanel p1 = new JPanel();
		JLabel name= new JLabel("Username");
		JLabel passw = new JLabel("Password");
		p.add(name);
		username= new JTextField(45);
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
		
		add(p,BorderLayout.CENTER); //username and password
		add(p0,BorderLayout.NORTH); //image
		add(p1,BorderLayout.SOUTH); //login button
		
//		add(image);
		setSize(600,200);
		
		
		}
	public void show(ICredentialsHandler handler)
	{
		this.handler=handler;
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		JButton pb = (JButton)ae.getSource();
		if(pb==login) {
		user=username.getText()	;
		pass= password.getPassword();
		//UserCredentials user = new UserCredentials();
		//user.setUserName(user);
		u1 = new UserCredentials();
		u1.setUserName(user);
		u1.setPwd(pass);

		this.handler.handleLogin(u1);
		}
		else if(pb==exit)
		{
			System.exit(0);
		}
	}
//	public static void main(String[] args)
//	{
//		new LoginFrame();
//	}

	//this.handler.handleLogin(user, pass);
}
