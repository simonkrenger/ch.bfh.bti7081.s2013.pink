package ch.bfh.bti7081.s2013.pink.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

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

	@SuppressWarnings("unchecked")
	public <T> T saveOrUpdate(T entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		entity = (T) session.merge(entity);

		session.getTransaction().commit();
		session.close();
		return entity;
	}

	public static HibernateDataSource getInstance() {
		return INSTANCE;
	}
}
