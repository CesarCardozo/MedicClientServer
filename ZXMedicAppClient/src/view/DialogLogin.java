package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import controller.Actions;
import controller.ControllerClient;

public class DialogLogin extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pn1, pn2, pn3, pn4;
	private JButton btnOk, btnImg;
	private JLabel jlId, ljPassw;
	private JTextArea txAId;
	private JPasswordField txAPassw;
	
	public DialogLogin(ControllerClient controller,  Actions action) {
		init(controller, action);
		this.setVisible(true);
	}
	
	private void init(ControllerClient controller,  Actions action) {
		this.setLayout(new GridLayout(4, 1));
		getContentPane().setBackground(Color.GRAY);
		setTitle("UPTC-EPS LOGIN");
		setSize(ConstansUI.SIZE_WINDOW_X, ConstansUI.SIZE_WINDOW_Y+80);
		setLocationRelativeTo(null);
		putElements(controller, action);
	}
	
	
	private void putElements(ControllerClient controller, Actions action) {
		this.pn1 = new JPanel();
		pn1.setBackground(Color.GRAY);
		this.pn1.setLayout(new GridLayout(1, 1));
		this.btnImg = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/login.png")).getImage().getScaledInstance(450, 290, 300)));
		this.btnImg.setBorder(null);
		this.btnImg.setBackground(Color.DARK_GRAY);
		this.btnImg.setFocusable(false);
		this.pn1.add(btnImg);
		this.add(pn1);
		
		this.pn2 = new JPanel();
		pn2.setBackground(Color.GRAY);
//		pn2.setBorder(BorderFactory.createTitledBorder("Login"));
		this.pn2.setLayout(new GridLayout(4, 1));
		
		jlId = new JLabel("Id");
		txAId = new JTextArea();
		txAId.setSize(20, 50);

		this.pn2.add(jlId);
		this.pn2.add(txAId);
		
		this.add(this.pn2);
		
		this.pn3 = new JPanel();
		pn3.setBackground(Color.GRAY);
		this.pn3.setLayout(new GridLayout(4, 1));
		ljPassw = new JLabel("Password");
		txAPassw = new JPasswordField();
		txAId.setSize(20, 50);
		this.pn3.add(ljPassw);
		this.pn3.add(txAPassw);
		
		this.add(this.pn3);
		
		this.pn4 = new JPanel();
		pn4.setBackground(Color.GRAY);
		this.pn4.setLayout(new GridLayout(1, 1));
		this.btnOk = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/oklogin.png")).getImage().getScaledInstance(200, 100, 100)));
		this.btnOk.setBorder(null);
		this.btnOk.setBackground(Color.DARK_GRAY);
		this.btnOk.setFocusable(false);
		this.btnOk.addActionListener(controller);
		this.btnOk.setActionCommand(action.toString());
		this.pn4.add(btnOk);
		this.add(pn4);
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