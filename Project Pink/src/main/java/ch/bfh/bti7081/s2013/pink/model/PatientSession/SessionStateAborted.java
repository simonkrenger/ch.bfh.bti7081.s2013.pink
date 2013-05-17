package ch.bfh.bti7081.s2013.pink.model.PatientSession;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: sc0238
 * Date: 09.05.13
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public class SessionStateAborted extends EditableSessionState {
    @Override
    public SessionStateType getStateType() {
        return SessionStateType.Aborted;
    }

    @Override
    public LinkedList<SessionStateType> getPossibleNextStateType() {
        final LinkedList<SessionStateType> sessionStateTypes = new LinkedList<SessionStateType>();
		sessionStateTypes.add(SessionStateType.Planned);
        sessionStateTypes.add(SessionStateType.Finished);
        sessionStateTypes.add(SessionStateType.Started);

        return sessionStateTypes;
    }

    @Override
    public SessionStateType getDefaultNextState() {
		return SessionStateType.Planned;
    }

    @Override
    public String getName() {
        return "Aborted";
    }

    @Override
    public String getDescription() {
        return "Session has been prematurely be closed.";
    }
}
