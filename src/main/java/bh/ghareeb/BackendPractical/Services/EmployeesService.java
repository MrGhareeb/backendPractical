package bh.ghareeb.BackendPractical.Services;

import bh.ghareeb.BackendPractical.Components.ConfigComponent;
import bh.ghareeb.BackendPractical.Records.EmployeesRecord;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Service that handle the business logic related to all employees
 */
@Service
public class EmployeesService {
    private String jsonPath;


    private static final Logger logger = LoggerFactory.getLogger(EmployeesService.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<EmployeesRecord> employeesRecordList = new CopyOnWriteArrayList<>();

    public EmployeesService() {
        this.jsonPath = ConfigComponent.jsonDBPath;
        this.readJson();
    }

    /**
     * Read the json file based on the location provided in the properties file
     */
    private void readJson() {
        try {
            File file = new File(this.jsonPath);
            if (file.exists()) {

                this.employeesRecordList.addAll(
                        objectMapper.readValue(file, new TypeReference<List<EmployeesRecord>>() {
                        })
                );
            }
        } catch (Exception e) {
            logger.error("An error occurred: {}", e.getMessage(), e);
        }
    }

    /**
     * Get all the employees from the json file
     * @return List of all the employees
     */
    public List<EmployeesRecord> getEmployees() {
        return employeesRecordList;
    }

    public void setRecordList(EmployeesRecord newEmployeesRecord) {
        this.employeesRecordList.add(newEmployeesRecord);
    }

    /**
     * Retrieve the employee by the id
     * @param id the id of the employee
     * @return one records that match the id
     */
    public Optional<EmployeesRecord> getEmployees(Integer id) {
        return this.employeesRecordList.stream().filter(employeesRecord -> employeesRecord.id() == id).findFirst();
    }

    /**
     * retrieve the employees by name or range of salary or all the above
     * @param name the name of the employee (case exclusive)
     * @param fromSalary <b>Optional</b> the start range of the employee salary
     * @param toSalary <b>Optional</b> the end range of the employee salary
     * @return List of records for all the employees that match the details provided
     */
    public List<EmployeesRecord> getEmployees(String name, Optional<Double> fromSalary, Optional<Double> toSalary) {
        List<EmployeesRecord> result = List.of();

        try {
            result = this.employeesRecordList.stream()
                    //filter the data by name
                    .filter(employeesRecord ->
                            (name == null
                                    ||
                                    String.format("%s %s", employeesRecord.firstName(), employeesRecord.lastName())
                                            .toLowerCase()
                                            .contains(name.toLowerCase())


                            )
                    )
                    //filter the data by salary (start from)
                    .filter(employeesRecord ->
                            (fromSalary.isEmpty()
                                    ||
                                    fromSalary.get() <= employeesRecord.salary()
                            )
                    )
                    //filter the data by salary (end to)
                    .filter(employeesRecord ->
                            (toSalary.isEmpty()
                                    || toSalary.get() >= employeesRecord.salary()
                            ))

                    .collect(Collectors.toList());
        } catch (Exception e) {

        }

        if (result.isEmpty()) return null;

        return result;
    }


}
