package ch.bfh.bti7081.s2013.pink.model.PatientSession;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: sc0238
 * Date: 09.05.13
 * Time: 10:49
 * To change this template use File | Settings | File Templates.
 */
public interface ISessionState
{
    /**
     * Gets the current state type.
     * @return SessionStateType to identify.
     */
    public SessionStateType getStateType();

    /**
     * Gets the next possible session states.
     * @return List of SessionStateTypes.
     */
    public LinkedList<SessionStateType> getPossibleNextStateType();

    /**
     * Gets the next default session state.
     * @return SessionState suggestion.
     */
    public SessionStateType getDefaultNextState();

    /**
     * Gets the DisplayName of the Session.
     * @return Name of the Session Type.
     */
    public String getName();

    /**
     * Gets the Description of the Session.
     * @return Description of the State.
     */
    public String getDescription();

    /**
     * Gets a Flag if the Session is editable.
     * @return Flag is Session allows editing.
     */
    public boolean isEditable();
}
