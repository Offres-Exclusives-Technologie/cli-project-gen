<#if useMysql>
const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const ${currentEntity.name} = sequelize.define('${currentEntity.name?lower_case}', {
    <#list currentEntity.fields as field>
    ${field.name}: {
        type: DataTypes.${field.type?upper_case},
        <#if field.required?? && field.required>
        allowNull: false,
        </#if>
        <#if field.unique?? && field.unique>
        unique: true,
        </#if>
    },
    </#list>
}, {
    timestamps: true
});

module.exports = ${currentEntity.name};
<#else>
const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const ${currentEntity.name}Schema = new Schema({
    <#list currentEntity.fields as field>
    ${field.name}: {
        type: ${field.type},
        <#if field.required?? && field.required>
        required: true,
        </#if>
        <#if field.unique?? && field.unique>
        unique: true,
        </#if>
    },
    </#list>
}, {
    timestamps: true
});

module.exports = mongoose.model('${currentEntity.name}', ${currentEntity.name}Schema);
</#if>