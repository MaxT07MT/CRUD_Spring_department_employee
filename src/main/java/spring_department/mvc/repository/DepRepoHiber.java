package spring_department.mvc.repository;


import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring_department.mvc.models.Department;


import javax.sql.DataSource;
import java.util.List;

@Repository
public class DepRepoHiber implements DepartmentRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public DepRepoHiber(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    public List<Department> findAll() {
        List<Department> departments = (List<Department>) sessionFactory.openSession().createQuery("FROM Department").list();
        return departments;
    }


    public Department findById(int id) {
        return sessionFactory.getCurrentSession().get(Department.class, id);
    }

    public int create(Department department) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.save(department);
        tx1.commit();
        session.close();
        return 0;
    }

    public int update(Department department) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.update(department);
        tx1.commit();
        session.close();
        return 0;
    }

    public int delete(int department) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(department);
        tx1.commit();
        session.close();

        return department;
    }


}
