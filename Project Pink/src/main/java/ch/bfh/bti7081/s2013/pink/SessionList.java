package ch.bfh.bti7081.s2013.pink;

import ch.bfh.bti7081.s2013.pink.model.Session;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sc0238
 * Date: 29.05.13
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
public class SessionList extends VerticalLayout implements View
{
    public SessionList(List<Session> sessions)
    {
        setSizeFull();

        for (Session session : sessions)
        {
            addComponent(new PatientOverview(0, 200, session.getPatient(), session));
        }
    }

    /**
     * This view is navigated to.
     * <p/>
     * This method is always called before the view is shown on screen.
     * {@link com.vaadin.navigator.ViewChangeListener.ViewChangeEvent#getParameters() event.getParameters()} may contain
     * extra parameters relevant to the view.
     *
     * @param event ViewChangeEvent representing the view change that is
     *              occurring. {@link com.vaadin.navigator.ViewChangeListener.ViewChangeEvent#getNewView()
     *              event.getNewView()} returns <code>this</code>.
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
