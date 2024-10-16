const express = require('express');
const router = express.Router();
const ${currentEntity.name}Controller = require('../controllers/${currentEntity.name}Controller');
<#if useSecurity>
const { authenticate, authorize } = require('../middleware/auth');

router.post('/',
    authenticate,
    authorize(['admin']),
    ${currentEntity.name}Controller.create
);

router.get('/:id',
    authenticate,
    ${currentEntity.name}Controller.getById
);
<#else>
router.post('/', ${currentEntity.name}Controller.create);
router.get('/:id', ${currentEntity.name}Controller.getById);
</#if>

// ... other routes

module.exports = router;