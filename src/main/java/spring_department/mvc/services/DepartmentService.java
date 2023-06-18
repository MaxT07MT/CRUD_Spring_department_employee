package spring_department.mvc.services;


import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring_department.mvc.exception.DepartmentException;
import spring_department.mvc.models.Department;
import spring_department.mvc.repository.DepartmentRepository;
import spring_department.mvc.repository.DepartmentRepositoryJdbc;


import java.util.ArrayList;
import java.util.List;
//@Service
public class DepartmentService {



    private final DepartmentRepository departmentRepository;

    RepoFactory repoFactory;

    @Autowired
    public DepartmentService(RepoFactory repoFactory) {
        this.repoFactory=repoFactory;
        this.departmentRepository = repoFactory.getDepRepo();

    }

            public ArrayList<Department> getAll() {

                return (ArrayList<Department>) departmentRepository.findAll();
            }

            public Department getById(int id) {

                return departmentRepository.findById(id);
            }


            public int create(Department department) {
                List<ConstraintViolation> violations = new Validator().validate(department);
                if (!violations.isEmpty()) {
                    throw new DepartmentException(violations);
                }
                return departmentRepository.create(department);
            }

            private void validate(Department department) {
                if (StringUtils.isBlank(department.getName())) {
                    throw new DepartmentException("Department name has");
                }
                if (StringUtils.isBlank(department.getAddress())) {
                    throw new DepartmentException("Address has to be filled");
                }
                if (StringUtils.isBlank(department.getDescription())) {
                    throw new DepartmentException("Description has be filled");
                }
            }

            public void update(Department department) {
                List<ConstraintViolation> violations = new Validator().validate(department);
                if (!violations.isEmpty()) {
                    throw new DepartmentException(violations);
                }
               departmentRepository.update(department);
            }

            public void delete(int id) {
                Department department = getById(id);
                if (department == null) {

                    throw new DepartmentException("cannot find department with id " + id);
                }

                departmentRepository.delete(id);
            }



            }




