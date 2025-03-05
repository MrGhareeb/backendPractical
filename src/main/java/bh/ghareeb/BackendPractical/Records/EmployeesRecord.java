package bh.ghareeb.BackendPractical.Records;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Positive;

/**
 * Represent one record of an employee <br>
 * <b>Note:</b> The records contain json property mapping which matches with the fields names
 * @param id Auto generated identifier
 * @param firstName the first name of employee <b>(Required Field)</b>
 * @param lastName the last name of the employee <b>(Required Field)</b>
 * @param dateOfBirth the date birth of the employee
 * @param salary The salary is a decimal number must be a positive number
 * @param joinDate The join date of the employee
 * @param department the department the employee belong to
 */
public record EmployeesRecord(
        @JsonProperty("id")
//        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id,
        @JsonProperty("firstName")
//        @NotBlank
        String firstName,
        @JsonProperty("lastName")
        String lastName,
        @JsonProperty("dateOfBirth")
        Date dateOfBirth,
//        @Positive
        @JsonProperty("salary")
        Double salary,
        @JsonProperty("joinDate")
        Date joinDate,
        @JsonProperty("department")
        String department
) {

}
