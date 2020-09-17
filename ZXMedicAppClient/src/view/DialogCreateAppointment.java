package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.toedter.calendar.JCalendar;

import controller.Actions;
import controller.ControllerClient;

public class DialogCreateAppointment extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnOk;
	private JCalendar calendarG;
	private JSpinner sppinerH;
	private JSpinner sppinerM;
	private JPanel pn1, pn2, pn3;
	private JButton btnCancel;

	public DialogCreateAppointment(ControllerClient controller) {
		init(controller);
		setVisible(true);
	}

	private void init(ControllerClient controller) {
		designWindow();
		getContentPane().setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		setTitle("UPTC-EPS Create Appointment");
		setSize(ConstansUI.SIZE_WINDOW_X + 55, ConstansUI.SIZE_WINDOW_Y + 45);
		setLocationRelativeTo(null);
		putElements(controller);

	}

	private void putElements(ControllerClient controller) {

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel jlTitlePanel = new JLabel("Appointment");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 20);
		c.gridx = 0;
		c.gridy = 0;
		jlTitlePanel.setFont(new java.awt.Font("Tahoma", 1, 18));
		jlTitlePanel.setForeground(Color.white);
		add(jlTitlePanel, c);

		// ------ Calendario ------
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.ipady = 50; // make this component tall
		c.insets = new Insets(0, 20, 0, 20);
		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		this.pn1 = new JPanel();
		this.pn1.setLayout(new GridLayout(1, 1));
		pn1.setBorder(BorderFactory.createTitledBorder("Calendar - date"));
		pn1.setBackground(Color.decode("#f6f6f5"));
		this.calendarG = new JCalendar();
		this.calendarG.setBackground(Color.decode("#f6f6f5"));
		this.calendarG.setDecorationBackgroundVisible(false);
		this.calendarG.setDecorationBackgroundColor(Color.red);
		this.pn1.add(calendarG);
		this.add(pn1);
		add(pn1, c);

		// ------ Hora ------
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weighty = 0.5;
		c.insets = new Insets(0, 20, 0, 20);
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.gridy = 2; // third row
		this.sppinerH = new JSpinner();
		sppinerH.setEditor(new JSpinner.DefaultEditor(sppinerH));
		this.sppinerH.setSize(100, 10);
		SpinnerNumberModel modeloSpinner = new SpinnerNumberModel();
		modeloSpinner.setMaximum(23);
		modeloSpinner.setMinimum(0);
		modeloSpinner.setStepSize(1);
		sppinerH.setModel(modeloSpinner);
		this.pn2 = new JPanel();
		this.pn2.setLayout(new GridLayout(1, 2));
		pn2.setBackground(Color.decode("#f6f6f5"));
		pn2.setBorder(BorderFactory.createTitledBorder("Hour"));
		this.sppinerM = new JSpinner();
		sppinerM.setEditor(new JSpinner.DefaultEditor(sppinerM));
		SpinnerNumberModel modeloSpinnerM = new SpinnerNumberModel();
		modeloSpinnerM.setMaximum(30);
		modeloSpinnerM.setMinimum(0);
		modeloSpinnerM.setStepSize(30);
		sppinerM.setModel(modeloSpinnerM);
		this.pn2.add(sppinerH);
		this.pn2.add(sppinerM);
		add(pn2, c);

		// ------ Botones ------
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 10, 15, 20);
		c.gridx = 1;
		c.gridy = 3;
		this.pn3 = new JPanel();
		this.pn3.setBackground(Color.decode(ConstansUI.COLOR_BACKGROUND_APP));
		this.btnOk = new JButton(new ImageIcon(
				new ImageIcon(getClass().getResource("/img/ok.png")).getImage().getScaledInstance(40, 40, 40)));
		this.btnOk.addActionListener(controller);
		this.btnOk.setText("Crear   ");
		this.btnOk.setActionCommand(Actions.OK_CREATE_APPOINT.toString());
		this.btnOk.setBackground(Color.green);
		this.btnOk.setBounds(10, 110, 100, 100);
		this.btnOk.setFocusable(false);
		this.pn3.add(btnOk);

		this.btnCancel = new JButton(new ImageIcon(
				new ImageIcon(getClass().getResource("/img/cancel.png")).getImage().getScaledInstance(40, 40, 40)));
		this.btnCancel.setText("Cancelar  ");
		this.btnCancel.setBackground(ConstansUI.COLOR_BOTON);
		this.btnCancel.setFocusable(false);
		this.btnCancel.addActionListener(controller);
		this.btnCancel.setActionCommand(Actions.CANCEL_CREATE_APPOINTMENT.toString());
		this.pn3.add(btnCancel);
		add(pn3, c);
	}

	@SuppressWarnings("deprecation")
	public Date getDate() {
		return new Date(calendarG.getCalendar().getTime().getYear(), calendarG.getCalendar().getTime().getMonth(),
				calendarG.getCalendar().getTime().getDate(), (Integer) sppinerH.getValue(),
				(Integer) sppinerM.getValue());
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
			java.util.logging.Logger.getLogger(DialogLogin.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(DialogLogin.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(DialogLogin.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(DialogLogin.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
	}

	public static void main(String[] args) {
		new DialogCreateAppointment(null);
	}
}