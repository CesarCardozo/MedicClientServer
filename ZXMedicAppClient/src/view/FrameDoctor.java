package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controller.Actions;
import controller.ControllerClient;
import model.entity.Appointment;
import model.entity.Doctor;

public class FrameDoctor extends JFrame {

	private JPanel pn1, pn2, pn3;
	private JButton btnAddAppo, btnDlete, btnAttend;
	private DefaultTableModel modelTAppoint;
	private JTable tableAppoitn;
	private JButton btnExit;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameDoctor(ControllerClient controller, Doctor doctor, ArrayList<Appointment> appointments) {
		this.setVisible(true);
		init(controller, doctor);
		if (appointments != null && !appointments.isEmpty()) {
			fillTable(appointments);
		}
	}

	private void init(ControllerClient controller, Doctor doctor) {
		this.setLayout(new GridLayout(3, 1));
		addPnl(controller, doctor);
		getContentPane().setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		setIconImage(new ImageIcon(getClass().getResource(ConstansUI.PATH_IMAGE_PERFIL)).getImage());
		setTitle("UPTC-EPS DOCTOR MANAGER");
		setSize(1020, 610);
		setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeConection();
				System.exit(0);
			}
		});
	}

	private void addPnl(ControllerClient controller, Doctor doctor) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// ------ Boton salir ---------
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weighty = 1.5; 
		c.anchor = GridBagConstraints.LAST_LINE_END; 
		c.insets = new Insets(20, 0, 0, 20); 
		c.gridx = 2; 
		c.gridwidth = 1; 
		c.gridy = 0; 
		this.btnExit = new JButton(new ImageIcon(
				new ImageIcon(getClass().getResource("/img/cancel.png")).getImage().getScaledInstance(25, 25, 25)));
		this.btnExit.addActionListener(controller);
		this.btnExit.setActionCommand(Actions.CANCEL_DOCTOR.toString());
		this.btnExit.setText(" Back  ");
		this.btnExit.setBackground(Color.decode("#007cc0"));
		this.btnExit.setBounds(20, 110, 100, 100);
		this.btnExit.setFocusable(false);
		add(btnExit, c);

		//---- Nombre doctor
		JLabel lbNameUser = new JLabel("Dr. " + doctor.getName());
		lbNameUser.setFont(new java.awt.Font("Tahoma", 1, 14));
		lbNameUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNameUser.setVerticalAlignment(SwingConstants.CENTER);
		lbNameUser.setForeground(Color.white);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(20, 60, 0, 10);
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		add(lbNameUser, c);
		
		// ----- botones agregar y eliminar
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(80, 20, 0, 0);
		c.gridx = 0;
		c.gridy = 1;
		this.pn1 = new JPanel();
		this.btnAddAppo = new JButton(new ImageIcon(
				new ImageIcon(getClass().getResource("/img/calendario.png")).getImage().getScaledInstance(40, 40, 40)));
		this.btnAddAppo.addActionListener(controller);
		this.btnAddAppo.setActionCommand(Actions.CREATE_APPOINT.toString());
		this.btnAddAppo.setText("Create Appointment  ");
		this.btnAddAppo.setBackground(Color.decode("#caccd1"));
		this.btnAddAppo.setFocusable(false);
		this.btnDlete = new JButton(new ImageIcon(
				new ImageIcon(getClass().getResource("/img/basura.png")).getImage().getScaledInstance(40, 40, 40)));
		this.btnDlete.addActionListener(controller);
		this.btnDlete.setActionCommand(Actions.TO_DELETE.toString());
		this.btnDlete.setText("Delete  ");
		this.btnDlete.setBackground(Color.decode("#caccd1"));
		this.btnDlete.setFocusable(false);
		pn1.add(btnAddAppo);
		pn1.add(btnDlete);
		pn1.setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		add(pn1, c);

		JLabel lbr = new JLabel("");
		lbr.setHorizontalAlignment(SwingConstants.LEFT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		c.gridx = 1;
		c.gridy = 1;
		add(lbr, c);

		// --------- Tabla
		createTablePerson(c);

		//---------- Boton atender Cita
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 0.2;
		c.anchor = GridBagConstraints.CENTER; // bottom of space
		c.insets = new Insets(10, 50, 70, 90); 
		c.weightx = 0.2;
		c.gridx = 1; 
		c.gridwidth = 1; 
		c.gridy = 4; 
		this.btnAttend = new JButton(new ImageIcon(
				new ImageIcon(getClass().getResource("/img/ok.png")).getImage().getScaledInstance(40, 40, 40)));
		this.btnAttend.addActionListener(controller);
		this.btnAttend.setActionCommand(Actions.BTN_ATTEND_APPOINTMENT.toString());
		this.btnAttend.setText("   Attend Appointment ");
		this.btnAttend.setBackground(ConstansUI.COLOR_BOTON);
		this.btnAttend.setFocusable(false);
		add(btnAttend, c);
	}

	private void createTablePerson(GridBagConstraints c) {

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.ipady = 180; // make this component tall
		c.insets = new Insets(10, 50, 0, 20);
		c.weighty = 2.0; // request any extra vertical space
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridheight = 2;
		c.gridx = 0;
		c.gridy = 2;
		this.pn2 = new JPanel(new BorderLayout());
		pn2.setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		pn2.setBorder(BorderFactory.createTitledBorder(null, "Mis Citas", SwingConstants.RIGHT, 1,
				new java.awt.Font("Tahoma", 1, 18), Color.white));

		modelTAppoint = new DefaultTableModel();
		modelTAppoint.setColumnIdentifiers(new String[] { "Date", "Status", "Patient" });
		tableAppoitn = new JTable(modelTAppoint);
		tableAppoitn.setBackground(Color.gray);

		JScrollPane jScrollPane = new JScrollPane(tableAppoitn);
		jScrollPane.setBackground(Color.black);
		
		this.pn2.add(jScrollPane, BorderLayout.CENTER);

		this.add(pn2, c);
	}

	public void fillTable(ArrayList<Appointment> appointmentList) {
		this.clearTable();
		for (int i = 0; i < appointmentList.size(); i++) {
			modelTAppoint.addRow(new Object[] { appointmentList.get(i).getDate(), appointmentList.get(i).getStatus(),
					appointmentList.get(i).getPatient() });
		}
	}

	public void clearTable() {
		modelTAppoint.setRowCount(0);
	}

	public static void main(String[] args) {
		new FrameDoctor(null, null, null);
	}
}