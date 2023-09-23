package uz.coding.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.coding.employee.dto.Department;
import uz.coding.employee.model.DepartmentModel;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE  )
public interface DepartmentMapper {
    @Mapping(target = "id", ignore = true )
    @Mapping(target = "location", source = "location")
    Department map(DepartmentModel departmentModel);

    @Mapping(target = "id", ignore = true )
    Department map(@MappingTarget Department department,
                 DepartmentModel departmentModel);

}
