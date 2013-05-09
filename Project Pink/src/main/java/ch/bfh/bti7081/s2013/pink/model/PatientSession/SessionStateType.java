package ch.bfh.bti7081.s2013.pink.model.PatientSession;

/**
 * Created with IntelliJ IDEA.
 * User: sc0238
 * Date: 09.05.13
 * Time: 10:45
 * To change this template use File | Settings | File Templates.
 */
public enum SessionStateType
{
    /**
     * Invalid State.
     */
    Invalid,

    /**
     * Session is being planed.
     */
    Planed,

    /**
     * Session has started and is in progress.
     */
    Started,

    /**
     * Session has been aborted.
     */
    Aborted,

    /**
     * Session has been successfully finished.
     */
    Finished,

    /**
     * Session has been cancelled.
     */
    Cancelled,

    /**
     * Session has been archived.
     */
    Archived,

    /**
     * Session has been reopened for reediting.
     */
    Reopened
}
