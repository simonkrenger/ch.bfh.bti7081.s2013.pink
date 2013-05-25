package ch.bfh.bti7081.s2013.pink.model;

/**
 * Application Context.
 *
 * @author Christoph Seiler (christoph.seiler@gmail.com)
 * Date: 22.05.13
 * Time: 19:14
 */
public class Context
{
    private static Context context;

    private Doctor doctor;

    /**
     *  Private constructor for singleton pattern.
     *
     * @author Christoph Seiler (christoph.seiler@gmail.com)
     */
    private Context()
    {

    }

    /**
     * Gets the current Context for this Application.
     *
     * @author Christoph Seiler (christoph.seiler@gmail.com)
     * @return current Application Context.
     */
    public static Context getCurrent()
    {
        if (context == null)
        {
            context = new Context();
        }

        return context;
    }

    /**
     * Gets the current loggedIn Doctor.
     *
     * @author Christoph Seiler (christoph.seiler@gmail.com)
     * @return current Doctor.
     */
    public Doctor getDoctor()
    {
		return new Doctor("Hansi", "Hinterseher", null);
    }
}
