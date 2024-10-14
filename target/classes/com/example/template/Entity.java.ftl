package ${packageName}.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "${currentEntity.name?lower_case}s")
public class ${currentEntity.name} {

    <#list currentEntity.attributes as attribute>
    @<#if attribute.name == "id">Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)<#else>Column</#if>
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