const express = require('express');
const cors = require('cors');
const morgan = require('morgan');
const routes = require('./routes');
const errorHandler = require('./middleware/errorHandler');
const logger = require('./config/logger');

require('dotenv').config();

const app = express();

// Middleware
app.use(cors());
app.use(morgan('dev'));
app.use(express.json());

// Routes
app.use('/api', routes);

// Error handling
app.use(errorHandler);

module.exports = app;

// templates/core/server.js.ftl
const app = require('./app');
const logger = require('./config/logger');
const config = require('./config/database');

const PORT = process.env.PORT || 3000;

<#if useMysql>
// Database connection
config.authenticate()
    .then(() => logger.info('Database connected'))
    .catch(err => logger.error('Database connection error:', err));
<#else>
// Database connection
const mongoose = require('mongoose');
mongoose.connect(process.env.MONGODB_URI, {
    useNewUrlParser: true,
    useUnifiedTopology: true
})
.then(() => logger.info('MongoDB connected'))
.catch(err => logger.error('MongoDB connection error:', err));
</#if>

app.listen(PORT, () => {
    logger.info(`Server running on port ${PORT}`);
});
