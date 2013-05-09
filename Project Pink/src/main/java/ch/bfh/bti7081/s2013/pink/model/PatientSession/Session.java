package ch.bfh.bti7081.s2013.pink.model.PatientSession;

import ch.bfh.bti7081.s2013.pink.model.Doctor;
import ch.bfh.bti7081.s2013.pink.model.Note;
import ch.bfh.bti7081.s2013.pink.model.Patient;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Session {
	private Patient patient;
	private Doctor doctors;

	private Date timeStart;
	private Date timeEnd;

	private SessionStateType sessionStateType = SessionStateType.Invalid;
    private ISessionState sessionState;

	private List<Note> notes = new LinkedList<Note>();

    public SessionStateType getSessionStateType() { return sessionStateType; }

    public void getSessionStateType(SessionStateType type) { sessionStateType = type; }

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
            case Planed:    return new SessionStatePlaned();

            default:
            case Started:   return new SessionStateStarted();
        }
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
