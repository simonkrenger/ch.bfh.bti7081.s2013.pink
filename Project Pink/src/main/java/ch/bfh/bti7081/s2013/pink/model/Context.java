package ch.bfh.bti7081.s2013.pink.model;

/**
 * Application Context. The context is implemented as a Singleton (pattern).
 * 
 * @author Christoph Seiler (christoph.seiler@gmail.com)
 */
public class Context {
	private static Context context = null;

	private ThreadLocal<Doctor> doctor = new ThreadLocal<Doctor>();

	/**
	 * Private constructor for singleton pattern.
	 * 
	 * @author Christoph Seiler (christoph.seiler@gmail.com)
	 */
	private Context() {
		// TODO: Login - static context won't work then, must be thread local
		// doctor =
		// HibernateDataSource.getInstance().findAll(Doctor.class).get(0);
	}

	/**
	 * Gets the current Context for this application.
	 * 
	 * @return current Application Context.
	 */
	public static Context getCurrent() {
		if (context == null) {
			context = new Context();
		}

		return context;
	}

	/**
	 * @return the currently logged in {@link Doctor}
	 */
	public Doctor getDoctor() {
		return doctor.get();
	}

	public void setDoctor(Doctor doctor) {
		this.doctor.set(doctor);
	}
}
