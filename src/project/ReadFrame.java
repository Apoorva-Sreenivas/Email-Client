package project;

import java.awt.event.*;
import java.io.*;
import java.util.Properties;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.mail.*;


@SuppressWarnings("serial")
public class ReadFrame extends JFrame implements ActionListener
{
	ErrorMessage err;
	JLabel emailNo,subject,from;
	JTextArea text;
	JButton next,prev,compose;
	JPanel p0,p,p1,p2;
	JScrollPane scroll;
	JComboBox<String> dropdown;
	int curMsgIndex;
	private Message[] messages;
	String selectedOption = "INBOX";
	
	//Merge
	private String imapHost = "imap.gmail.com";
	private Session session;
	private Folder emailFolder;
	private Store store;
	private String protocol = "imaps";
	public  void receiveEmail(String imapHost, String user, char[] cs) throws MessagingException {  
		URLName url = new URLName(protocol, imapHost, 993, selectedOption, user, String.valueOf(cs));
		   Properties properties = new Properties();  
		   Session emailSession = Session.getDefaultInstance(properties);  
		   store = emailSession.getStore(protocol);
		   store.connect(imapHost, user, String.valueOf(cs)); 
			  
		   emailFolder = store.getFolder(url);
		   emailFolder.open(Folder.READ_ONLY);  
		  
		   //4) retrieve the messages from the folder in an array and print it  
		   messages = emailFolder.getMessages();  
	}
	public ReadFrame(UserCredentials u)
	{
		
        
		super("Read");
		RecieveMail robj = new RecieveMail(); 
		setLayout(new BorderLayout());
		GridLayout l2 = new GridLayout(5,1);
		p = new JPanel(l2);
		l2.setVgap(3);
		p.setBorder(new EmptyBorder(10, 20, 10, 20));
		String[] options = {"INBOX", "[Gmail]/Spam", "[Gmail]/Sent Mail", "[Gmail]/Drafts", "[Gmail]/Important",  "[Gmail]/Starred"};
        dropdown = new JComboBox<>(options);
        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the label text when a new option is selected
            	
                selectedOption = (String) dropdown.getSelectedItem();
                try {
					receiveEmail(imapHost, u.getUserName(), u.getPwd());
					curMsgIndex = -1;
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                //System.out.println(selectedOption);
                //selectedOption is the variable to be passed in RecieveMail.recievemail()
            }
        });
        p.add(dropdown);
        p.add(new JLabel(""));
		p.add(new JLabel("Email Number"));
		emailNo= new JLabel();
		p.add(emailNo);
		p.add(new JLabel("From"));
		from= new JLabel();
		p.add(from);
		p.add(new JLabel("Subject"));
		subject= new JLabel();
		p.add(subject);
		p.add(new JLabel("Text"));
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
		add(p,BorderLayout.NORTH);
		add(p2,BorderLayout.WEST);
		add(p1,BorderLayout.SOUTH);
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
		if(pb==compose)
		{
			new SendFrame();
		}

	}
	
	public  void updateMail() throws IOException
	{
		if(messages.length == 0)
			return;
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
		
		
	}
	private String convertMessage()
	{
		Message msg = messages[curMsgIndex];

		try {
			Object content = msg.getContent();
			//Sent messages did not return MultiPart instance, instead they returned String instances
			if(content instanceof String)
			{
				String body = (String) content;
				return body;
			}
			//For other messages
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

