package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Actions;
import controller.ControllerClient;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btnAddPerson,  btnAddDoctor,  btnUptc;
	private DialogLogin dialogLogin;

	public MainFrame(ControllerClient controller) {
		this.setVisible(true);
		init(controller);

	}

	private void init(ControllerClient controller) {
		designWindow();
		this.setLayout(new GridLayout(4, 1));
		getContentPane().setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		addBtns(controller);
		setIconImage(new ImageIcon(getClass().getResource("/img/appointment.png")).getImage());
		setTitle("UPTC-EPS LOGIN");
		setSize(ConstansUI.SIZE_WINDOW_X, ConstansUI.SIZE_WINDOW_Y + 80);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeConection();
			    System.exit(0);
			}
		});
	}

	private void addBtns(ControllerClient controller) {
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

		//----Panel formulario Login
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.5;
		gbc.weighty = 0.3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		dialogLogin = new DialogLogin(controller);
		add(dialogLogin, gbc);
		 
		//----Botones de registrar
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
		this.btnAddPerson.setActionCommand(Actions.BTN_SIGNUP_PATIENT.toString());
		this.btnAddPerson.setBorder(null);
		this.btnAddPerson.setBackground(ConstansUI.COLOR_BOTON);
		this.btnAddPerson.setFocusable(false);
		pnbBtnRegister.add(this.btnAddPerson);

		this.btnAddDoctor = new JButton("Register Doctor");
		ImageIcon iconobtn = new ImageIcon("/img/appointment.png");
		this.btnAddDoctor.setIcon(iconobtn);
		this.btnAddDoctor.addActionListener(controller);
		this.btnAddDoctor.setActionCommand(Actions.BTN_SIGNUP_DOCTOR.toString());
		this.btnAddDoctor.setBackground(Color.decode("#ffc20e"));
		pnbBtnRegister.add(this.btnAddDoctor);
		
		add(pnbBtnRegister, gbc);
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

	public String getId() {
		return this.dialogLogin.getId();
	}
	
	public String getPassword() {
		return this.dialogLogin.getPassword();
	}

	public DialogLogin getDialogLogin() {
		return dialogLogin;
	}
	
	public static void main(String[] args) {
	//	designWindow();
		MainFrame mf = new MainFrame(null);
		
	}

}
