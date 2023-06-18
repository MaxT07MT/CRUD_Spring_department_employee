package spring_department.mvc.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import spring_department.mvc.models.Employee;


import java.util.List;

public class EmpRepoHiber implements EmployeeRepository {
    
    SessionFactory sessionFactory;
    @Autowired
    public EmpRepoHiber(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = (List<Employee>) sessionFactory
                .getCurrentSession().createQuery("SELECT e FROM Employee e")
                .list();
        return employees;
    }


    public Employee findById(int id) {
        return sessionFactory.getCurrentSession().get(Employee.class, id);
    }

    public List<Employee> findByDepId(int depId) {
        return (List<Employee>) sessionFactory.getCurrentSession()
                .createQuery("SELECT  FROM  Employee  WHERE e.depId = :depId")
                .setParameter("depId", depId)
                .list();
    }


    public int create(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.save(employee);
        tx1.commit();
        session.close();
        return 0;
    }

    public int delete(int employee) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(employee);
        tx1.commit();
        session.close();
        return employee;
    }

    @Override
    public boolean exist(int id) {
        return true;
    }

    @Override
    public List<Employee> getAllEmp() {
        return null;
    }

    public int update(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(employee);
        tx1.commit();
        session.close();
        return 0;
    }


}
