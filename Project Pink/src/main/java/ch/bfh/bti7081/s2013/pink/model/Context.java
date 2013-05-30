package ch.bfh.bti7081.s2013.pink.model;

/**
 * Application Context. The context is implemented as a Singleton (pattern).
 * 
 * @author Christoph Seiler (christoph.seiler@gmail.com)
 * 
 */
public class Context {
	private static Context context;

	private Doctor doctor;

	/**
	 * Private constructor for singleton pattern.
	 * 
	 * @author Christoph Seiler (christoph.seiler@gmail.com)
	 */
	private Context() {

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
	 * Gets the current {@link Doctor} logged in.
	 * 
	 * @return current Doctor.
	 */
	public Doctor getDoctor() {
		// TODO: Return this.doctor
		return new Doctor("Hansi", "Hinterseher", null);
	}
}
