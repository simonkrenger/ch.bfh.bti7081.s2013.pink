package ch.bfh.bti7081.s2013.pink.model.PatientSession;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ch.bfh.bti7081.s2013.pink.model.Doctor;
import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.Patient;

public class Session {
	private Patient patient;
	private Doctor doctor;

	private Date timeStart;
	private Date timeEnd;

	private SessionStateType sessionStateType = SessionStateType.Invalid;
	public ISessionState sessionState;

	private List<Note> notes = new LinkedList<Note>();

	public Session(Patient patient, Doctor doctor) {
		this.patient = patient;
		this.doctor = doctor;
	}

	public SessionStateType getSessionStateType() {
		return sessionStateType;
	}

    public void getSessionStateType(SessionStateType type) { sessionStateType = type; }

	public String getName() {
		return sessionState.getName();
	}

	public String getDescription() {
		return sessionState.getDescription();
	}

	public void addNote(Note n) {
		if (sessionState.isEditable()) {
			notes.add(n);
		} else {
			throw new RuntimeException("Note is not editable!");
		}
	}

	public Patient getPatient() {
		return patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

    public ISessionState getSessionState()
    {
        if (sessionState == null)
        {
            sessionState = resolveSessionStateType(sessionStateType);
        }
        return sessionState;
    }

    private ISessionState resolveSessionStateType(SessionStateType type)
    {
        switch (type)
        {
            case Aborted:   return new SessionStateAborted();
            case Archived:  return new SessionStateArchived();
            case Cancelled: return new SessionStateCancelled();
            case Finished:  return new SessionStateFinished();
            case Reopened:  return new SessionStateReopened();
            case Planned:    return new SessionStatePlanned();

            default:
            case Started:   return new SessionStateStarted();
        }
    }

	public void proceedToDefaultNextState() {
		sessionState = resolveSessionStateType(sessionState
				.getDefaultNextState());
	}

    public void changeState(SessionStateType type)
    {
        if (isTranslationPossible(type))
        {
            sessionState = resolveSessionStateType(type);
            sessionStateType = type;
        }
    }

    public boolean isTranslationPossible(SessionStateType type)
    {
        return getSessionState().getPossibleNextStateType().contains(type);
    }

    public boolean isEditable()
    {
        return getSessionState().isEditable();
    }
}
