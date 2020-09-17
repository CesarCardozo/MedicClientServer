package view;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ViewUtils {

	public static void showError(Component parent, String error) {
		JOptionPane.showMessageDialog(parent, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
