package spring_department.mvc.repository;


import org.springframework.stereotype.Repository;
import spring_department.mvc.models.Department;

import java.util.List;
@Repository
public interface DepartmentRepository {
   List<Department> findAll();

    Department findById(int id);

    int create(Department department);

    int update(Department department);

    int delete(int id);
}
