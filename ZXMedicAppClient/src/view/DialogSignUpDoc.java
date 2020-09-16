package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import controller.Actions;
import controller.ControllerClient;
import model.entity.MedicalSpeciality;

public class DialogSignUpDoc extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pn1, pn2, pn3, pn4;
	private JButton btnOk, btnImg;
	private JLabel jlId, ljPassw, jlName, jlPhone, jlEmail, jlSpeciality;
	private JTextArea txAId, txAName, txAPhone, txAEmail;
	private JComboBox<MedicalSpeciality> comboBox;
	private JPasswordField txAPassw;
	
	public DialogSignUpDoc(ControllerClient controller) {
		init(controller);
		this.setVisible(true);
	}
	
	private void init(ControllerClient controller) {
		this.setLayout(new GridLayout(4, 1));
		getContentPane().setBackground(Color.GRAY);
		setTitle("UPTC-EPS REGISTER DOCTOR");
		setSize(ConstansUI.SIZE_WINDOW_X, ConstansUI.SIZE_WINDOW_Y+80);
		setLocationRelativeTo(null);
		putElements(controller);
	}
	
	
	private void putElements(ControllerClient controller) {
		this.pn1 = new JPanel();
		pn1.setBackground(Color.GRAY);
		this.pn1.setLayout(new GridLayout(1, 1));
		this.btnImg = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/signup.png")).getImage().getScaledInstance(500, 250, 250)));
		this.btnImg.setBorder(null);
		this.btnImg.setBackground(Color.DARK_GRAY);
		this.btnImg.setFocusable(false);
		this.pn1.add(btnImg);
		this.add(pn1);
		
		this.pn2 = new JPanel();
		pn2.setBackground(Color.GRAY);
		this.pn2.setLayout(new GridLayout(6, 1));
		
		jlId = new JLabel("Id");
		txAId = new JTextArea();

		this.pn2.add(jlId);
		this.pn2.add(txAId);
		
		jlName = new JLabel("Name");
		txAName = new JTextArea();

		this.pn2.add(jlName);
		this.pn2.add(txAName);
		
		jlPhone = new JLabel("Phone");
		txAPhone = new JTextArea();
		
		this.pn2.add(jlPhone);
		this.pn2.add(txAPhone);
		
		this.add(this.pn2);
		
		this.pn3 = new JPanel();
		pn3.setBackground(Color.GRAY);
		this.pn3.setLayout(new GridLayout(6, 1));
		
		jlEmail = new JLabel("Email");
		txAEmail = new JTextArea();
		
		this.pn3.add(jlEmail);
		this.pn3.add(txAEmail);
		
		jlSpeciality = new JLabel("Speciality");
		comboBox = new JComboBox<MedicalSpeciality>();
		comboBox.setModel(new DefaultComboBoxModel<MedicalSpeciality>(MedicalSpeciality.values()));
		
		this.pn3.add(jlSpeciality);
		this.pn3.add(comboBox);
		
		ljPassw = new JLabel("Password");
		txAPassw = new JPasswordField();
		this.pn3.add(ljPassw);
		this.pn3.add(txAPassw);

		this.add(this.pn3);
		
		this.pn4 = new JPanel();
		pn4.setBackground(Color.GRAY);
		this.pn4.setLayout(new GridLayout(1, 1));
		this.btnOk = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/btnsignup.png")).getImage().getScaledInstance(200, 100, 100)));
		this.btnOk.setBorder(null);
		this.btnOk.setBackground(Color.DARK_GRAY);
		this.btnOk.setFocusable(false);
		this.btnOk.addActionListener(controller);
		this.btnOk.setActionCommand(Actions.REGISTER_DOCTOR.toString());
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
	
	public String getPhone() {
		return this.txAPhone.getText();
	}
	
	public String getName() {
		return this.txAName.getText();
	}
	
	public String getEmail() {
		return this.txAEmail.getText();
	}
	
	public MedicalSpeciality getSpeciality () {
		return (MedicalSpeciality) this.comboBox.getSelectedItem();
	}
	
	
	public static void main(String[] args) {
		DialogSignUpDoc dialogLoginPatient = new DialogSignUpDoc(null);
		
		System.out.println(dialogLoginPatient.getId());
		System.out.println(dialogLoginPatient.getPassword());
	}
}