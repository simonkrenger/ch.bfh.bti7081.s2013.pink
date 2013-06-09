package ch.bfh.bti7081.s2013.pink.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is where all the persistence magic happens. It should provide a clean
 * and easy to use interface to load and save objects.
 * 
 * @author Christian Meyer <chrigu.meyer@gmail.com>
 */
public class HibernateDataSource {
	private static final HibernateDataSource INSTANCE = new HibernateDataSource();
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	protected final SessionFactory sessionFactory;

	private File propFile = new File(System.getProperty("user.home")
			+ File.separator + "pink.properties");

	protected HibernateDataSource() {
		Configuration configuration = getConfiguration();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	Configuration getConfiguration() {

		Configuration conf = new Configuration();
		conf.configure();

		Properties prop = getProperties();
		FileReader fr = null;
		try {
			fr = new FileReader(propFile);
			prop.load(fr);
		} catch (IOException e) {
			LOG.warn("Error loading properties: " + e.getLocalizedMessage(), e);
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				LOG.warn(
						"Error closing the FileReader for properties: "
								+ e.getLocalizedMessage(), e);
			}
		}

		String driverClass = prop.getProperty("db.driver");
		String url = prop.getProperty("db.url");
		String user = prop.getProperty("db.username");
		String pass = prop.getProperty("db.password");
		String dialect = prop.getProperty("hibernate.dialect");

		conf.setProperty("hibernate.connection.driver_class", driverClass);
		conf.setProperty("hibernate.connection.url", url);
		conf.setProperty("hibernate.connection.username", user);
		conf.setProperty("hibernate.connection.password", pass);
		if (dialect != null)
			conf.setProperty("hibernate.dialect", dialect);

		FileWriter fw = null;
		try {
			fw = new FileWriter(propFile);
			prop.store(fw, null);

			LOG.info("Saved properties to " + propFile.getPath());
		} catch (IOException e) {
			throw new RuntimeException("Error saving properties: "
					+ e.getLocalizedMessage(), e);
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				LOG.warn(
						"Error closing the FileWriter for properties: "
								+ e.getLocalizedMessage(), e);
			}
		}
		return conf;
	}

	private Properties getProperties() {
		Properties prop = new Properties();

		prop.setProperty("db.driver", "org.h2.Driver");
		prop.setProperty("db.url", "jdbc:h2:~/pinkDB");
		prop.setProperty("db.username", "user");
		prop.setProperty("db.password", "password");

		return prop;
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

	public List<ch.bfh.bti7081.s2013.pink.model.Session> getSessionsByName(
			String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria crit = session
				.createCriteria(ch.bfh.bti7081.s2013.pink.model.Session.class);
		if (name != null) {
			crit = crit.createCriteria("patient").add(
					Restrictions.or(Restrictions.ilike("name", name,
							MatchMode.ANYWHERE), Restrictions.ilike(
							"firstName", name, MatchMode.ANYWHERE)));

		}
		@SuppressWarnings("unchecked")
		List<ch.bfh.bti7081.s2013.pink.model.Session> result = crit.list();

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

	public <T> T reload(T entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.refresh(entity);
		
		session.getTransaction().commit();
		session.close();
		return entity;
	}

	public void remove(Object entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.delete(entity);

		session.getTransaction().commit();
		session.close();
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
