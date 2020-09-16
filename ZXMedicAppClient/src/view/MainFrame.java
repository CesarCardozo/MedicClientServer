package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.*;

public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton btnAddPerson, btnAddAppointment, btnAddPerson2, btnAddAppointment2, btnExit, btnUptc;
	private JPanel pn1, pn2, pn3, pn4;
	
	public MainFrame(ControllerClient controller) {
		this.setVisible(true);
		init(controller);
		
	}
	
	private void init(ControllerClient controller) {
		this.setLayout(new GridLayout(4, 1));
		addBtns(controller);
		getContentPane().setBackground(Color.DARK_GRAY);
		setIconImage(new ImageIcon(getClass().getResource("/img/appointment.png")).getImage());
		setTitle("UPTC-EPS LOGIN");
		setSize(ConstansUI.SIZE_WINDOW_X, ConstansUI.SIZE_WINDOW_Y+80);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void addBtns(ControllerClient controller) {
		pn4 = new JPanel();
		pn4.setBackground(Color.GRAY);
		this.pn4.setLayout(new GridLayout(1, 1));
		this.btnUptc = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/uptc2.jpg")).getImage().getScaledInstance(400, 300, 300)));
		this.btnUptc.setBorder(null);
		this.btnUptc.setBackground(Color.DARK_GRAY);
		this.btnUptc.setFocusable(false);
		this.pn4.add(btnUptc);
		this.add(pn4);
		
		pn1 = new JPanel();
		pn1.setBackground(Color.GRAY);
		pn1.setBorder(BorderFactory.createTitledBorder("Login"));
		this.pn1.setLayout(new GridLayout(1, 2));
		this.btnAddPerson = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/cliente.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnAddPerson.addActionListener(controller);
		this.btnAddPerson.setActionCommand(Actions.BTN_LOGIN_PATIENT.toString());
		this.btnAddPerson.setBorder(null);
		this.btnAddPerson.setBackground(Color.DARK_GRAY);
		this.btnAddPerson.setFocusable(false);
		pn1.add(btnAddPerson);
		
		this.btnAddAppointment = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/doctor.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnAddAppointment.addActionListener(controller);
		this.btnAddAppointment.setActionCommand(Actions.BTN_LOGIN_DOCTOR.toString());
		this.btnAddAppointment.setBorder(null);
		this.btnAddAppointment.setBackground(Color.DARK_GRAY);
		this.btnAddAppointment.setFocusable(false);
		this.pn1.add(btnAddAppointment);
		
		this.add(pn1);
		
		pn2 = new JPanel();
		pn2.setBackground(Color.GRAY);
		pn2.setBorder(BorderFactory.createTitledBorder("SignUp"));
		this.pn2.setLayout(new GridLayout(1, 2));
		this.btnAddPerson2 = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/cliente.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnAddPerson2.addActionListener(controller);
		this.btnAddPerson2.setActionCommand(Actions.BTN_SIGNUP_PATIENT.toString());
		this.btnAddPerson2.setBorder(null);
		this.btnAddPerson2.setBackground(Color.DARK_GRAY);
		this.btnAddPerson2.setFocusable(false);
		pn2.add(btnAddPerson2);
		
		this.btnAddAppointment2 = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/doctor.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnAddAppointment2.addActionListener(controller);
		this.btnAddAppointment2.setActionCommand(Actions.BTN_SIGNUP_DOCTOR.toString());
		this.btnAddAppointment2.setBorder(null);
		this.btnAddAppointment2.setBackground(Color.DARK_GRAY);
		this.btnAddAppointment2.setFocusable(false);
		this.pn2.add(btnAddAppointment2);
		
		this.add(pn2);
		
		pn3 = new JPanel();
		pn3.setBackground(Color.GRAY);
		pn3.setBorder(BorderFactory.createTitledBorder("Exit..."));
		
		this.btnExit = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/exit.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnExit.addActionListener(controller);
		this.btnExit.setActionCommand(Actions.EXIT.toString());
		this.btnExit.setBorder(null);
		this.btnExit.setBackground(Color.DARK_GRAY);
		this.btnExit.setBounds(10, 110, 100, 100);
		this.btnExit.setFocusable(false);
		this.pn3.add(btnExit);
		
		this.add(pn3);
	}
	
	public static void main(String[] args) {
		new MainFrame(null);
	}

}
