package uz.coding.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.coding.employee.dto.Person;
import uz.coding.employee.model.PersonModal;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE)
public interface PersonMapper {

    @Mapping(target = "id", ignore = true )
    @Mapping(target = "fio", source = "personModal.fio" )
    Person map(PersonModal personModal);

    @Mapping(target = "id", ignore = true )
    Person map(@MappingTarget Person person, PersonModal personModal);


}

