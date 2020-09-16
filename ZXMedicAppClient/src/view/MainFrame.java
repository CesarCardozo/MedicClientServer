package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.*;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btnAddPerson, btnAddAppointment, btnAddDoctor, btnAddAppointment2, btnExit, btnUptc;
	private JPanel pn1, pn2, pn3, pn4;

	public MainFrame(ControllerClient controller) {
		this.setVisible(true);
		init(controller);

	}

	private void init(ControllerClient controller) {
		this.setLayout(new GridLayout(4, 1));
		getContentPane().setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		addBtns(controller);
		setIconImage(new ImageIcon(getClass().getResource("/img/appointment.png")).getImage());
		setTitle("UPTC-EPS LOGIN");
		setSize(ConstansUI.SIZE_WINDOW_X, ConstansUI.SIZE_WINDOW_Y + 80);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void addBtns(ControllerClient controller) {
//		pn4 = new JPanel();
//		pn4.setBackground(Color.GRAY);
//		this.pn4.setLayout(new GridLayout(1, 1));
//		this.btnUptc = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/uptc2.jpg")).getImage().getScaledInstance(400, 300, 300)));
//		this.btnUptc.setBorder(null);
//		this.btnUptc.setBackground(Color.DARK_GRAY);
//		this.btnUptc.setFocusable(false);
//		this.pn4.add(btnUptc);
//		this.add(pn4);
//		
//		pn1 = new JPanel();

		// getContentPane().setBackground(Color.decode("#fefefe"));
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.BOTH;
		JPanel nn5 = new JPanel();
		nn5.setBackground(Color.CYAN);
		nn5.setLayout(new GridLayout(1, 1));
		this.btnUptc = new JButton(new ImageIcon(
				new ImageIcon(getClass().getResource("/img/eps.jpg")).getImage().getScaledInstance(400, 180, 300)));
		this.btnUptc.setBorder(null);
		 this.btnUptc.setBackground(Color.cyan);
		this.btnUptc.setFocusable(false);
		nn5.add(btnUptc);
		add(btnUptc, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.5;
		gbc.weighty = 0.3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		DialogLogin dialogLogin = new DialogLogin(controller, null);
		add(dialogLogin, gbc);
		
		 
		//Botones de registrar
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.2;
		gbc.weighty = 0;
		gbc.insets = new Insets(0,20,30,20); 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		JLabel lbText =  new JLabel("Crea tu cuenta para acceder a los servicios!");
		lbText.setForeground(Color.WHITE);	
		lbText.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbText, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.weightx = 0.3;
		gbc.weighty = 0.1;
		gbc.fill = GridBagConstraints.BOTH;
		JPanel nn = new JPanel();
		nn.setBackground(Color.red);
		
		JPanel pnbBtnRegister = new JPanel();
		pnbBtnRegister.setLayout(new GridLayout(1, 2, 10, 3));
		pnbBtnRegister.setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		this.btnAddPerson = new JButton("Register Patient");
		this.btnAddPerson.addActionListener(controller);
		this.btnAddPerson.setActionCommand(Actions.BTN_LOGIN_PATIENT.toString());
		this.btnAddPerson.setBorder(null);
		this.btnAddPerson.setBackground(Color.decode("#ffc20e"));
		this.btnAddPerson.setFocusable(false);
		pnbBtnRegister.add(this.btnAddPerson);

		this.btnAddDoctor = new JButton("Register Doctor");
		ImageIcon iconobtn = new ImageIcon("/img/appointment.png");
		this.btnAddDoctor.setIcon(iconobtn);
		this.btnAddDoctor.addActionListener(controller);
		this.btnAddDoctor.setActionCommand(Actions.BTN_LOGIN_PATIENT.toString());
		//this.btnAddDoctor.setBorder(null);
		 this.btnAddDoctor.setBackground(Color.decode("#ffc20e"));
		//this.btnAddDoctor.setFocusable(false);
		pnbBtnRegister.add(this.btnAddDoctor);
		
		add(pnbBtnRegister, gbc);

//		pn1.setBackground(Color.GRAY);
//		pn1.setBorder(BorderFactory.createTitledBorder("Login"));
//		this.pn1.setLayout(new GridLayout(1, 2));
//		this.btnAddPerson = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/cliente.png")).getImage().getScaledInstance(100, 100, 100)));
//		this.btnAddPerson.addActionListener(controller);
//		this.btnAddPerson.setActionCommand(Actions.BTN_LOGIN_PATIENT.toString());
//		this.btnAddPerson.setBorder(null);
//		this.btnAddPerson.setBackground(Color.DARK_GRAY);
//		this.btnAddPerson.setFocusable(false);
//		pn1.add(btnAddPerson);
//		
//		this.btnAddAppointment = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/doctor.png")).getImage().getScaledInstance(100, 100, 100)));
//		this.btnAddAppointment.addActionListener(controller);
//		this.btnAddAppointment.setActionCommand(Actions.BTN_LOGIN_DOCTOR.toString());
//		this.btnAddAppointment.setBorder(null);
//		this.btnAddAppointment.setBackground(Color.DARK_GRAY);
//		this.btnAddAppointment.setFocusable(false);
//		this.pn1.add(btnAddAppointment);
//		
//		this.add(pn1);
//		
//		pn2 = new JPanel();
//		pn2.setBackground(Color.GRAY);
//		pn2.setBorder(BorderFactory.createTitledBorder("SignUp"));
//		this.pn2.setLayout(new GridLayout(1, 2));
//		this.btnAddPerson2 = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/cliente.png")).getImage().getScaledInstance(100, 100, 100)));
//		this.btnAddPerson2.addActionListener(controller);
//		this.btnAddPerson2.setActionCommand(Actions.BTN_SIGNUP_PATIENT.toString());
//		this.btnAddPerson2.setBorder(null);
//		this.btnAddPerson2.setBackground(Color.DARK_GRAY);
//		this.btnAddPerson2.setFocusable(false);
//		pn2.add(btnAddPerson2);
//		
//		this.btnAddAppointment2 = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/doctor.png")).getImage().getScaledInstance(100, 100, 100)));
//		this.btnAddAppointment2.addActionListener(controller);
//		this.btnAddAppointment2.setActionCommand(Actions.BTN_SIGNUP_DOCTOR.toString());
//		this.btnAddAppointment2.setBorder(null);
//		this.btnAddAppointment2.setBackground(Color.DARK_GRAY);
//		this.btnAddAppointment2.setFocusable(false);
//		this.pn2.add(btnAddAppointment2);
//		
//		this.add(pn2);
//		
//		pn3 = new JPanel();
//		pn3.setBackground(Color.GRAY);
//		pn3.setBorder(BorderFactory.createTitledBorder("Exit..."));
//		
//		this.btnExit = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/exit.png")).getImage().getScaledInstance(100, 100, 100)));
//		this.btnExit.addActionListener(controller);
//		this.btnExit.setActionCommand(Actions.EXIT.toString());
//		this.btnExit.setBorder(null);
//		this.btnExit.setBackground(Color.DARK_GRAY);
//		this.btnExit.setBounds(10, 110, 100, 100);
//		this.btnExit.setFocusable(false);
//		this.pn3.add(btnExit);
//		
//		this.add(pn3);
	}

	public static void designWindow() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}
	// ------------------------

	

	public static void main(String[] args) {
		designWindow();
		new MainFrame(null);
	}

}
