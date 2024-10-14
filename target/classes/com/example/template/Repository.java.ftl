package ${packageName}.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ${packageName}.model.${currentEntity.name};
import java.util.List;

public interface ${currentEntity.name}Repository extends JpaRepository<${currentEntity.name}, Long> {
    
}