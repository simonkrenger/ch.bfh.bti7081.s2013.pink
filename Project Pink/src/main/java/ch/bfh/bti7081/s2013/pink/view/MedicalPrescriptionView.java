package ch.bfh.bti7081.s2013.pink.view;

import ch.bfh.bti7081.s2013.pink.medication.LocalMedicalService;
import ch.bfh.bti7081.s2013.pink.model.Dose.Period;
import ch.bfh.bti7081.s2013.pink.model.Session;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Created with IntelliJ IDEA. User: sc0238 Date: 24.05.13 Time: 17:06 To change
 * this template use File | Settings | File Templates.
 */
public class MedicalPrescriptionView extends NavigationView {
	private Session privateSession;

	Button btnPrescribe;
	Button btnUnsafePrescribe;

	private VerticalLayout layout = new VerticalLayout();

	private int valueDoseAmount;
	private int valueDoseMultiplier;
	private Period valueDosePeriod;

	public MedicalPrescriptionView(Session patientSession) {
		this.privateSession = patientSession;
		setCaption("Prescription");
		setSizeFull();

		LocalMedicalService localMedicalService = new LocalMedicalService();

		layout.addComponent(new Label(privateSession.getTimeEnd().toString()));

		// Select medicine ComboBox
		ComboBox medicineSel = new ComboBox("Medicine");
		medicineSel.setValue("initial Value");
		// for (Medication medicine :
		// localMedicalService.searchForMedicaments("")) {
		// medicineSel.addItem(medicine);
		// }

		layout.addComponent(medicineSel);

		// Look into Dose and MedicalPrescirption Classes for the Options

		// Create a text fields for Dose
		TextField doseAmount = new TextField("doseAmount");

		// Handle changes in the value
		doseAmount.setValue("initial value");
		doseAmount.addTextChangeListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				valueDoseAmount = Integer.parseInt(event.getText());
			}

		});

		layout.addComponent(doseAmount);

		// Create a text fields for Dose
		TextField doseMultiplier = new TextField("doseMultiplier");

		// Handle changes in the value
		doseMultiplier.addTextChangeListener(new TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				valueDoseMultiplier = Integer.parseInt(event.getText());
			}

		});

		layout.addComponent(doseMultiplier);

		// Create a text fields for Dose NativeSelect dosePeriod = new
		NativeSelect dosePeriod = new NativeSelect("DosePeriod");
		for (Period periods : Period.values()) {
			dosePeriod.addItem(periods.name());
		}
		dosePeriod.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				valueDosePeriod = (Period) event.getProperty().getValue();

			}
		});


		layout.addComponent(dosePeriod);

		// Dose consisting of amount, multiplier, period(Date From To)
		// Amount(2 Pillen) int multi(3mal) int period(Täglich) enum

		// Period From - To (Dates) (Not in MedicationPresription Yet

		// reason String

		// Notes Note
		// prescriber Doctor (from Session)

		// Collect Infromation From Form

		btnPrescribe = new Button("Prescribe", new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {
				btnUnsafePrescribe.setEnabled(!btnUnsafePrescribe.isEnabled());
			}
		});

		btnUnsafePrescribe = new Button("Prescribe Unsafe",
				new Button.ClickListener() {
					@Override
					public void buttonClick(Button.ClickEvent clickEvent) {
						// To change body of implemented methods use File |
						// Settings | File Templates.
					}
				});
		btnUnsafePrescribe.setEnabled(false);

		layout.addComponent(btnPrescribe);
		layout.addComponent(btnUnsafePrescribe);

		setContent(layout);
	}
}
