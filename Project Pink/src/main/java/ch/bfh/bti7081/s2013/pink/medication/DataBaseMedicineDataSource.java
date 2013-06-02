package ch.bfh.bti7081.s2013.pink.medication;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Medication;

/**
 * Database based dataSource for medicine.
 *
 * @author Christoph Seiler (christoph.seiler@gmail.com)
 */
public class DataBaseMedicineDataSource extends HibernateDataSource implements IMedicineDataSource {

    /**
     * Get all medicine in database.
     *
     * @return complete list of all medicine.
     */
    public List<Medication> GetAllMedicine()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Medication.class);
        @SuppressWarnings("unchecked")
        List<Medication> result = criteria.list();

        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Gets all medicine with a specific text in the name.
     *
     * @param text which has to be present in the name.
     * @return List of medicine matching the text.
     */
    public List<Medication> GetMedicineContainingText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text");
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Medication.class);
        criteria.add(Restrictions.ilike("name", text, MatchMode.ANYWHERE));

        @SuppressWarnings("unchecked")
        List<Medication> result = criteria.list();

        session.getTransaction().commit();
        session.close();
        return result;
    }
}
