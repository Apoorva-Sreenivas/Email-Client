package project;

import javax.swing.*;

@SuppressWarnings("serial")
public class ErrorMessage extends JFrame 
{
	
	public ErrorMessage(String x)
	{
//		super("Error");
//		setLayout(new BorderLayout());
		JPanel p = new JPanel();
//		JLabel err = new JLabel(x);
//		JOptionPane err = new JOptionPane(x);
//		err.addActionListener(this);
		JOptionPane.showInternalMessageDialog(p, x,"Error",JOptionPane.ERROR_MESSAGE);
//		err.setText("<HTML> Line1<br> Line2</HTML>");
//		p.add(err);
//		add(p,BorderLayout.CENTER);
//		setSize(700,200);
//		setVisible(true);	
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		this.setVisible(false);
//}

//	public static void main (String[] args)
//	{
//		new ErrorMessage("javax.mail.AuthenticationFailedException");
//	}
//	
	

}
