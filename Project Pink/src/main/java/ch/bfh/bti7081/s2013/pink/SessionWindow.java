/**
 * Class that shows the upcomming sessions with the patients
 *
 * @author	Marco Berger	<lostchall@gmail.com>
 */
package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.Patient;
import ch.bfh.bti7081.s2013.pink.model.Session;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class SessionWindow extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	private Label title;
	private Patient patient;
	private Session session;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public SessionWindow(Session session, Patient patient) {
		this.session = session;
		this.patient = patient;
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
		setHeight("100%");

		// Create title field
		TextField tf = new TextField("Patient session");

		mainLayout.addComponent(tf, "top:200px;left:200px;");

		// Session start time
		title = new Label();
		title.setValue("Start time");
		mainLayout.addComponent(title, "top:100px;left:200px;");

		// start time value
		title = new Label();
		title.setValue(session.getTimeStart().toString());
		mainLayout.addComponent(title, "top:100px;left:270px;");

		// Session end time
		title = new Label();
		title.setValue("End time");
		mainLayout.addComponent(title, "top:100px;left:200px;");

		// end time value
		title = new Label();
		title.setValue(session.getTimeEnd().toString());
		mainLayout.addComponent(title, "top:100px;left:270px;");
		return mainLayout;
	}

	public class SearchButton extends CustomComponent implements
			Button.ClickListener {
		Button searchbutton;

		public SearchButton() {
			// Create a Button with the given caption.
			searchbutton = new Button("Search");

			// Listen for ClickEvents.
			searchbutton.addListener(this);

			setCompositionRoot(searchbutton);
		}

		/** Handle search events for the button. */
		public void buttonClick(Button.ClickEvent event) {
			// Open Search
			// PatientDetailView detailledPatientView = new PatientDetailView(
			// patient);
			// mainLayout.addComponent(detailledPatientView);

		}
	}

	public void patientSearch(String searchTag) {
		//
	}
}