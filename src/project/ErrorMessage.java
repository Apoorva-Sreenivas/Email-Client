package project;

import javax.swing.*;

@SuppressWarnings("serial")
public class ErrorMessage extends JFrame {

	public ErrorMessage(String x) {

		JOptionPane.showInternalMessageDialog(null, x, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
