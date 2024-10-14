package ${packageName}.converter;

import ${packageName}.dto.${currentEntity.name}DTO;
import ${packageName}.model.${currentEntity.name};
import org.springframework.stereotype.Component;

@Component
public class ${currentEntity.name}Converter {

    public ${currentEntity.name}DTO toDTO(${currentEntity.name} entity) {
        if (entity == null) {
            return null;
        }

        ${currentEntity.name}DTO dto = new ${currentEntity.name}DTO();
        <#list currentEntity.attributes as attribute>
        dto.set${attribute.name?cap_first}(entity.get${attribute.name?cap_first}());
        </#list>

        return dto;
    }

    public ${currentEntity.name} toEntity(${currentEntity.name}DTO dto) {
        if (dto == null) {
            return null;
        }

        ${currentEntity.name} entity = new ${currentEntity.name}();
        <#list currentEntity.attributes as attribute>
        entity.set${attribute.name?cap_first}(dto.get${attribute.name?cap_first}());
        </#list>

        return entity;
    }
}