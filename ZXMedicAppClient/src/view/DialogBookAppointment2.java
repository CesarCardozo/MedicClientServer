package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.Actions;
import controller.ControllerClient;
import model.entity.Appointment;
import model.entity.MedicalSpeciality;

public class DialogBookAppointment2 extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel jp1;
	private JComboBox<Appointment> jComboBox;

	private JPanel pn2;

	private JButton btnOk;

	public DialogBookAppointment2(ControllerClient controller,  ArrayList<Appointment> listAppointment) {
		init(controller, listAppointment);
		setVisible(true);
	}
	
	
	private void init(ControllerClient controller,  ArrayList<Appointment> listAppointment) {
		this.setLayout(new GridLayout(2, 1));
		getContentPane().setBackground(Color.GRAY);
		setTitle("UPTC-EPS Create Appointment");
		setSize(ConstansUI.SIZE_WINDOW_X - 50, ConstansUI.SIZE_WINDOW_Y -140);
		setLocationRelativeTo(null);
		putElements(controller, listAppointment);
	}

	private void putElements(ControllerClient controller, ArrayList<Appointment> listAppointment) {
		this.jp1 = new JPanel();
		jp1.setBorder(BorderFactory.createTitledBorder("Book Apointmet"));
		jComboBox = new JComboBox<>();
		for (Appointment appointment : listAppointment) {
			jComboBox.addItem(appointment);//mostrar un to sting special
		}
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
		this.btnOk.setActionCommand(Actions.BOOK_APPOINTMENT.toString());
		this.pn2.add(btnOk);
		this.add(pn2);
	}
	public Appointment getAppointment () {
		return (Appointment) this.jComboBox.getSelectedItem();
	}
	
	public static void main(String[] args) {
		ArrayList<Appointment> list = new ArrayList<Appointment>();
		Appointment appointment= new Appointment(new Date(2020, 10, 1));
		Appointment appointment2= new Appointment(new Date(2020, 10, 5));
		Appointment appointment3= new Appointment(new Date(2020, 10, 10));
		
		list.add(appointment);
		list.add(appointment2);
		list.add(appointment3);
		
		new DialogBookAppointment2(null,list);
	}

}
