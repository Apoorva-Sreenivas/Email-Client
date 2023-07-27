package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class ShowMessage extends JFrame implements ActionListener
{
	public ShowMessage()
	{
		super("Message");
		
		setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JLabel sent = new JLabel("Message sent");
		p1.add(sent,BorderLayout.CENTER);
		JButton ok = new JButton("OK");
		ok.setSize(20,20);
		ok.addActionListener(this);
		p2.add(ok,BorderLayout.SOUTH);
		add(p1,BorderLayout.CENTER);
		add(p2,BorderLayout.SOUTH);
		setSize(200,100);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
	
//	public static void main(String[] args)
//	{
//		new ShowMessage();
//	}
	

}
