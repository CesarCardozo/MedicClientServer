package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import controller.Actions;
import controller.ControllerClient;

public class DialogLogin extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel pn1, pn2, pn3, pn4;
	private JButton btnOk, btnImg;
	private JLabel jlId, ljPassw;
	private JTextArea txAId;
	private JPasswordField txAPassw;

	public DialogLogin(ControllerClient controller, Actions action) {
		init(controller, action);
		this.setVisible(true);
	}

	private void init(ControllerClient controller, Actions action) {
		this.setLayout(new BorderLayout());
		setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
//		setTitle("UPTC-EPS LOGIN");
//		setSize(ConstansUI.SIZE_WINDOW_X, ConstansUI.SIZE_WINDOW_Y+80);
//		setLocationRelativeTo(null);
		putElements(controller, action);
	}

	private void putElements(ControllerClient controller, Actions action) {
//		this.pn2 = new JPanel();
//		//pn2.setBackground(Color.GRAY);
//		this.pn2.setLayout(new GridLayout(6, 1, 0, 0));
//		
//		jlId = new JLabel("Id");
//		this.pn2.add(jlId);
//		
//		txAId = new JTextArea();
//		this.pn2.add(txAId);
//		this.pn2.add(new JLabel(""));
//		
//		ljPassw = new JLabel("Password");
//		this.pn2.add(ljPassw);
//		
//		txAPassw = new JPasswordField();
//		this.pn2.add(txAPassw);
//		
//		
//		this.pn4 = new JPanel();
//		//pn4.setBackground(Color.GRAY);
//		this.pn4.setLayout(new BorderLayout());
//		this.btnOk = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/oklogin.png")).getImage().getScaledInstance(200, 200, 200)));
//		this.btnOk = new JButton("LOGIN");
//		this.btnOk.setBorder(null);
//		this.btnOk.setBackground(Color.BLUE);
//		this.btnOk.setFocusable(false);
//		this.btnOk.addActionListener(controller);
//		//this.btnOk.setActionCommand(action.toString());
//		this.pn4.add(btnOk);
//		this.pn2.add(pn4);
//		
//		this.add(this.pn2, BorderLayout.CENTER);
//	

		JLabel jLabel;
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		jLabel = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(jLabel, c);

		jLabel = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		add(jLabel, c);

		jLabel = new JLabel("");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		add(jLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.ipady = 50; // make this component tall
		c.insets = new Insets(0, 20, 0, 20);
		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		this.pn2 = new JPanel();
		// pn2.setBackground(Color.GRAY);
		this.pn2.setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		this.pn2.setLayout(new GridLayout(4, 1, 0, 0));
		jlId = new JLabel("Id");
		jlId.setForeground(Color.WHITE);
		this.pn2.add(jlId);
		txAId = new JTextArea();
		this.pn2.add(txAId);
		ljPassw = new JLabel("Password");
		ljPassw.setForeground(Color.WHITE);
		this.pn2.add(ljPassw);
		txAPassw = new JPasswordField();
		this.pn2.add(txAPassw);
		add(this.pn2, c);

		JButton jButtonPacient = new JButton("Login Patient");
		jButtonPacient.setBackground(Color.GREEN);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; 
		c.weighty = 0.5; 
		c.anchor = GridBagConstraints.CENTER; 
		c.insets = new Insets(10, 40, 10, 10); 
		c.gridx = 1; 
		c.gridwidth = 1; // 2 columns wide
		c.gridy = 2; // third row
		add(jButtonPacient, c);
		
		JButton jButtonDoctor = new JButton("Login Doctor");
		jButtonDoctor.setBackground(Color.GREEN);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 0.5; // request any extra vertical space
		c.anchor = GridBagConstraints.CENTER; // bottom of space
		c.insets = new Insets(10, 0, 10, 40); // top padding
		c.gridx = 2; // aligned with button 2
		c.gridwidth = 1; // 2 columns wide
		c.gridy = 2; // third row
		add(jButtonDoctor, c);
	}

	public String getId() {
		return this.txAId.getText();
	}

	@SuppressWarnings("deprecation")
	public String getPassword() {
		return this.txAPassw.getText();
	}

	public static void main(String[] args) {
		DialogLogin dialogLoginPatient = new DialogLogin(null, null);

		System.out.println(dialogLoginPatient.getId());
		System.out.println(dialogLoginPatient.getPassword());
	}
}