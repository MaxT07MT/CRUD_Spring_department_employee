package spring_department.mvc.exception;

import net.sf.oval.ConstraintViolation;

import java.util.List;

public class DepartmentException extends RuntimeException{
    public List<ConstraintViolation> violations ;
    public DepartmentException(String s) {
        super(s);
    }

    public DepartmentException(List<ConstraintViolation> violations) {
    this.violations = violations;
    }
}
