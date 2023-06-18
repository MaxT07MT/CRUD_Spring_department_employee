package spring_department.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spring_department.mvc.services.EmployeeService;
//@Controller
//@RequestMapping("/employees")
public class ListEmployeeController {
    private final EmployeeService employeeService;
   // @Autowired
    public ListEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public String employeeList(Model model){
        model.addAttribute("employee", employeeService.getAllEmp());
        return "employees/empList";
    }
    @GetMapping("/{id}")
    public String showByIdEmp(
            @PathVariable("id") int id,
            Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employees/empShowById";
    }
}
