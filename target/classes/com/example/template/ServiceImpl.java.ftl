package ${packageName}.serviceImpl;

import ${packageName}.service.${currentEntity.name}Service;
import ${packageName}.dto.${currentEntity.name}DTO;
import ${packageName}.model.${currentEntity.name};
import ${packageName}.converter.${currentEntity.name}Converter;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ${currentEntity.name}ServiceImpl implements ${currentEntity.name}Service {
    // TODO: Add repository dependency

    @Override
    public ${currentEntity.name}DTO create${currentEntity.name}(${currentEntity.name}DTO ${currentEntity.name?uncap_first}DTO) {
        // TODO: Implement method
        return null;
    }

    @Override
    public ${currentEntity.name}DTO get${currentEntity.name}ById(Long id) {
        // TODO: Implement method
        return null;
    }

    @Override
    public List<${currentEntity.name}DTO> getAll${currentEntity.name}s() {
        // TODO: Implement method
        return null;
    }

    @Override
    public ${currentEntity.name}DTO update${currentEntity.name}(Long id, ${currentEntity.name}DTO ${currentEntity.name?uncap_first}DTO) {
        // TODO: Implement method
        return null;
    }

    @Override
    public void delete${currentEntity.name}(Long id) {
        // TODO: Implement method
    }
}