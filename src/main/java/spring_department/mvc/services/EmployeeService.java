package spring_department.mvc.services;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring_department.mvc.exception.EmployeeException;
import spring_department.mvc.models.Employee;
import spring_department.mvc.repository.EmployeeRepository;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

//@Service
public class EmployeeService {

    public EmployeeRepository employeeRepository;
    RepoFactory repoFactory;


    public EmployeeService(RepoFactory repoFactory) {
        this.repoFactory=repoFactory;
        this.employeeRepository = repoFactory.getEmpRepo();
    }

    public ArrayList<Employee> getAll() {
        return (ArrayList<Employee>) employeeRepository.getAll();
    }
    public ArrayList<Employee> getAllEmp() {
        return (ArrayList<Employee>) employeeRepository.getAllEmp();
    }
    public ArrayList<Employee> getByDepId(int depId) {
        return (ArrayList<Employee>) employeeRepository.findByDepId(depId);
    }


    public Employee getById(int id) {
        return employeeRepository.findById(id);
    }

    public int create(Employee employee) {
        List<ConstraintViolation> violations = new Validator().validate(employee);
        if (!violations.isEmpty()) {
            throw new EmployeeException(violations);
        }
        return employeeRepository.create(employee);
    }

    public int update(int id, Employee employee) {
        if (!employeeRepository.exist(id)) {
            throw new EntityNotFoundException("employee id " + id +"do not found");
        }

        List<ConstraintViolation> violations = new Validator().validate(employee);
        if (!violations.isEmpty()) {
            throw new EmployeeException(violations);
        }
        employee.setId(id);
        return employeeRepository.update(employee);


    }

    public void delete(int id) {
     if (!employeeRepository.exist(id)){
            throw new EmployeeException("cannot find employee with id " + id);
        }
        employeeRepository.delete(id);
    }

}
