
package project;

import javax.mail.*;
import java.util.*;


public class RecieveMail {
	ErrorMessage err;
	Message[] messages;
	private Session session;
	private Folder emailFolder;
	private Store store;
	private String protocol = "imaps";
	String selectedOption = "INBOX";
	 public  void receiveEmail(String imapHost,   
			  String user, char[] cs) throws MessagingException {  
//			  try {  
				  
			   //1) get the session object  
		 URLName url = new URLName(protocol, imapHost, 993, selectedOption, user, String.valueOf(cs));
			   Properties properties = new Properties();  
//			   properties.setProperty("mail.pop3.host", pop3Host);  
//			   properties.setProperty("mail.pop3.port","995");
//			   properties.put("mail.pop3.starttls.enable", "true");
			   Session emailSession = Session.getDefaultInstance(properties);  
			   
			   store = emailSession.getStore(protocol);
//			   String cs1 = String.valueOf(cs);
			      store.connect(imapHost, user, String.valueOf(cs)); 
			  
			   emailFolder = store.getFolder(url);
			   emailFolder.open(Folder.READ_ONLY);  
			  
			   //4) retrieve the messages from the folder in an array and print it  
			   messages = emailFolder.getMessages();  
//			   if(messages.length==0)
//			   {
//				   err=new ErrorMessage("No messages"); 
//			   }
//			   for  (int i = 0; i < messages.length; i++) {  
//			    Message message = messages[i];                    
//			    System.out.println("---------------------------------");  
//			    System.out.println("Email Number " + (i + 1));  
//			    
//			    System.out.println("Subject: " + message.getSubject());  
//			    System.out.println("From: " + message.getFrom()[0]);  
//			    System.out.println("Text: " + message.getContent().toString());  
//			   }  
			  
			   //5) close the store and folder objects  
			     
			  
			   
//			  catch (IOException e) {e.printStackTrace();}  
}
			  public Message[] getMessage()
			  {
				  return this.messages;
			  }
			  
			  public void closeMail()
			  {
				  try {
					emailFolder.close(false);
					store.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					err=new ErrorMessage(e.toString());                       
				}  
				   
			  }
//			 public static void main(String[] args) {  
//			  LoginFrame log = new LoginFrame();
//			  String host = "pop.gmail.com";//change accordingly  
//			  String mailStoreType = "pop3";  
//			  String username= log.user;  
//			  String password= log.pass;//change accordingly  
//			  
//			  receiveEmail(host, mailStoreType, username, password);  
//			  
//			 }  


}

