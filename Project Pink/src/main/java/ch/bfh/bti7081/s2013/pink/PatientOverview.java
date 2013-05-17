package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Session;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class PatientOverview extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	private Label name;
	private Label prename;
	private Label sessionBegin;
	private Label nameValue;
	private Label prenameValue;
	private Label sessionBeginValue;
	private String test;
	private int positionX;
	private int positionY;
	private Patient patient;
	private Session session;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public PatientOverview(int posX, int posY, Patient patient, Session session) {
		positionX = posX;
		positionY = posY;
		this.patient = patient;
		this.session = session;
		buildMainLayout();
		setCompositionRoot(mainLayout);
		// TODO add user code here
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
			// Name
			name = new Label();
			name.setImmediate(false);
			name.setWidth("-1px");
			name.setHeight("-1px");
			name.setValue("Name");
			mainLayout.addComponent(name, "top:" + positionX
					+ "px;left:212.0px;");

			// Name Value
			nameValue = new Label();
			nameValue.setImmediate(false);
			nameValue.setWidth("-1px");
			nameValue.setHeight("-1px");
		nameValue.setValue(patient.getName());
			mainLayout.addComponent(nameValue, "top:" + positionX
					+ "px;left:312.0px;");
			positionX += 30;
			// Prename
			prename = new Label();
			prename.setImmediate(false);
			prename.setWidth("-1px");
			prename.setHeight("-1px");
			prename.setValue("Prename");
			mainLayout.addComponent(prename, "top:" + positionX
					+ "px;left:212.0px;");

			// Prename Value
			prenameValue = new Label();
			prenameValue.setImmediate(false);
			prenameValue.setWidth("-1px");
			prenameValue.setHeight("-1px");
		prenameValue.setValue(patient.getFirstName());
			mainLayout.addComponent(prenameValue, "top:" + positionX
					+ "px;left:312.0px;");
			positionX += 30;

			// SessionBegin
			sessionBegin = new Label();
			sessionBegin.setImmediate(false);
			sessionBegin.setWidth("-1px");
			sessionBegin.setHeight("-1px");
			sessionBegin.setValue("Session begin:");
			mainLayout.addComponent(sessionBegin, "top:" + positionX
					+ "px;left:212.0px;");

			// SessionBegin Value
			sessionBeginValue = new Label();
			sessionBeginValue.setImmediate(false);
			sessionBeginValue.setWidth("-1px");
			sessionBeginValue.setHeight("-1px");
		sessionBeginValue.setValue(session.getTimeStart());
			mainLayout.addComponent(sessionBeginValue, "top:" + positionX
					+ "px;left:312.0px;");

		return mainLayout;
	}

}