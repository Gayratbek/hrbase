package uz.coding.document.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.coding.document.dto.PersonDocument;
import uz.coding.document.model.PersonDocumentRequest;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE)
public interface PersonDocumentMapper {

    @Mapping(target = "id", ignore = true )
    @Mapping(target = "personId", source = "personId")
    @Mapping(target = "date", source = "documentRequest.date" )
    PersonDocument map(PersonDocumentRequest documentRequest);

    @Mapping(target = "id", ignore = true )
    PersonDocument map(@MappingTarget PersonDocument personDocument, PersonDocumentRequest documentRequest);


}
