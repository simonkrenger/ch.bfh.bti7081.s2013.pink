package ch.bfh.bti7081.s2013.pink.model.PatientSession;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: sc0238
 * Date: 09.05.13
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */
public class SessionStateStarted extends EditableSessionState {

    @Override
    public SessionStateType getStateType() {
        return SessionStateType.Started;
    }

    @Override
    public LinkedList<SessionStateType> getPossibleNextStateType() {
        final LinkedList<SessionStateType> sessionStateTypes = new LinkedList<SessionStateType>();
        sessionStateTypes.add(SessionStateType.Aborted);
        sessionStateTypes.add(SessionStateType.Finished);

        return sessionStateTypes;
    }

    @Override
    public SessionStateType getDefaultNextState() {
        return SessionStateType.Finished;
    }

    @Override
    public String getName() {
        return "In Progress";
    }

    @Override
    public String getDescription() {
        return "Session is being conducted.";
    }
}
