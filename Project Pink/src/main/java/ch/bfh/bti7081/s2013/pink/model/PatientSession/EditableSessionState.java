package ch.bfh.bti7081.s2013.pink.model.PatientSession;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: sc0238
 * Date: 09.05.13
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public abstract class EditableSessionState implements ISessionState
{
    public abstract SessionStateType getStateType();

    public abstract LinkedList<SessionStateType> getPossibleNextStateType();

    public abstract SessionStateType getDefaultNextState();

    public abstract String getName();

    public abstract String getDescription();

    public boolean isEditable() {
        return true;
    }
}
