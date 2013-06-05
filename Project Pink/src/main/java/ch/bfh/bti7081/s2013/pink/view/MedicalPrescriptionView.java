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

	private Session privateSession;

	Button btnPrescribe;
	Button btnUnsafePrescribe;

	private VerticalLayout layout = new VerticalLayout();

	private Medication valueMedication;
	private Date valueDateFrom, valueDateTo;
	private String valueReason;
	// private Dose valueDose = new Dose(amount, multiplier, period);
	private int amout;
	private int multiplier;
	private Period period;

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
		NumberField doseAmount = new NumberField("Amount");

		// Handle changes in the value
		doseAmount.setValue("0");
		doseAmount.setWidth(30, Unit.PIXELS);
		doseAmount.addTextChangeListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				amout = Integer.parseInt(event.getText());
			}

		});
		doseAmount.setWidth("100%");

		group.addComponent(doseAmount);

		/**
		 * Add a TextField to enter the dose Multiplier Set the dose Multiplier
		 * to the inserted Value
		 * 
		 * @default = 0
		 */
		NumberField doseMultiplier = new NumberField("Multiplier");
		doseMultiplier.setValue("0");
		doseMultiplier.setWidth(30, Unit.PIXELS);
		// Handle changes in the value
		doseMultiplier.addTextChangeListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				multiplier = Integer.parseInt(event.getText());
			}

		});
		doseMultiplier.setWidth("100%");

		group.addComponent(doseMultiplier);

		/**
		 * Add a Native Select to enter choose a Period Set the dose Period to
		 * the chosen value
		 * 
		 * @default = 0
		 */
		NativeSelect dosePeriod = new NativeSelect("Period");
		for (Period periods : Period.values()) {
			dosePeriod.addItem(periods.name());
		}
		dosePeriod.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				period = Period
						.valueOf((String) event.getProperty().getValue());
			}
		});
		dosePeriod.setWidth("100%");

		group.addComponent(dosePeriod);

		/**
		 * Add a Date Field to enter the beginning of the prescription Set the
		 * dateFrom
		 * 
		 * @default = Actual Date
		 */
		DateField dateFrom = new DateField("From");
		dateFrom.setValue(new Date());
		dateFrom.addValueChangeListener(new ValueChangeListener() {

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
		Label doctor = new Label("the doctor");
		doctor.setValue(privateSession.getDoctor().getName());
		doctor.setWidth("100%");

		group.addComponent(doctor);

		/**
		 * Add a Button to Prescribe a medication
		 * 
		 * @Param patient = patient from the session
		 * @Param medication = selected medication
		 * @Param dose = selected dose by amount, multiplier and period
		 * @Param doctor = doctor from the session
		 */
		btnPrescribe = new Button("Prescribe", new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {
				localMedicalService.prescribeMedicament(privateSession
						.getPatient(), valueMedication, new Dose(amout,
						multiplier, period), valueDateFrom, valueDateTo);
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
