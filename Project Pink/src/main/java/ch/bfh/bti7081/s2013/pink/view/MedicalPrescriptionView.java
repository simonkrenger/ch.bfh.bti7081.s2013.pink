package ch.bfh.bti7081.s2013.pink.view;

import ch.bfh.bti7081.s2013.pink.medication.LocalMedicalService;
import ch.bfh.bti7081.s2013.pink.model.Session;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created with IntelliJ IDEA.
 * User: sc0238
 * Date: 24.05.13
 * Time: 17:06
 * To change this template use File | Settings | File Templates.
 */
public class MedicalPrescriptionView extends VerticalLayout implements View
{
    private Session privateSession;

    Button btnPrescribe;
    Button btnUnsafePrescribe;


    public MedicalPrescriptionView(Session patientSession)
    {
        this.privateSession = patientSession;
        setSizeFull();

		LocalMedicalService localMedicalService = new LocalMedicalService();

        addComponent(new Label(privateSession.getTimeEnd().toString()));

		// Select medicine ComboBox
		ComboBox medicineSel = new ComboBox("Medicine");
		medicineSel.setWidth(100, Unit.PIXELS);
		medicineSel.setValue(localMedicalService.searchForMedicaments("Enter medicine name"));
		addComponent(medicineSel);

		// Look into Dose and MedicalPrescirption Classes for the Options

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

        btnUnsafePrescribe = new Button("Prescribe Unsafe", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        btnUnsafePrescribe.setEnabled(false);

        addComponent(btnPrescribe);
        addComponent(btnUnsafePrescribe);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
