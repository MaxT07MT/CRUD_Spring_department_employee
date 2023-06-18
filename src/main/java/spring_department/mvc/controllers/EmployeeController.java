package spring_department.mvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import spring_department.mvc.exception.EmployeeException;
import spring_department.mvc.models.Employee;
import spring_department.mvc.services.EmployeeService;

//@Controller
//@RequestMapping("/departments/{depId}/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String allEmpl(Model model) {
        model.addAttribute("employee", employeeService.getAll());
        return "employees/empAll";
    }


    @GetMapping("/showEmp")
    public String allEmpByDepId(@PathVariable("depId") int depId, Model model) {
        model.addAttribute("employee", employeeService.getByDepId(depId));
        return "employees/empAll";
    }


    @GetMapping("/{id}")
    public String showByIdEmp(
            @PathVariable("depId") int depId,
            @PathVariable("id") int id,
            Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employees/empShowById";
    }


    @GetMapping("/newEmp")
    public String newEmployee(@ModelAttribute("employee") Employee employee) {
        return "employees/empCreate";
    }

    @PostMapping("/createEmp")
    public String createEmp(@PathVariable("depId") int depId, @ModelAttribute("employee") @Valid Employee employee,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "employees/empCreate";
       try {
           employeeService.create(employee);
           return "redirect:/departments/{depId}/employees/showEmp";
       }catch (EmployeeException error){
           return "employees/empCreate";
       }
    }

    @GetMapping("/{id}/editEmp")
    public String editEmp(
            @PathVariable("depId") int depId,
            @PathVariable("id") int id,
            Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employees/empEdit";
    }

    @PatchMapping("/{id}")
    public String updateEmp(@ModelAttribute("employee") @Valid Employee employee,
                            BindingResult bindingResult,
                            @PathVariable("id") int id,
                            @PathVariable("depId") int depId) {
        if (bindingResult.hasErrors())
            return "employees/empEdit";
        try {
            employeeService.update(id, employee);
            return "redirect:/departments/{depId}/employees/{id}";
        }catch (EmployeeException error){
            return "employees/empEdit";
        }
    }

    @GetMapping("/{id}/delEmp")
    public String delete(Model model,
            @PathVariable("id") int id){
        model.addAttribute("employee", employeeService.getById(id));
        return "employees/empDelete";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteEmp(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/departments/{depId}/employees/showEmp";
    }
    @PostMapping("/{id}/del")
    public String delEmp(@PathVariable("id") int id){
        return deleteEmp(id);
    }
}
