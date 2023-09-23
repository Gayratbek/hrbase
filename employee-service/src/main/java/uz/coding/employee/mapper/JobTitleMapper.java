package uz.coding.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.coding.employee.dto.Department;
import uz.coding.employee.dto.Employee;
import uz.coding.employee.dto.JobTitle;
import uz.coding.employee.dto.Person;
import uz.coding.employee.model.EmployeeModel;
import uz.coding.employee.model.JobTitleModel;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE  )
public interface JobTitleMapper {

    @Mapping(target = "id", ignore = true )
    JobTitle map(JobTitleModel jobTitleModel);

    @Mapping(target = "id", ignore = true )
    JobTitle map(@MappingTarget JobTitle jobTitle, JobTitleModel jobTitleModel);


    @Mapping(target = "id", ignore = true )
    @Mapping(target = "code", source = "jobTitleModel.code")
    @Mapping(target = "name", source = "jobTitleModel.name")
    @Mapping(target = "description", source = "jobTitleModel.description")
    @Mapping(target = "goals", source = "jobTitleModel.goals")
    @Mapping(target = "location", source = "jobTitleModel.location")
    @Mapping(target = "department", source = "department")
    @Mapping(target = "manager", source = "employee")
    JobTitle map(JobTitleModel jobTitleModel,
                 Department department, Employee employee );

//    @Mapping(target = "id", ignore = true )
//    @Mapping(target = "department", source = "departmentDto")
//    @Mapping(target = "manager", source = "employeeDto")
//    JobTitle map(JobTitleModel jobTitleModel, Department departmentDto, Employee employeeDto);




}
