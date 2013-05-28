package ch.bfh.bti7081.s2013.pink.model.PatientSession;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: sc0238
 * Date: 09.05.13
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
public class SessionStatePlanned extends EditableSessionState {
    @Override
    public SessionStateType getStateType() {
        return SessionStateType.Planned;
    }

    @Override
    public LinkedList<SessionStateType> getPossibleNextStateType() {
        final LinkedList<SessionStateType> sessionStateTypes = new LinkedList<SessionStateType>();
        sessionStateTypes.add(SessionStateType.Cancelled);
        sessionStateTypes.add(SessionStateType.Planned);
        sessionStateTypes.add(SessionStateType.Started);

        return sessionStateTypes;
    }

    @Override
    public SessionStateType getDefaultNextState() {
        return SessionStateType.Started;
    }

    @Override
    public String getName() {
        return "Planed";
    }

    @Override
    public String getDescription() {
        return "Session has been scheduled.";
    }
}
