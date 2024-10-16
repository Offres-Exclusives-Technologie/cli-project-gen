const ${currentEntity.name}Service = require('../services/${currentEntity.name}Service');
const logger = require('../config/logger');
const { validate${currentEntity.name} } = require('../validators/${currentEntity.name}Validator');

class ${currentEntity.name}Controller {
    async create(req, res, next) {
        try {
            const validatedData = await validate${currentEntity.name}(req.body);
            const result = await ${currentEntity.name}Service.create(validatedData);
            logger.info(`Created ${currentEntity.name}: ${result.id}`);
            res.status(201).json(result);
        } catch (error) {
            next(error);
        }
    }

    async getById(req, res, next) {
        try {
            const result = await ${currentEntity.name}Service.getById(req.params.id);
            if (!result) {
                return res.status(404).json({ message: '${currentEntity.name} not found' });
            }
            res.json(result);
        } catch (error) {
            next(error);
        }
    }

    // ... other CRUD methods
}


module.exports = new ${currentEntity.name}Controller();