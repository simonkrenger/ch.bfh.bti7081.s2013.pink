package ch.bfh.bti7081.s2013.pink.model.PatientSession;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: sc0238
 * Date: 09.05.13
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
public class SessionStateArchived extends LockedSessionState {
    @Override
    public SessionStateType getStateType() {
        return SessionStateType.Archived;
    }

    @Override
    public LinkedList<SessionStateType> getPossibleNextStateType() {
        final LinkedList<SessionStateType> sessionStateTypes = new LinkedList<SessionStateType>();
        sessionStateTypes.add(SessionStateType.Reopened);

        return sessionStateTypes;
    }

    @Override
    public SessionStateType getDefaultNextState() {
        return SessionStateType.Reopened;
    }

    @Override
    public String getName() {
        return "Archived";
    }

    @Override
    public String getDescription() {
        return "Session has been archived for storage";
    }
}
