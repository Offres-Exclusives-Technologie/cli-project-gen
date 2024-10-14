package ${packageName}.controller;

import ${packageName}.service.${currentEntity.name}Service;
import ${packageName}.dto.${currentEntity.name}DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/${currentEntity.name?lower_case}s")
public class ${currentEntity.name}Controller {

    private final ${currentEntity.name}Service ${currentEntity.name?uncap_first}Service;

    @Autowired
    public ${currentEntity.name}Controller(${currentEntity.name}Service ${currentEntity.name?uncap_first}Service) {
        this.${currentEntity.name?uncap_first}Service = ${currentEntity.name?uncap_first}Service;
    }

    @PostMapping
    public ResponseEntity<${currentEntity.name}DTO> create${currentEntity.name}(@RequestBody ${currentEntity.name}DTO ${currentEntity.name?uncap_first}DTO) {
        return new ResponseEntity<>(${currentEntity.name?uncap_first}Service.create${currentEntity.name}(${currentEntity.name?uncap_first}DTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<${currentEntity.name}DTO> get${currentEntity.name}(@PathVariable Long id) {
        return ResponseEntity.ok(${currentEntity.name?uncap_first}Service.get${currentEntity.name}ById(id));
    }

    @GetMapping
    public ResponseEntity<List<${currentEntity.name}DTO>> getAll${currentEntity.name}s() {
        return ResponseEntity.ok(${currentEntity.name?uncap_first}Service.getAll${currentEntity.name}s());
    }

    @PutMapping("/{id}")
    public ResponseEntity<${currentEntity.name}DTO> update${currentEntity.name}(@PathVariable Long id, @RequestBody ${currentEntity.name}DTO ${currentEntity.name?uncap_first}DTO) {
        return ResponseEntity.ok(${currentEntity.name?uncap_first}Service.update${currentEntity.name}(id, ${currentEntity.name?uncap_first}DTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete${currentEntity.name}(@PathVariable Long id) {
        ${currentEntity.name?uncap_first}Service.delete${currentEntity.name}(id);
        return ResponseEntity.noContent().build();
    }
}