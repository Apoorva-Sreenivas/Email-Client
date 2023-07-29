package project;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

public class Control implements ICredentialsHandler {
	ErrorMessage err ;
	static LoginFrame log;
	public static void main(String[] args)
	{
		
		log = new LoginFrame();
		log.show(new Control());
		
	}

	@Override
	public void handleLogin(UserCredentials u) {
		
		
		RecieveMail robj = new RecieveMail(); 
		SendMail sobj = new SendMail();
		try {
			
		  robj.recieveCred(u);
		  robj.receiveEmail();
		  
		  sobj.recieveCred(u);
		  new ReadFrame().setMessages(robj.getMessage());
		  log.setVisible(false);
		  
		}
		catch (NoSuchProviderException e) {
//			  e.printStackTrace();
			
			  err=new ErrorMessage(e.toString()); 
			  }   
		  catch (MessagingException e) {
//			  e.printStackTrace();
			  err=new ErrorMessage(e.toString()); 
			  } 
	}

}
