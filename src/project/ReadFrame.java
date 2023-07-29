package project;
//
//import java.awt.event.*;
//import java.io.*;
//import java.awt.*;
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import javax.mail.*;
//
//
//@SuppressWarnings("serial")
//public class ReadFrame extends JFrame implements ActionListener
//{
//	ErrorMessage err;
//	JLabel emailNo,subject,from;
//	JTextArea text;
//	JButton next,prev,compose;
//	JPanel p,p1,p2;
//	JScrollPane scroll;
//	int curMsgIndex;
//	private Message[] messages;
//	
//	public ReadFrame()
//	{
//		super("Read");
//		setLayout(new BorderLayout());
//		GridLayout l2 = new GridLayout(4,2);
//		p = new JPanel(l2);
//		l2.setVgap(3);
//		p.setBorder(new EmptyBorder(10, 20, 10, 20));
//		p.add(new JLabel("Email Number"));
//		emailNo= new JLabel();
//		p.add(emailNo);
//		p.add(new JLabel("From"));
//		from= new JLabel();
//		p.add(from);
//		p.add(new JLabel("Subject"));
//		subject= new JLabel();
//		p.add(subject);
//		p.add(new JLabel("Text"));
//		p2 = new JPanel();
//		p2.setBorder(new EmptyBorder(10, 10, 10, 10));
//		text= new JTextArea();
//
//		text.setSize(5,30);
////		text.setSize(30,72);
////		text.scrollRectToVisible(getBounds());
//		text.setLineWrap(true);
//		scroll = new JScrollPane(text);
//		 scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
//	        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
////	        scroll.setMaximumSize(new Dimension(400,400));
//	        scroll.setPreferredSize(new Dimension(700,600));	
////		p2.add(text);
//		p2.add(scroll);
////		  text.setWrapStyleWord(true);
////		  scroll = new JScrollPane(text);
////		scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
////				 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
////		p.add(scroll);
//		p1 = new JPanel();
//		next = new JButton("NEXT");
//		next.addActionListener(this);
//		prev = new JButton("PREV");
//		prev.addActionListener(this);
////		ImageIcon i2 = new ImageIcon("C:/Users/Apoorva/eclipse-workspace/email client software/src/assets/edit.png");
////		Image i3 = i2.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT );
//		compose = new JButton("COMPOSE");
//		compose.addActionListener(this);
//		p1.add(prev);
//		p1.add(next);
//		p1.add(compose);
////		JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 300);
////	    JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 300);
////
////	    hbar.setUnitIncrement(2);
////	    hbar.setBlockIncrement(1);
////
////	    hbar.addAdjustmentListener(new MyAdjustmentListener());
////	    vbar.addAdjustmentListener(new MyAdjustmentListener());
////	    add(hbar, BorderLayout.SOUTH);
////	    add(vbar, BorderLayout.EAST);
//		
//		add(p,BorderLayout.NORTH);
//		add(p2,BorderLayout.WEST);
//		add(p1,BorderLayout.SOUTH);
//		setSize(800,800);
//		setVisible(true);
//			
//	}
//
//	public void actionPerformed(ActionEvent ae)
//	{
//		JButton pb=(JButton)ae.getSource();
//		if(pb==next||pb==prev) {
//			if(pb==next&& curMsgIndex<messages.length-1)
//				curMsgIndex++;
//			else if(pb==prev && curMsgIndex>0)
//				curMsgIndex--;
//			else if(pb==next&&curMsgIndex==messages.length-1)
//				curMsgIndex=0;
//			else if(pb==prev&&curMsgIndex==0||curMsgIndex==-1)
//				curMsgIndex= messages.length-1;
//			try {
//				updateMail();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//	//			e.printStackTrace();
//				err=new ErrorMessage(e.toString()); 
//			}
//		}
//		if(pb==compose)
//		{
//			new SendFrame();
//		}
//
//	}
//	
//	public  void updateMail() throws IOException
//	{
//		Message msg = messages[curMsgIndex];
//		emailNo.setText(Integer.toString(curMsgIndex+1));
//		Address addr;
//		try {
//			addr = msg.getFrom()[0];
//			from.setText(addr.toString());
//			subject.setText(msg.getSubject());
//			//Object contents = msg.getContent();
//			String content = convertMessage();
//			
//			//System.out.println(content);
//			text.setText(content);
//		}
//		catch (MessagingException e) {
////			e.printStackTrace();
//			err=new ErrorMessage(e.toString()); 
//		}
//		
//	}
//	public void windowClosing(WindowEvent e)
//	{
//		RecieveMail r1 = new RecieveMail();
//		r1.closeMail();
//	}
//	
////	public static void main(String[] args)
////	{
////		new ReadFrame();
////	}
//	public void setMessages(Message[] message) {
//		// TODO Auto-generated method stub
//		curMsgIndex = -1;
//		this.messages = message;
//		
//		
//	}
//	private String convertMessage()
//	{
//		Message msg = messages[curMsgIndex];
//
//		try {
//			Object content = msg.getContent();
//			StringBuffer msgs = new StringBuffer();
//		StringBuffer messageContent = new StringBuffer();
//		 Multipart multipart = (Multipart) content;
//		 String msg1 = new String();
//         for (int i = 0; i < multipart.getCount(); i++) 
//         {
//             Part part = (Part) multipart.getBodyPart(i);
//             
////             System.out.println(part.getContentType());
//             if(!part.isMimeType("text/html"))
//             {
//            	 msgs = messageContent.append(part.getContent().toString());
//             }
//                 msg1 = msgs.toString();
////                 System.out.println(msg1);
//         }
//         return msg1;
////         return messageContent.toString();
//		}
//         catch (IOException e) {
// 			// TODO Auto-generated catch block
//// 			e.printStackTrace();
//        	 err=new ErrorMessage(e.toString()); 
// 		} catch (MessagingException e) {
// 			// TODO Auto-generated catch block
//// 			e.printStackTrace();
// 			err=new ErrorMessage(e.toString()); 
// 		}
//		return null;
//		
//
//}
////	class MyAdjustmentListener implements AdjustmentListener {
////	    public void adjustmentValueChanged(AdjustmentEvent e) {
////	      //label.setText("    New Value is " + e.getValue() + "      ");
////	      repaint();
////	    }
////	  }
//}
//
//package Program;


import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.mail.*;


@SuppressWarnings("serial")
public class ReadFrame extends JFrame implements ActionListener
{
	ErrorMessage err;
	JLabel emailNo,subject,from,label;
	JTextArea text;
	JButton next,prev,compose,logout;
	JPanel p,p1,p2;
	JScrollPane scroll;
//	JComboBox<String> dropdown;
	String selectedOption ;
	int curMsgIndex;
	private Message[] messages;
	
	public ReadFrame()
	{
		super("Read");
		getContentPane().setLayout(new BorderLayout());
		GridLayout l2 = new GridLayout(4,2); //5,2 WITH DROPDOWN 
		p = new JPanel(l2);
		l2.setVgap(3);
		
//		label = new JLabel("LABEL");
//		p.add(label);
//		String[] options = {"INBOX", "SPAM", "SENT"};
//        dropdown = new JComboBox<>(options);
//        dropdown.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                // Update the label text when a new option is selected
//            	
//                selectedOption = (String) dropdown.getSelectedItem();
//                dispose();
//                RecieveMail robj = new RecieveMail();
//                robj.setMailType(selectedOption);
//                try {
//					robj.receiveEmail();
//					System.out.println("New ReadFrame called11");
//					new ReadFrame().setMessages(robj.getMessage());
//				} catch (MessagingException e1) {
//					// TODO Auto-generated catch block
//					err=new ErrorMessage(e.toString()); 
//				}
//                //selectedOption is the variable to be passed in RecieveMail.recievemail()
//            }
//        });
//        p.add(dropdown);
//        p.add(new JLabel(""));
		p.setBorder(new EmptyBorder(10, 20, 10, 20));
		p.add(new JLabel("EMAIL NUMBER"));
		emailNo= new JLabel();
		p.add(emailNo);
		p.add(new JLabel("FROM"));
		from= new JLabel();
		p.add(from);
		p.add(new JLabel("SUBJECT"));
		subject= new JLabel();
		p.add(subject);
		p.add(new JLabel("TEXT"));
		p2 = new JPanel();
		p2.setBorder(new EmptyBorder(10, 10, 10, 10));
		text= new JTextArea();

		text.setSize(5,30);
//		text.setSize(30,72);
//		text.scrollRectToVisible(getBounds());
		text.setLineWrap(true);
		scroll = new JScrollPane(text);
		 scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
	        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
//	        scroll.setMaximumSize(new Dimension(400,400));
	        scroll.setPreferredSize(new Dimension(700,600));	
//		p2.add(text);
		p2.add(scroll);
//		  text.setWrapStyleWord(true);
//		  scroll = new JScrollPane(text);
//		scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//				 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		p.add(scroll);
		p1 = new JPanel();
		next = new JButton("NEXT");
		next.addActionListener(this);
		prev = new JButton("PREV");
		prev.addActionListener(this);
//		ImageIcon i2 = new ImageIcon("C:/Users/Apoorva/eclipse-workspace/email client software/src/assets/edit.png");
//		Image i3 = i2.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT );
		compose = new JButton("COMPOSE");
		compose.addActionListener(this);
		p1.add(prev);
		p1.add(next);
		p1.add(compose);
//		JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 300);
//	    JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 300);
//
//	    hbar.setUnitIncrement(2);
//	    hbar.setBlockIncrement(1);
//
//	    hbar.addAdjustmentListener(new MyAdjustmentListener());
//	    vbar.addAdjustmentListener(new MyAdjustmentListener());
//	    add(hbar, BorderLayout.SOUTH);
//	    add(vbar, BorderLayout.EAST);
		
		getContentPane().add(p,BorderLayout.NORTH);
		getContentPane().add(p2,BorderLayout.WEST);
		getContentPane().add(p1,BorderLayout.SOUTH);
		
		logout = new JButton("LOGOUT");
		logout.addActionListener(this);
		p1.add(logout);
		
	
		setSize(800,800);
		setVisible(true);
			
	}

	public void actionPerformed(ActionEvent ae)
	{
		JButton pb=(JButton)ae.getSource();
		if(pb==next||pb==prev) {
			if(pb==next&& curMsgIndex<messages.length-1)
				curMsgIndex++;
			else if(pb==prev && curMsgIndex>0)
				curMsgIndex--;
			else if(pb==next&&curMsgIndex==messages.length-1)
				curMsgIndex=0;
			else if(pb==prev&&curMsgIndex==0||curMsgIndex==-1)
				curMsgIndex= messages.length-1;
			try {
				updateMail();
			} catch (IOException e) {
				// TODO Auto-generated catch block
	//			e.printStackTrace();
				err=new ErrorMessage(e.toString()); 
			}
		}
		else if(pb==compose)
		{
			new SendFrame();
		}
		else if(pb==logout)
		{
			dispose();
			LoginFrame obj1=new LoginFrame();
			obj1.show(new Control());
			obj1.setVisible(true);
			
			JOptionPane.showMessageDialog(null,"LOGGED OUT SUCCESSFULLY!!!");			
		}
		

	}
	
	public  void updateMail() throws IOException
	{
		Message msg = messages[curMsgIndex];
		emailNo.setText(Integer.toString(curMsgIndex+1));
		Address addr;
		try {
			addr = msg.getFrom()[0];
			from.setText(addr.toString());
			subject.setText(msg.getSubject());
			//Object contents = msg.getContent();
			String content = convertMessage();
			
			//System.out.println(content);
			text.setText(content);
		}
		catch (MessagingException e) {
//			e.printStackTrace();
			err=new ErrorMessage(e.toString()); 
		}
		
	}
	public void windowClosing(WindowEvent e)
	{
		RecieveMail r1 = new RecieveMail();
		r1.closeMail();
	}
	
//	public static void main(String[] args)
//	{
//		new ReadFrame();
//	}
	public void setMessages(Message[] message) {
		// TODO Auto-generated method stub
		curMsgIndex = -1;
		this.messages = message;
		if(messages.length==0)
		{
			err = new ErrorMessage("No Mails to display");
		}
		
		
	}
	private String convertMessage()
	{
		Message msg = messages[curMsgIndex];

		try {
			Object content = msg.getContent();
			StringBuffer msgs = new StringBuffer();
		StringBuffer messageContent = new StringBuffer();
		 Multipart multipart = (Multipart) content;
		 String msg1 = new String();
         for (int i = 0; i < multipart.getCount(); i++) 
         {
             Part part = (Part) multipart.getBodyPart(i);
             
//             System.out.println(part.getContentType());
             if(!part.isMimeType("text/html"))
             {
            	 msgs = messageContent.append(part.getContent().toString());
             }
                 msg1 = msgs.toString();
//                 System.out.println(msg1);
         }
         return msg1;
//         return messageContent.toString();
		}
         catch (IOException e) {
 			// TODO Auto-generated catch block
// 			e.printStackTrace();
        	 err=new ErrorMessage(e.toString()); 
 		} catch (MessagingException e) {
 			// TODO Auto-generated catch block
// 			e.printStackTrace();
 			err=new ErrorMessage(e.toString()); 
 		}
		return null;
		

}
//	class MyAdjustmentListener implements AdjustmentListener {
//	    public void adjustmentValueChanged(AdjustmentEvent e) {
//	      //label.setText("    New Value is " + e.getValue() + "      ");
//	      repaint();
//	    }
//	  }
}

