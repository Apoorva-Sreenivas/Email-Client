
package project;

import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.mail.*;

@SuppressWarnings("serial")
public class ReadFrame extends JFrame implements ActionListener {
	ErrorMessage err;
	JLabel emailNo, subject, from;
	JTextArea text;
	JButton next, prev, compose, logout;
	JPanel p0, p, p1, p2;
	JScrollPane scroll;
	JComboBox<String> dropdown;
	int curMsgIndex;
	private Message[] messages;
	String selectedOption = "INBOX";
	private String imapHost = "imap.gmail.com";

	public ReadFrame(UserCredentials u) {

		super("Read");
		RecieveMail robj = new RecieveMail();
		setLayout(new BorderLayout());
		GridLayout l2 = new GridLayout(5, 1);
		p = new JPanel(l2);
		l2.setVgap(3);
		p.setBorder(new EmptyBorder(10, 20, 10, 20));
		String[] options = { "INBOX", "[Gmail]/Spam", "[Gmail]/Sent Mail", "[Gmail]/Drafts", "[Gmail]/Important",
				"[Gmail]/Starred" };
		dropdown = new JComboBox<>(options);
		dropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedOption = (String) dropdown.getSelectedItem();
				try {
					robj.receiveEmail(imapHost, selectedOption, u.getUserName(), u.getPwd());
					curMsgIndex = -1;
					setMessages(robj.getMessage());
				} catch (MessagingException e1) {

					e1.printStackTrace();
				}

			}
		});
		p.add(dropdown);
		p.add(new JLabel(""));
		p.add(new JLabel("Email Number"));
		emailNo = new JLabel();
		p.add(emailNo);
		p.add(new JLabel("From"));
		from = new JLabel();
		p.add(from);
		p.add(new JLabel("Subject"));
		subject = new JLabel();
		p.add(subject);
		p.add(new JLabel("Text"));
		p2 = new JPanel();
		p2.setBorder(new EmptyBorder(10, 10, 10, 10));
		text = new JTextArea();

		text.setSize(5, 30);

		text.setLineWrap(true);
		scroll = new JScrollPane(text);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(700, 600));
		p2.add(scroll);
		p1 = new JPanel();
		next = new JButton("NEXT");
		next.addActionListener(this);
		prev = new JButton("PREV");
		prev.addActionListener(this);
		compose = new JButton("COMPOSE");
		compose.addActionListener(this);
		logout = new JButton("LOGOUT");
		logout.addActionListener(this);
		p1.add(logout);
		p1.add(prev);
		p1.add(next);
		p1.add(compose);
		p1.add(logout);
		add(p, BorderLayout.NORTH);
		add(p2, BorderLayout.WEST);
		add(p1, BorderLayout.SOUTH);
		setSize(800, 800);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {
		JButton pb = (JButton) ae.getSource();
		if (pb == next || pb == prev) {
			if (pb == next && curMsgIndex < messages.length - 1)
				curMsgIndex++;
			else if (pb == prev && curMsgIndex > 0)
				curMsgIndex--;
			else if (pb == next && curMsgIndex == messages.length - 1)
				curMsgIndex = 0;
			else if (pb == prev && curMsgIndex == 0 || curMsgIndex == -1)
				curMsgIndex = messages.length - 1;
			try {
				updateMail();
			} catch (IOException e) {
				err = new ErrorMessage(e.toString());
			}
		}
		if (pb == compose) {
			new SendFrame();
		}
		if (pb == logout) {
			dispose();
			LoginFrame obj1 = new LoginFrame();
			obj1.setVisible(true);
			JOptionPane.showMessageDialog(null, "LOGGED OUT SUCCESSFULLY!!!");

		}

	}

	public void updateMail() throws IOException {
		if (messages.length == 0)
			return;
		Message msg = messages[curMsgIndex];
		emailNo.setText(Integer.toString(curMsgIndex + 1) + "/" + Integer.toString(messages.length));
		Address addr;
		try {
			addr = msg.getFrom()[0];
			from.setText(addr.toString());
			subject.setText(msg.getSubject());
			String content = convertMessage();
			text.setText(content);
		} catch (MessagingException e) {
			err = new ErrorMessage(e.toString());
		}

	}

	public void windowClosing(WindowEvent e) {
		RecieveMail r1 = new RecieveMail();
		r1.closeMail();
	}

	public void setMessages(Message[] message) {
		curMsgIndex = -1;
		this.messages = message;
		if (messages.length == 0) {
			JOptionPane.showMessageDialog(null, "YOU HAVE NO MESSAGES!!");

		}

	}

	private String convertMessage() {
		Message msg = messages[curMsgIndex];

		try {
			Object content = msg.getContent();
			if (content instanceof String) {
				String body = (String) content;
				return body;
			}
			StringBuffer msgs = new StringBuffer();
			StringBuffer messageContent = new StringBuffer();
			Multipart multipart = (Multipart) content;
			String msg1 = new String();
			for (int i = 0; i < multipart.getCount(); i++) {
				Part part = (Part) multipart.getBodyPart(i);

				if (!part.isMimeType("text/html")) {
					msgs = messageContent.append(part.getContent().toString());
				}
				msg1 = msgs.toString();
			}
			return msg1;
		} catch (IOException e) {
			err = new ErrorMessage(e.toString());
		} catch (MessagingException e) {
			err = new ErrorMessage(e.toString());
		}
		return null;

	}

}
