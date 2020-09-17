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
import javax.swing.table.DefaultTableModel;

import controller.Actions;
import controller.ControllerClient;
import model.entity.Appointment;
import model.entity.Patient;

public class FramePatient extends JFrame {

	private JPanel pn1, pn2, pn3;
	private JButton btnAddAppo, btnDlete;
	private DefaultTableModel modelTAppoint;
	private JTable tableAppoitn;
	private JButton btnExit;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FramePatient(ControllerClient controller, Patient patient, ArrayList<Appointment> appointments) {
		this.setVisible(true);
		init(controller, patient);
		if (appointments != null && !appointments.isEmpty()) {
			fillTable(appointments);
		}
	}

	private void init(ControllerClient controller, Patient patient) {
		this.setLayout(new GridLayout(3, 1));
		addPnl(controller, patient);
		getContentPane().setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		setIconImage(new ImageIcon(getClass().getResource(ConstansUI.PATH_IMAGE_PERFIL)).getImage());
		setTitle("UPTC-EPS PATIENT MANAGER");
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

	private void addPnl(ControllerClient controller, Patient patient) {

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// ------ Boton salir ---------
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weighty = 1.5;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.insets = new Insets(0, 0, 0, 20);
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 0;
		this.btnExit = new JButton(new ImageIcon(
				new ImageIcon(getClass().getResource("/img/cancel.png")).getImage().getScaledInstance(30, 30, 30)));
		this.btnExit.addActionListener(controller);
		this.btnExit.setActionCommand(Actions.CANCEL_PATIENT.toString());
		this.btnExit.setText("  Back ");
		this.btnExit.setBackground(ConstansUI.COLOR_BOTON_BACK);
		this.btnExit.setBounds(10, 110, 100, 100);
		this.btnExit.setFocusable(false);
		this.add(btnExit, c);

		// ---- Nombre paciente
		String texto = "<html>Nombre del paciente <P>" +
				 "<html>  " + patient.getName() + "<P>";
		JLabel lbNameUser = new JLabel(texto);
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
		//c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(40, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 1;
		this.pn1 = new JPanel();
		this.btnAddAppo = new JButton(new ImageIcon(
				new ImageIcon(getClass().getResource("/img/cita.png")).getImage().getScaledInstance(40, 40, 40)));
		this.btnAddAppo.addActionListener(controller);
		this.btnAddAppo.setActionCommand(Actions.PATIENT_BOOK_APPOINT.toString());
		this.btnAddAppo.setText("Book Appointment");
		this.btnAddAppo.setBackground(ConstansUI.COLOR_BOTON_OK);
		this.btnAddAppo.setFocusable(false);
		this.btnDlete = new JButton(new ImageIcon(
				new ImageIcon(getClass().getResource("/img/basura.png")).getImage().getScaledInstance(40, 40, 40)));
		this.btnDlete.addActionListener(controller);
		this.btnDlete.setActionCommand(Actions.BTN_CANCEL_APPOINTMENT.toString());
		this.btnDlete.setText("Delete");;
		this.btnDlete.setBackground(ConstansUI.COLOR_BOTON_BLANCO);
		this.btnDlete.setFocusable(false);
		pn1.add(btnAddAppo);
		pn1.add(btnDlete);
		pn1.setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		this.add(pn1, c);

		JLabel lbr = new JLabel("");
		lbr.setHorizontalAlignment(SwingConstants.LEFT);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2;
		c.gridx = 1;
		c.gridy = 1;
		add(lbr, c);
		
		//----- Tabla pacientes
		createTablePerson(c);
	}

	private void createTablePerson(GridBagConstraints c) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.ipady = 220; // make this component tall
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
		modelTAppoint.setColumnIdentifiers(new String[] { "Date", "Status", "Doctor" });
		tableAppoitn = new JTable(modelTAppoint);
		tableAppoitn.setBackground(Color.gray);

		JScrollPane jScrollPane = new JScrollPane(tableAppoitn);
		jScrollPane.setBackground(Color.black);
		this.pn2.add(jScrollPane, BorderLayout.CENTER);
		this.add(pn2,c);

	}

	public void fillTable(ArrayList<Appointment> appointmentList) {
		this.clearTable();
		String status = appointmentList.get(i).getStatus();
		for (int i = 0; i < appointmentList.size(); i++) {
			modelTAppoint.addRow(new Object[] { appointmentList.get(i).getDate(), appointmentList.get(i).getStatus(),
					appointmentList.get(i).getDoctor() });
		}
	}

	public void clearTable() {
		modelTAppoint.setRowCount(0);
	}

	public static void main(String[] args) {
		new FramePatient(null, new Patient("1", "stuar", "333", "jj@k", "123"), null);
	}
}
