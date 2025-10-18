const express = require('express');
const sessions = require('../models/session');

const viewRouter = express.Router();

viewRouter.route('/')
.get((req, res, next) => {
    // Finding all sessions in DB
    sessions.find({})
    .then((sessionsFound) => {
        // Rendering found data
        res.render('sessionlist', { 
            'sessionlist': sessionsFound, 
            title: 'All Training Sessions'
        });
    })
    .catch((err) => next(err)); 
});

module.exports = viewRouter;