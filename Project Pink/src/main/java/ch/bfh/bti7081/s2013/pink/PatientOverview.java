package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Session;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
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
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// Set BG
		Resource res = new ThemeResource("img/patient_bg.png");

		// Display the image without caption
		Image bgImage = new Image(null, res);
		positionY -= 60;
		mainLayout.addComponent(bgImage, "top:" + positionX + "px;left:"
				+ positionY + "px;");

		positionY += 60;
		positionX += 30;
		// Name
		name = new Label();
		name.setImmediate(false);
		name.setWidth("-1px");
		name.setHeight("-1px");
		name.setValue("Name");
		mainLayout.addComponent(name, "top:" + positionX + "px;left:"
				+ positionY + "px;");

		// Name Value
		nameValue = new Label();
		nameValue.setImmediate(false);
		nameValue.setWidth("-1px");
		nameValue.setHeight("-1px");
		nameValue.setValue(patient.getName());
		positionY += 100;
		mainLayout.addComponent(nameValue, "top:" + positionX + "px;left:"
				+ positionY + "px;");
		positionY -= 100;
		positionX += 30;
		// Prename
		prename = new Label();
		prename.setImmediate(false);
		prename.setWidth("-1px");
		prename.setHeight("-1px");
		prename.setValue("Prename");
		mainLayout.addComponent(prename, "top:" + positionX + "px;left:"
				+ positionY + "px;");

		// Prename Value
		prenameValue = new Label();
		prenameValue.setImmediate(false);
		prenameValue.setWidth("-1px");
		prenameValue.setHeight("-1px");
		prenameValue.setValue(patient.getFirstName());
		positionY += 100;
		mainLayout.addComponent(prenameValue, "top:" + positionX + "px;left:"
				+ positionY + "px;");
		positionY -= 100;

		Button button = new Button("Show details");

		positionY += 400;

		mainLayout.addComponent(button, "top:" + positionX + "px;left:"
				+ positionY + "px;");

		positionY -= 400;
		// Serve the image from the theme
		Resource res2 = new ThemeResource("img/mascot.png");

		// Display the image without caption
		Image image = new Image(null, res2);
		positionX -= 35;
		mainLayout.addComponent(image, "top:" + positionX + "px;left:490.0px;");
		positionX += 30;
		positionX += 35;

		// SessionBegin
		sessionBegin = new Label();
		sessionBegin.setImmediate(false);
		sessionBegin.setWidth("-1px");
		sessionBegin.setHeight("-1px");
		sessionBegin.setValue("Session begin:");
		mainLayout.addComponent(sessionBegin, "top:" + positionX + "px;left:"
				+ positionY + "px;");
		positionY += 100;

		// SessionBegin Value
		sessionBeginValue = new Label();
		sessionBeginValue.setImmediate(false);
		sessionBeginValue.setWidth("-1px");
		sessionBeginValue.setHeight("-1px");
		// TODO: Date formatting
		sessionBeginValue.setValue(String.valueOf(session.getTimeStart()));
		mainLayout.addComponent(sessionBeginValue, "top:" + positionX
				+ "px;left:" + positionY + "px;");

		return mainLayout;
	}

}