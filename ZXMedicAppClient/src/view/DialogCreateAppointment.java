package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.toedter.calendar.JCalendar;

import controller.Actions;
import controller.ControllerClient;
public class DialogCreateAppointment extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnOk;
	private JCalendar calendarG;
	private JSpinner sppinerH;
	private JSpinner sppinerM;
	private JPanel pn1, pn2, pn3;


	public DialogCreateAppointment(ControllerClient controller) {
		init(controller);
		setVisible(true);
	}


	private void init(ControllerClient controller) {
		this.setLayout(new GridLayout(5, 1));
		getContentPane().setBackground(Color.GRAY);
		setTitle("UPTC-EPS Create Appointment");
		setSize(ConstansUI.SIZE_WINDOW_X - 50, ConstansUI.SIZE_WINDOW_Y +120);
		setLocationRelativeTo(null);
		putElements(controller);
	}

	private void putElements(ControllerClient controller) {
		this.setLayout(new GridLayout(3, 1));
		this.pn1 = new JPanel();
		this.pn1.setLayout(new GridLayout(2, 1));
		pn1.setBorder(BorderFactory.createTitledBorder("Calendar - date"));
		this.calendarG = new JCalendar();
		this.pn1.add(calendarG);
		this.add(pn1);

		this.sppinerH = new JSpinner();
		this.sppinerH.setSize(100, 50);
		SpinnerNumberModel modeloSpinner = new SpinnerNumberModel();
		modeloSpinner.setMaximum(23);
		modeloSpinner.setMinimum(0);
		modeloSpinner.setStepSize(1);
		sppinerH.setModel(modeloSpinner);

		this.pn2 = new JPanel();
		this.pn2.setLayout(new GridLayout(1, 2));
		pn2.setBorder(BorderFactory.createTitledBorder("Hour"));
		this.sppinerM = new JSpinner();
		SpinnerNumberModel modeloSpinnerM = new SpinnerNumberModel();
		modeloSpinnerM.setMaximum(30);
		modeloSpinnerM.setMinimum(0);
		modeloSpinnerM.setStepSize(30);
		sppinerH.setModel(modeloSpinnerM);
		this.pn2.add(sppinerM);
		this.pn2.add(sppinerH);
		this.add(pn2);

		this.pn3 = new JPanel();
		this.btnOk = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/exit.png")).getImage().getScaledInstance(100, 100, 100)));
		this.btnOk.addActionListener(controller);
		this.btnOk.setActionCommand(Actions.OK_CREATE_APPOINT.toString());
		this.btnOk.setBorder(null);
		this.btnOk.setBackground(Color.DARK_GRAY);
		this.btnOk.setBounds(10, 110, 100, 100);
		this.btnOk.setFocusable(false);
		this.pn3.add(btnOk);
		this.add(pn3);
	}

	public Date getDate() {
		return new Date(calendarG.getCalendar().getTime().getYear(), calendarG.getCalendar().getTime().getMonth() ,calendarG.getCalendar().getTime().getDate(), (Integer) sppinerH.getValue(), (Integer) sppinerM.getValue() );
	}

	public static void main(String[] args) {
		new DialogCreateAppointment(null);
	}
}