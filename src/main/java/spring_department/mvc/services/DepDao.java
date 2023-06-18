package spring_department.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spring_department.mvc.models.Department;

import java.util.List;
@Repository
public class DepDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Department> getAll() {
        return jdbcTemplate.query("SELECT * FROM enterprise.department", new BeanPropertyRowMapper<>(Department.class));
    }

    public Department getById(int id) {
        return jdbcTemplate.query("SELECT * FROM Department WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Department.class))
                .stream().findAny().orElse(null);
    }

    public void create(Department department) {
        jdbcTemplate.update("INSERT INTO enterprise.department VALUES(1, ?, ?, ?, ?, ?)", department.getName(), department.getPhone(),
                department.getEmail() , department.getAddress(), department.getDescription());
    }

    public void update(int id, Department department) {
        jdbcTemplate.update("UPDATE department SET name=?, phone=?, email=?, address=? , description=? WHERE id=?", department.getName(),
                department.getPhone(), department.getEmail(), department.getAddress(), department.getDescription(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM enterprise.department WHERE id=?", id);
    }
}
