package ch.bfh.bti7081.s2013.pink.view;

import java.util.Date;
import java.util.List;

import ch.bfh.bti7081.s2013.pink.medication.LocalMedicalService;
import ch.bfh.bti7081.s2013.pink.model.Dose;
import ch.bfh.bti7081.s2013.pink.model.Dose.Period;
import ch.bfh.bti7081.s2013.pink.model.Medication;
import ch.bfh.bti7081.s2013.pink.model.Session;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.NumberField;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * View Class to show a window to prescribe a medicament
 * 
 * @author Christoph Seiler (Christoph Seiler) Date: 22.05.13 Time: 18:40
 */
public class MedicalPrescriptionView extends NavigationView {
	private static final long serialVersionUID = -7838749079897250776L;

	private Session privateSession;

	Button btnPrescribe;
	Button btnUnsafePrescribe;

	private VerticalLayout layout = new VerticalLayout();

	private Medication valueMedication;
	private Date valueDateFrom, valueDateTo;
	private String valueReason;
	// private Dose valueDose = new Dose(amount, multiplier, period);
	private int amount = 1;
	private int multiplier = 1;
	private Period period = Period.DAY;

	/**
	 * not used anymore Delete before Finisehing // private int valueDoseAmount;
	 * // private int valueDoseMultiplier; // private Period valueDosePeriod;
	 **/

	LocalMedicalService localMedicalService = new LocalMedicalService();

	/**
	 * Creates the View.
	 * 
	 * 
	 * @return a View
	 */
	public MedicalPrescriptionView(Session patientSession) {

		/**
		 * get the active Session and set the Windows initials
		 */
		this.privateSession = patientSession;
		setCaption("Prescription");
		setSizeFull();

		layout.setMargin(true);
		layout.addComponent(new Label(privateSession.getTimeEnd().toString()));

		VerticalComponentGroup group = new VerticalComponentGroup();
		layout.addComponent(group);

		/**
		 * Add a ComboBox to select a medicament from the medicament database
		 * Set valueMedication to the chosen value
		 */
		ComboBox medicineSel = new ComboBox("Medicine");

		for (Medication medicine : localMedicalService.searchForMedicaments("")) {
			medicineSel.addItem(medicine.getName());
		}
		medicineSel.setValue("enter medicine name");

		medicineSel.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 3289141036055777064L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				String value = event.getProperty().getValue().toString();
				List<Medication> values = localMedicalService
						.searchForMedicaments(value);
				valueMedication = values.get(0);

			}
		});
		medicineSel.setWidth("100%");

		group.addComponent(medicineSel);

		/**
		 * Add a TextField to enter the doseAmount Set the dose Amount to the
		 * inserted Value
		 * 
		 * @default = 0
		 */
		HorizontalLayout doseGroup = new HorizontalLayout();
		group.addComponent(doseGroup);
		NumberField doseAmount = new NumberField();

		// Handle changes in the value
		doseAmount.setValue(String.valueOf(amount));
		doseAmount.setWidth("3em");
		doseAmount.addTextChangeListener(new TextChangeListener() {
			private static final long serialVersionUID = 4376224365227823135L;

			@Override
			public void textChange(TextChangeEvent event) {
				if (event.getText().length() > 0)
					amount = Integer.parseInt(event.getText());
			}
		});
		doseGroup.addComponent(doseAmount);

		doseGroup.addComponent(new Label(" unit(s), "));

		/**
		 * Add a TextField to enter the dose Multiplier Set the dose Multiplier
		 * to the inserted Value
		 * 
		 * @default = 0
		 */
		NumberField doseMultiplier = new NumberField();
		doseMultiplier.setValue(String.valueOf(multiplier));
		doseMultiplier.setWidth("3em");
		// Handle changes in the value
		doseMultiplier.addTextChangeListener(new TextChangeListener() {
			private static final long serialVersionUID = -7622388979175553469L;

			@Override
			public void textChange(TextChangeEvent event) {
				if (event.getText().length() > 0)
					multiplier = Integer.parseInt(event.getText());
			}
		});
		doseGroup.addComponent(doseMultiplier);

		doseGroup.addComponent(new Label(" times per "));

		/**
		 * Add a Native Select to enter choose a Period Set the dose Period to
		 * the chosen value
		 * 
		 * @default = 0
		 */
		NativeSelect dosePeriod = new NativeSelect();
		for (Period period : Period.values()) {
			dosePeriod.addItem(period);
		}
		dosePeriod.setValue(period);
		dosePeriod.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = -6657351913129873449L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				period = (Period) event.getProperty().getValue();
			}
		});
		dosePeriod.setWidth("100%");

		doseGroup.addComponent(dosePeriod);

		/**
		 * Add a Date Field to enter the beginning of the prescription Set the
		 * dateFrom
		 * 
		 * @default = Actual Date
		 */
		DateField dateFrom = new DateField("From");
		dateFrom.setValue(new Date());
		dateFrom.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = -6042814295914237555L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				valueDateFrom = (Date) event.getProperty().getValue();

			}
		});
		dateFrom.setWidth("100%");
		group.addComponent(dateFrom);

		/**
		 * Add a Date Field to enter the end of the prescription Set the dateTo
		 * 
		 * @default = Actual Date
		 */

		DateField dateTo = new DateField("To");
		dateTo.setValue(new Date());
		dateTo.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 5607231465260025486L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				valueDateTo = (Date) event.getProperty().getValue();

			}
		});
		dateTo.setWidth("100%");
		group.addComponent(dateTo);

		/**
		 * Add a Text Field to enter a reason for the prescription Set the
		 * reason
		 * 
		 * @default = ""
		 */
		TextField reason = new TextField("Reason");
		reason.setValue("");

		reason.addTextChangeListener(new TextChangeListener() {
			private static final long serialVersionUID = 557853181333568046L;

			@Override
			public void textChange(TextChangeEvent event) {
				valueReason = event.getText();
			}

		});
		reason.setWidth("100%");
		group.addComponent(reason);

		/**
		 * TO DO It should be possoble to add notes here
		 * 
		 */

		/**
		 * Add a Label to show the doctor from the Session
		 */
		// Does this make sense?
		// Label doctor = new Label("the doctor");
		// doctor.setValue(privateSession.getDoctor().getName());
		// doctor.setWidth("100%");
		//
		// group.addComponent(doctor);

		/**
		 * Add a Button to Prescribe a medication
		 * 
		 * @Param patient = patient from the session
		 * @Param medication = selected medication
		 * @Param dose = selected dose by amount, multiplier and period
		 * @Param doctor = doctor from the session
		 */
		btnPrescribe = new Button("Prescribe", new Button.ClickListener() {
			private static final long serialVersionUID = 6264139734209239541L;

			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {
				localMedicalService.prescribeMedicament(privateSession
						.getPatient(), valueMedication, new Dose(amount,
						multiplier, period), valueReason, valueDateFrom,
						valueDateTo);
			}
		});

		layout.addComponent(btnPrescribe);

		/**
		 * btnUnsafePrescribe = new Button("Prescribe Unsafe", new
		 * Button.ClickListener() {
		 * 
		 * @Override public void buttonClick(Button.ClickEvent clickEvent) { //
		 *           To change body of implemented methods use File | //
		 *           Settings | File Templates. } });
		 *           btnUnsafePrescribe.setEnabled(false);
		 * 
		 *           layout.addComponent(btnUnsafePrescribe);
		 * 
		 *           /**
		 * 
		 */

		setContent(layout);
	}
}
