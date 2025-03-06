package bh.ghareeb.BackendPractical.Controllers;

import bh.ghareeb.BackendPractical.Records.EmployeesRecord;
import bh.ghareeb.BackendPractical.Services.EmployeesService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * API controller that handle retrieving and storing the employee details
 */
@RestController
@RequestMapping("/employees")
public class EmployeesController {

    EmployeesService employeesService = new EmployeesService();

    /**
     * Get all the employees in the database <b>Json file</b>
     * @param name Filter the returned data based on the name <b>Optional</b>
     * @param fromSalary start range of the employee salary <b>Optional</b>
     * @param toSalary  end range of the employee salary <b>Optinal</b>
     * @return a list of the employees that matches the filter or all the employees
     */
    @GetMapping
    public List<EmployeesRecord> GetEmployees(@RequestParam(required = false) String name, @RequestParam(required = false) Double fromSalary, @RequestParam(required = false) Double toSalary) {
        return employeesService.getEmployees(name, Optional.ofNullable(fromSalary), Optional.ofNullable(toSalary));
    }

    /**
     * Get the employee by id
     * @param id the id of the employee
     * @return return one record of the employee that match the id proivded
     */
    @GetMapping("/{id}")
    public Optional<EmployeesRecord> GetEmployees(@PathVariable Integer id) {
        return employeesService.getEmployees(id);
    }

    /**
     * Add new employee to the database
     * @param newEmployeesRecord Object that match the employee record property <br> <b>Note:</b> the passed ID will be ignored
     * @return The generated id of the employee
     */
    @PostMapping
    public Map<String, Integer> SetEmployees(@RequestBody EmployeesRecord newEmployeesRecord) {
        Integer generatedId = this.employeesService.setRecordList(newEmployeesRecord);
        Map<String, Integer> response = new HashMap<>();
        response.put("id",generatedId);
        return response;
    }


}
