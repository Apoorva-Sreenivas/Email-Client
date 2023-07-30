//package project;
//
//import java.awt.event.*;
////import java.io.*;
//import java.awt.*;
//import javax.swing.*;
////import javax.mail.*;
//import javax.swing.border.EmptyBorder;
//
//@SuppressWarnings("serial")
//public class SendFrame extends JFrame implements ActionListener
//{
//	JTextField subject,to;
//	JTextArea content;
//	JScrollPane scroll;
//	JButton send;
//	JPanel p,p1,p2;
//	String to1,subject1,content1;
//	//UserCredentials u1;
//	
//	public SendFrame()
//	{
//		super("Compose");
//		setLayout(new BorderLayout());
//		GridLayout l3 = new GridLayout(4,2);
//		l3.setVgap(3);
//		p = new JPanel(l3);
//		p.setBorder(new EmptyBorder(10, 10, 10, 10));
//		p.add(new JLabel("To"));
//		to= new JTextField();
//		p.add(to);
//		p.add(new JLabel("Subject"));
//		subject= new JTextField();
//		p.add(subject);
//		p.add(new JLabel("Text"));
//		p2 = new JPanel();
//		p2.setBorder(new EmptyBorder(10, 20, 10, 20));
//		content= new JTextArea(50,50);
//		content.setEditable(true);
//
////		content.setSize(500,500);
//		
//		p2.add(content);
////		text.setSize(30,72);
////		text.scrollRectToVisible(getBounds());
////		text.setLineWrap(true);
////		scroll = new JScrollPane(content);
////		 scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
////	        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
//////	        scroll.setMaximumSize(new Dimension(400,400));
////	        scroll.setPreferredSize(new Dimension(700,600));	
//////		p2.add(text);
////		p2.add(scroll);
//		
////		  text.setWrapStyleWord(true);
////		  scroll = new JScrollPane(text);
////		scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
////				 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
////		p.add(scroll);
//		p1 = new JPanel();
//		p1.setBorder(new EmptyBorder(10, 10, 10, 10));
////		ImageIcon i3 = new ImageIcon("C:/Users/Apoorva/eclipse-workspace/email client software/src/assets/lsend.png");
//		send = new JButton("SEND");
//		send.addActionListener(this);
//		p1.add(send);
//		
//		add(p,BorderLayout.NORTH);
//		add(p2,BorderLayout.WEST);
//		add(p1,BorderLayout.SOUTH);
//		setSize(800,800);
//		setVisible(true);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent ae) {
//		// TODO Auto-generated method stub
////		JButton pb=(JButton)ae.getSource();
////		//u1 = new UserCredentials();
////		if(pb==send) {
//		to1 = to.getText();
//		subject1 = subject.getText();
//		content1 =content.getText();
////		System.out.println("Function entered");
//		SendMail sendmsg = new SendMail();
//		sendmsg.send(to1, subject1, content1);
//		this.setVisible(false);
////		}
//	}
//	
//
//	
//	
//	
//
//}
package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;

@SuppressWarnings("serial")
public class SendFrame extends JFrame implements ActionListener {
    JTextField subject, to, attachmentField;
    JTextArea content;
    JButton send, browse;
    JPanel p, p1, p2;
    String to1, subject1, content1, attachmentPath;

    public SendFrame() {
        super("Compose");
        setLayout(new BorderLayout());
        GridLayout l3 = new GridLayout(4, 2);
        l3.setVgap(3);
        p = new JPanel(l3);
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        p.add(new JLabel("To"));
        to = new JTextField();
        p.add(to);
        p.add(new JLabel("Subject"));
        subject = new JTextField();
        p.add(subject);
        p.add(new JLabel("Attachment"));
        attachmentField = new JTextField();
        p.add(attachmentField);
        p.add(new JLabel(""));
        browse = new JButton("Browse");
        browse.addActionListener(this);
        p.add(browse);
        p2 = new JPanel();
        p2.setBorder(new EmptyBorder(10, 20, 10, 20));
        content = new JTextArea(50, 50);
        content.setEditable(true);
        p2.add(content);
        p1 = new JPanel();
        p1.setBorder(new EmptyBorder(10, 10, 10, 10));
        send = new JButton("SEND");
        send.addActionListener(this);
        p1.add(send);
        add(p, BorderLayout.NORTH);
        add(p2, BorderLayout.WEST);
        add(p1, BorderLayout.SOUTH);
        setSize(800, 800);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton pb = (JButton) ae.getSource();
        if (pb == send) {
            to1 = to.getText();
            subject1 = subject.getText();
            content1 = content.getText();

            SendMail sendmsg = new SendMail();
            sendmsg.send(to1, subject1, content1, attachmentPath);
            this.setVisible(false);
        } else if (pb == browse) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                attachmentPath = selectedFile.getAbsolutePath();
                attachmentField.setText(attachmentPath);
            }
        }
    }
}

