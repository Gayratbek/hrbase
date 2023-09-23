package uz.coding.employee.mapper;

import org.mapstruct.*;
import uz.coding.employee.dto.Department;
import uz.coding.employee.dto.Employee;
import uz.coding.employee.dto.JobTitle;
import uz.coding.employee.dto.Person;
import uz.coding.employee.model.EmployeeModel;
import uz.coding.employee.model.PersonModal;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE  )
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true )
    @Mapping(target = "fullname", source = "person.fio")
    @Mapping(target = "person", source = "person")
    Employee map(EmployeeModel employeeModel, Person person);

    @Mapping(target = "id", ignore = true )
    @Mapping(target = "jobTitle", source = "jobTitle")
    @Mapping(target = "department", source = "department")
    @Mapping(target = "person", source = "person")
    Employee map(@MappingTarget Employee employee,
                 EmployeeModel employeeModel,
                 JobTitle jobTitle,
                 Department department,
                 Person person);

    @Mapping(target = "id", ignore = true )
    Employee map(EmployeeModel employeeModel);


}
