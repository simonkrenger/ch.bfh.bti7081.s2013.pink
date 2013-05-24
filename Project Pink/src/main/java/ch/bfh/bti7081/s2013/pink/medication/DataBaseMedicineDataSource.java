package ch.bfh.bti7081.s2013.pink.medication;

import ch.bfh.bti7081.s2013.pink.model.HibernateDataSource;
import ch.bfh.bti7081.s2013.pink.model.Medicine;
import ch.bfh.bti7081.s2013.pink.model.Patient;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Database based dataSource for medicine.
 *
 * @author Christoph Seiler (christoph.seiler@gmail.com)
 */
public class DataBaseMedicineDataSource extends HibernateDataSource implements IMedicineDataSource {

    /**
     * Get all medicine in database.
     *
     * @author Christoph Seiler (christoph.seiler@gmail.com)
     * @return complete list of all medicine.
     */
    public List<Medicine> GetAllMedicine()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Medicine.class);
        @SuppressWarnings("unchecked")
        List<Medicine> result = criteria.list();

        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Gets all medicine with a specific text in the name.
     *
     * @author Christoph Seiler (christoph.seiler@gmail.com)
     * @param text which has to be present in the name.
     * @return List of medicine matching the text.
     */
    public List<Medicine> GetMedicineContainingText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text");
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Medicine.class);
        criteria.add(Restrictions.ilike("name", text, MatchMode.ANYWHERE));

        @SuppressWarnings("unchecked")
        List<Medicine> result = criteria.list();

        session.getTransaction().commit();
        session.close();
        return result;
    }
}
