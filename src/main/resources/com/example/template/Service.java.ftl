package ${packageName}.service;

import ${packageName}.dto.${currentEntity.name}DTO;
import java.util.List;

public interface ${currentEntity.name}Service {
    ${currentEntity.name}DTO create${currentEntity.name}(${currentEntity.name}DTO ${currentEntity.name?uncap_first}DTO);
    ${currentEntity.name}DTO get${currentEntity.name}ById(Long id);
    List<${currentEntity.name}DTO> getAll${currentEntity.name}s();
    ${currentEntity.name}DTO update${currentEntity.name}(Long id, ${currentEntity.name}DTO ${currentEntity.name?uncap_first}DTO);
    void delete${currentEntity.name}(Long id);
}