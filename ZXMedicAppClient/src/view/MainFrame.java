package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import controller.*;

public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton btnAddPerson, btnAddAppointment, btnSowCalendar, btnExit;
	
	public MainFrame(ControllerClient controller) {
		this.setVisible(true);
		init(controller);
		
	}
	
	private void init(ControllerClient controller) {
		this.setLayout(new GridLayout(2, 2));
		addBtns(controller);
		getContentPane().setBackground(Color.DARK_GRAY);
		setIconImage(new ImageIcon(getClass().getResource(ConstansUI.PATH_IMAGE_PERFIL)).getImage());
		setTitle("UPTC-EPS LOGIN");
		setSize(ConstansUI.SIZE_WINDOW_X, ConstansUI.SIZE_WINDOW_Y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void addBtns(ControllerClient controller) {
		this.btnAddPerson = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/usuario.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnAddPerson.addActionListener(controller);
//		this.btnAddPerson.setActionCommand(Actions.PATIENT.toString());
		this.btnAddPerson.setBorder(null);
		this.btnAddPerson.setBackground(Color.DARK_GRAY);
		this.btnAddPerson.setFocusable(false);
		this.add(btnAddPerson);
		
		this.btnAddAppointment = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/doctor.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnAddAppointment.addActionListener(controller);
//		this.btnAddAppointment.setActionCommand(Actions.DOCTOR.toString());
		this.btnAddAppointment.setBorder(null);
		this.btnAddAppointment.setBackground(Color.DARK_GRAY);
		this.btnAddAppointment.setFocusable(false);
		this.add(btnAddAppointment);

		this.btnSowCalendar = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/apoyo.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnSowCalendar.addActionListener(controller);
//		this.btnSowCalendar.setActionCommand(Actions.APOYO.toString());
		this.btnSowCalendar.setBorder(null);
		this.btnSowCalendar.setBackground(Color.DARK_GRAY);
		this.btnSowCalendar.setFocusable(false);
		this.add(btnSowCalendar);
		
		this.btnExit = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/exit.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnExit.addActionListener(controller);
		this.btnExit.setActionCommand(Actions.EXIT.toString());
		this.btnExit.setBorder(null);
		this.btnExit.setBackground(Color.DARK_GRAY);
		this.btnExit.setBounds(10, 110, 100, 100);
		this.btnExit.setFocusable(false);
		this.add(btnExit);
	}
	
	public static void main(String[] args) {
		new MainFrame(null);
	}

}
