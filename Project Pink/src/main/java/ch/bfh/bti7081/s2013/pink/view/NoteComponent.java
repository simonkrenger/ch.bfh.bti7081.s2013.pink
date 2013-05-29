package ch.bfh.bti7081.s2013.pink.view;

import ch.bfh.bti7081.s2013.pink.client.NoteComponentState;
import ch.bfh.bti7081.s2013.pink.model.Note;

import com.vaadin.ui.AbstractComponent;

public class NoteComponent extends AbstractComponent {
	private static final long serialVersionUID = -7260300247218418563L;

	// private DigitalClockServerRpc rpc = new DigitalClockServerRpc() {
	// private GregorianCalendar cal;
	//
	// public void getServerTime() {
	// // update shared state
	// cal = new GregorianCalendar();
	// getState().time = cal.getTimeInMillis();
	// }
	//
	// };

	public NoteComponent(Note note) {
		// registerRpc(rpc);
		// rpc.getServerTime();
		// TODO Auto-generated constructor stub
	}

	@Override
	public NoteComponentState getState() {
		return (NoteComponentState) super.getState();
	}
}
