package ch.bfh.bti7081.s2013.pink.model.PatientSession;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: sc0238
 * Date: 09.05.13
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
public class SessionStateCancelled extends EditableSessionState
{

    @Override
    public SessionStateType getStateType() {
        return SessionStateType.Cancelled;
    }

    @Override
    public LinkedList<SessionStateType> getPossibleNextStateType()
    {
        final LinkedList<SessionStateType> sessionStateTypes = new LinkedList<SessionStateType>();
        sessionStateTypes.add(SessionStateType.Archived);

        return sessionStateTypes;
    }

    @Override
    public SessionStateType getDefaultNextState() {
        return SessionStateType.Archived;
    }

    @Override
    public String getName() {
        return "Cancelled";
    }

    @Override
    public String getDescription() {
        return "Session has been cancelled by Doctor or Patient";
    }
}
