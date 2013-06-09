package ch.bfh.bti7081.s2013.pink.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.vaadin.event.FieldEvents;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;

/**
 * View class that allows to search for a patient.
 * 
 * @author Marco Berger <lostchall@gmail.com>
 * 
 */
public class PatientSearchView extends CustomComponent {
	private static final long serialVersionUID = -4461318034316573467L;

	private List<ChangeListener> searchListener = new ArrayList<ChangeListener>();
	private TextField searchBox;
	private String searchValue;
	private String lastInput;

	/**
	 * Gets the current Search Value.
	 * 
	 * @return Search Text Value.
	 */
	public String getSearchValue() {
		return lastInput;
	}

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public PatientSearchView() {
		// Create the search field
		searchBox = new TextField();
		searchBox.setInputPrompt("Search");
		searchBox.setWidth("100%");
		searchBox.setTextChangeTimeout(300);
		searchBox
				.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.TIMEOUT);

		searchBox.addTextChangeListener(new FieldEvents.TextChangeListener() {
			private static final long serialVersionUID = -4257491747644045071L;

			@Override
			public void textChange(FieldEvents.TextChangeEvent event) {
				lastInput = event.getText();
				patientSearch();
			}
		});

		setCompositionRoot(searchBox);
	}

	private void patientSearch() {
		if (!lastInput.equalsIgnoreCase(searchValue)) {
			ChangeEvent event = new ChangeEvent(lastInput);
			for (ChangeListener eventListener : searchListener) {
				eventListener.stateChanged(event);
			}
			searchValue = lastInput;
		}
	}

	/**
	 * Listener to allow getting a new Search Input.
	 * 
	 * @param e
	 *            as a EventListener.
	 */
	public void addSearchListener(ChangeListener e) {
		this.searchListener.add(e);
	}
}