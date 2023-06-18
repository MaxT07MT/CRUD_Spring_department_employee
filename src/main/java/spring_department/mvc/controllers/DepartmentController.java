package spring_department.mvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import spring_department.mvc.exception.DepartmentException;
import spring_department.mvc.models.Department;
import spring_department.mvc.services.DepDao;
import spring_department.mvc.services.DepartmentService;

import java.nio.file.attribute.AttributeView;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepDao depDao;

    @Autowired
    public DepartmentController(DepDao depDao) {
        this.depDao = depDao;
    }

    @GetMapping()
    public String allDep(Model model) {
        model.addAttribute("departments", depDao.getAll());
        return "departments/depAll";
    }

    @GetMapping("/{id}")
    public String showByIdDep(@PathVariable("id") int id, Model model) {
        model.addAttribute("department", depDao.getById(id));
        return "departments/depShowById";
    }

    @GetMapping("/newDep")
    public String newDepartment(@ModelAttribute("department") Department department) {

        return "departments/depCreate";
    }


    @PostMapping()
    public String createDep(@ModelAttribute("department") @Valid Department department,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "departments/depCreate";
        try {
            depDao.create(department);
            return "redirect:/departments";
        } catch (DepartmentException error) {
            model.addAttribute("errorMsg", error.getMessage());
            return "departments/depCreate";
        }


    }

    @GetMapping("/{id}/editDep")
    public String editDep(Model model, @PathVariable("id") int id) {
        model.addAttribute("department", depDao.getById(id));
        return "departments/depEdit";
    }

    @PatchMapping("/{id}")
    public String updateDep(Model model, @ModelAttribute("department") @Valid Department department, BindingResult bindingResult,
                            @PathVariable("id") int id ) {
        if (bindingResult.hasErrors())
            return "departments/depEdit";
        try {
            depDao.update(id , department);
            return "redirect:/departments/{id}";
        }catch (DepartmentException error){
            model.addAttribute("department", department);
            model.addAttribute("error", error.violations);
            model.addAttribute("errorMsg", error.getMessage());
            return "departments/depEdit";
        }

    }

    @GetMapping("/{id}/deleteDep")
    public String doDeleteDep(Model model, @PathVariable("id") int id) {
        model.addAttribute("department", depDao.getById(id));
        return "departments/depDelete";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteDep(@PathVariable("id") int id) {
        depDao.delete(id);
        return "redirect:/departments";
    }
    @PostMapping("/{id}/del")
    public String delDep(@PathVariable("id") int id){
       return deleteDep(id);

    }

}