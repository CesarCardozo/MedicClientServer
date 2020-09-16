package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.Actions;
import controller.ControllerClient;
import model.entity.MedicalSpeciality;

public class DialogBookAppointment extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel jp1;
	private JComboBox<MedicalSpeciality> jComboBox;

	private JPanel pn2;

	private JButton btnOk;

	public DialogBookAppointment(ControllerClient controller) {
		init(controller);
		setVisible(true);
	}
	
	
	private void init(ControllerClient controller) {
		this.setLayout(new GridLayout(2, 1));
		getContentPane().setBackground(Color.GRAY);
		setTitle("UPTC-EPS Create Appointment");
		setSize(ConstansUI.SIZE_WINDOW_X - 50, ConstansUI.SIZE_WINDOW_Y -140);
		setLocationRelativeTo(null);
		putElements(controller);
	}

	private void putElements(ControllerClient controller) {
		this.jp1 = new JPanel();
		jp1.setBorder(BorderFactory.createTitledBorder("Speciality"));
		jComboBox = new JComboBox<MedicalSpeciality>();
		jComboBox.setModel(new DefaultComboBoxModel<MedicalSpeciality>(MedicalSpeciality.values()));
		jp1.add(jComboBox);
		this.add(jp1);
		
		this.pn2 = new JPanel();
		pn2.setBackground(Color.GRAY);
		this.pn2.setLayout(new GridLayout(1, 1));
		this.btnOk = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/ok.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnOk.setBorder(null);
		this.btnOk.setBackground(Color.DARK_GRAY);
		this.btnOk.setFocusable(false);
		this.btnOk.addActionListener(controller);
		this.btnOk.setActionCommand(Actions.OK_BOOK_APPOINTMENT.toString());
		this.pn2.add(btnOk);
		this.add(pn2);
	}
	public MedicalSpeciality getSpeciality () {
		return (MedicalSpeciality) this.jComboBox.getSelectedItem();
	}
	
	public static void main(String[] args) {
		new DialogBookAppointment(null);
	}

}
