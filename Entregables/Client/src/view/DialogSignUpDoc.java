package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

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

public class DialogSignUpDoc extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel pn1, pn2;
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
		getContentPane().setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		setTitle("UPTC-EPS REGISTER DOCTOR");
		setSize(ConstansUI.SIZE_WINDOW_X, ConstansUI.SIZE_WINDOW_Y + 150);
		setLocationRelativeTo(null);
		putElements(controller);
	}

	private void putElements(ControllerClient controller) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 3;
		c.gridy = 0;
		this.pn1 = new JPanel();
		this.pn1.setLayout(new GridLayout(1, 1));
		this.btnImg = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/registrar.png")).getImage()
				.getScaledInstance(400, 130, 130)));
		this.btnImg.setBorder(null);
		this.btnImg.setBackground(Color.DARK_GRAY);
		this.btnImg.setFocusable(false);
		this.pn1.add(btnImg);
		this.add(pn1);
		add(pn1, c);

		// ----- Formulario registro -----
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 20, 0, 20);
		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 3.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;

		this.pn2 = new JPanel();
		pn2.setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		this.pn2.setLayout(new GridLayout(12, 1));

		jlId = new JLabel("Id");
		jlId.setForeground(Color.WHITE);
		txAId = new JTextArea();

		this.pn2.add(jlId);
		this.pn2.add(txAId);

		jlName = new JLabel("Name");
		jlName.setForeground(Color.WHITE);
		txAName = new JTextArea();

		this.pn2.add(jlName);
		this.pn2.add(txAName);

		jlPhone = new JLabel("Phone");
		jlPhone.setForeground(Color.WHITE);
		txAPhone = new JTextArea();

		this.pn2.add(jlPhone);
		this.pn2.add(txAPhone);

		this.add(this.pn2);

		jlEmail = new JLabel("Email");
		jlEmail.setForeground(Color.WHITE);
		txAEmail = new JTextArea();

		this.pn2.add(jlEmail);
		this.pn2.add(txAEmail);

		jlSpeciality = new JLabel("Speciality");
		jlSpeciality.setForeground(Color.WHITE);
		comboBox = new JComboBox<MedicalSpeciality>();
		comboBox.setModel(new DefaultComboBoxModel<MedicalSpeciality>(MedicalSpeciality.values()));

		this.pn2.add(jlSpeciality);
		this.pn2.add(comboBox);

		ljPassw = new JLabel("Password");
		ljPassw.setForeground(Color.WHITE);
		txAPassw = new JPasswordField();
		this.pn2.add(ljPassw);
		this.pn2.add(txAPassw);
		add(pn2, c);

		// ----- Boton registro -----
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.anchor = GridBagConstraints.CENTER; // bottom of space
		c.insets = new Insets(23, 50, 23, 50); // top padding
		c.gridx = 1; // aligned with button 2
		c.gridwidth = 2; // 2 columns wide
		c.gridy = 2; // third row
		this.btnOk = new JButton(new ImageIcon(
				new ImageIcon(getClass().getResource("/img/ok.png")).getImage().getScaledInstance(40, 40, 40)));
		this.btnOk.setText("Register Doctor  ");
		this.btnOk.setBackground(ConstansUI.COLOR_BOTON_OK);
		this.btnOk.setFocusable(false);
		this.btnOk.addActionListener(controller);
		this.btnOk.setActionCommand(Actions.REGISTER_DOCTOR.toString());
		add(btnOk, c);
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

	public MedicalSpeciality getSpeciality() {
		return (MedicalSpeciality) this.comboBox.getSelectedItem();
	}

	public static void main(String[] args) {
		DialogSignUpDoc dialogLoginPatient = new DialogSignUpDoc(null);

		System.out.println(dialogLoginPatient.getId());
		System.out.println(dialogLoginPatient.getPassword());
	}
}