package spring_department.mvc.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring_department.mvc.repository.*;
import spring_department.mvc.utils.GlobalProperties;
//@Component
public class RepoFactory {

    public static final String JDBC_REPO_DRIVER = "JDBC";
    static String PROP_KEY_REPO_DRIVER = "repo.driver";
@Autowired
    public RepoFactory(DepRepoHiber depRepoHiber) {
        this.depRepoHiber = depRepoHiber;
    }



    DepRepoHiber depRepoHiber;
    public  DepartmentRepository getDepRepo() {
        return (JDBC_REPO_DRIVER.equals(GlobalProperties.getProperties().getProperty(PROP_KEY_REPO_DRIVER)))? new DepartmentRepositoryJdbc(): depRepoHiber;

    }
    EmpRepoHiber empRepoHiber;
    public  EmployeeRepository getEmpRepo() {
        return (JDBC_REPO_DRIVER.equals(GlobalProperties.getProperties().getProperty(PROP_KEY_REPO_DRIVER)))? new EmployeeRepositoryJdbs(): empRepoHiber;
    }
}
