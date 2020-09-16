package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Actions;
import controller.ControllerClient;
import model.entity.Appointment;

public class FrameDoctor extends JFrame{

	private JPanel pn1, pn2, pn3;
	private JButton btnAddAppo, btnDlete;
	private DefaultTableModel modelTAppoint;
	private JTable tableAppoitn;
	private JButton btnExit;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameDoctor(ControllerClient controller) {
		this.setVisible(true);
		init(controller);
	}

	private void init(ControllerClient controller) {
		this.setLayout(new GridLayout(3, 1));
		addPnl(controller);
		getContentPane().setBackground(Color.DARK_GRAY);
		setIconImage(new ImageIcon(getClass().getResource(ConstansUI.PATH_IMAGE_PERFIL)).getImage());
		setTitle("UPTC-EPS DOCTOR MANAGER");
		setSize(490, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void addPnl(ControllerClient controller) {
		this.pn1 = new JPanel();
		this.btnAddAppo = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/calendario.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnAddAppo.addActionListener(controller);
//		this.btnAddAppo.setActionCommand(Actions.PATIENT.toString());
		this.btnAddAppo.setBorder(null);
		this.btnAddAppo.setBackground(Color.DARK_GRAY);
		this.btnAddAppo.setFocusable(false);
		this.btnDlete = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/basura.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnDlete.addActionListener(controller);
//		this.btnDlete.setActionCommand(Actions.PATIENT.toString());
		this.btnDlete.setBorder(null);
		this.btnDlete.setBackground(Color.DARK_GRAY);
		this.btnDlete.setFocusable(false);
		pn1.add(btnAddAppo);
		pn1.add(btnDlete);
		pn1.setBackground(Color.DARK_GRAY);
		this.add(pn1);

		createTablePerson();

		this.btnExit = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/exit.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnExit.addActionListener(controller);
//		this.btnExit.setActionCommand(Actions.EXIT_DOCTOR.toString());
		this.btnExit.setBorder(null);
		this.btnExit.setBackground(Color.DARK_GRAY);
		this.btnExit.setBounds(10, 110, 100, 100);
		this.btnExit.setFocusable(false);
		this.pn3 = new JPanel();
		pn3.setBackground(Color.DARK_GRAY);
		this.pn3.add(btnExit);
		this.add(pn3);
	}

	private void createTablePerson() {
		modelTAppoint = new DefaultTableModel();
		modelTAppoint.setColumnIdentifiers(new String[] {"Date", "Status", "Patient"});
		tableAppoitn = new JTable(modelTAppoint);
		tableAppoitn.setBackground(Color.gray);

		JScrollPane jScrollPane = new JScrollPane(tableAppoitn);
		jScrollPane.setBackground(Color.black);
		this.pn2 = new JPanel();
		pn2.setBackground(Color.DARK_GRAY);
		this.pn2.add(jScrollPane, BorderLayout.WEST);
		this.add(pn2);

	}

	public void fillTable(ArrayList<Appointment> appointmentList) {
		this.clearTable();
		for (int i = 0; i < appointmentList.size(); i++) {
			modelTAppoint.addRow(new Object[] {appointmentList.get(i).getDate(), appointmentList.get(i).getStatus(), appointmentList.get(i).getPatient()});
		}
	}

	public void clearTable() {
		modelTAppoint.setRowCount(0);
	}

	public static void main(String[] args) {
		new FrameDoctor(null);
	}
}