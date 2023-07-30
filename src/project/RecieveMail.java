
package project;

import javax.mail.*;
import java.util.*;

public class RecieveMail {
	ErrorMessage err;
	Message[] messages;
	private Folder emailFolder;
	private Store store;
	private String protocol = "imaps";
	String selectedOption = "INBOX";

	public void receiveEmail(String imapHost, String selectedOption, String user, char[] cs) throws MessagingException {
		URLName url = new URLName(protocol, imapHost, 993, selectedOption, user, String.valueOf(cs));
		Properties properties = new Properties();
		Session emailSession = Session.getDefaultInstance(properties);
		store = emailSession.getStore(protocol);
		store.connect(imapHost, user, String.valueOf(cs));
		emailFolder = store.getFolder(url);
		emailFolder.open(Folder.READ_ONLY);
		messages = emailFolder.getMessages();
	}

	public Message[] getMessage() {
		return this.messages;
	}

	public void closeMail() {
		try {
			emailFolder.close(false);
			store.close();
		} catch (MessagingException e) {
			err = new ErrorMessage(e.toString());
		}

	}

}
