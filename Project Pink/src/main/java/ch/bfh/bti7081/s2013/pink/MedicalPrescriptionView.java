package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.Session;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * View to prescribe medication to a patient.
 * 
 * @author Christoph Seiler
 * @author Franziska Corradi
 * 
 */
public class MedicalPrescriptionView extends VerticalLayout implements View
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 797608741077712153L;

	/**
	 * Session in which the prescription takes place
	 */
	private Session privateSession;

    Button btnPrescribe;
    Button btnUnsafePrescribe;

	/**
	 * Constructor for the view class.
	 * 
	 * @param patientSession
	 *            Session in which the prescripion takes place
	 */
    public MedicalPrescriptionView(Session patientSession)
    {
        this.privateSession = patientSession;
        setSizeFull();

        addComponent(new Label(privateSession.getTimeEnd().toString()));

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
