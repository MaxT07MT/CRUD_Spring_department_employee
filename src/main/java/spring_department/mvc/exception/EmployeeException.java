package spring_department.mvc.exception;

import net.sf.oval.ConstraintViolation;

import java.util.List;

public class EmployeeException extends RuntimeException{
    public List<ConstraintViolation> violations ;
    public EmployeeException(String s) {
        super(s);
    }


    public EmployeeException(List<ConstraintViolation> violations) {
        this.violations = violations;
    }
}
