package spring_department.mvc.repository;



import org.springframework.stereotype.Repository;
import spring_department.mvc.models.Employee;

import java.util.List;
//@Repository
public interface EmployeeRepository {
    List<Employee> getAll();
    Employee findById(int id);
    List<Employee> findByDepId(int depId);
    int create(Employee employee);
    int update(Employee employee);
    int delete(int id);

   boolean exist(int id);

   List<Employee> getAllEmp();
}
