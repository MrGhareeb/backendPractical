package bh.ghareeb.BackendPractical.Controllers;

import bh.ghareeb.BackendPractical.Records.EmployeesRecord;
import bh.ghareeb.BackendPractical.Services.EmployeesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * API controller that handle retrieving and storing the employee details
 */
@RestController
@RequestMapping("/employees")
public class EmployeesController {

    EmployeesService employeesService = new EmployeesService();

    @GetMapping
    public List<EmployeesRecord> GetEmployees(@RequestParam(required = false) String name, @RequestParam(required = false) Double fromSalary, @RequestParam(required = false) Double toSalary) {
        return employeesService.getEmployees(name, Optional.ofNullable(fromSalary), Optional.ofNullable(toSalary));
    }

    @GetMapping("/{id}")
    public Optional<EmployeesRecord> GetEmployees(@PathVariable Integer id) {
        return employeesService.getEmployees(id);
    }


}
