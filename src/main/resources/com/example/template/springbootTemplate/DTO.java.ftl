package ${packageName}.dto;

public class ${currentEntity.name}DTO {

    <#list currentEntity.attributes as attribute>
    private ${attribute.type} ${attribute.name};

    </#list>

    // Constructors, getters, and setters

    <#list currentEntity.attributes as attribute>
    public ${attribute.type} get${attribute.name?cap_first}() {
        return ${attribute.name};
    }

    public void set${attribute.name?cap_first}(${attribute.type} ${attribute.name}) {
        this.${attribute.name} = ${attribute.name};
    }

    </#list>
}