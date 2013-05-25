package ch.bfh.bti7081.s2013.pink.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * This is where all the persistence magic happens. It should provide a clean
 * and easy to use interface to load and save objects.
 * 
 * @author Christian Meyer <chrigu.meyer@gmail.com>
 */
public class HibernateDataSource {
	private static final HibernateDataSource INSTANCE = new HibernateDataSource();

	protected final SessionFactory sessionFactory;

	protected HibernateDataSource() {
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	/**
	 * If you need a special method to search for something specific, you might
	 * want to get started by copying this one.
	 * 
	 * @param clazz
	 * @return all entities of type clazz
	 */
	public <T> List<T> findAll(Class<T> clazz) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria crit = session.createCriteria(clazz);
		@SuppressWarnings("unchecked")
		List<T> result = crit.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	/**
	 * @param name
	 * @param firstName
	 * @return all patients that have the given name and/or first name.
	 *         <code>null</code> values are ignored, so you can search just for
	 *         a first name if you fancy.
	 */
	public List<Patient> findPatients(String name, String firstName) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria crit = session.createCriteria(Patient.class);
		if (name != null)
			crit.add(Restrictions.eq("name", name));
		if (firstName != null)
			crit.add(Restrictions.eq("firstName", firstName));
		@SuppressWarnings("unchecked")
		List<Patient> result = crit.list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	/**
	 * @param entity
	 * @return a new copy of entity that is persisted and therefore has its id
	 *         set (if generated) and should be used to add to other objects
	 *         instead of the original.
	 */
	@SuppressWarnings("unchecked")
	public <T> T saveOrUpdate(T entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		entity = (T) session.merge(entity);

		session.getTransaction().commit();
		session.close();
		return entity;
	}

	/**
	 * The {@link HibernateDataSource} is a singleton, so use this instance
	 * dispenser to get your persistin' goin'.
	 * 
	 * @return <strong>the</strong> {@link HibernateDataSource}
	 */
	public static HibernateDataSource getInstance() {
		return INSTANCE;
	}
}
