const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const sessions = require('../models/session');

const sessionsRouter = express.Router();

// Render the Create Sessions site
sessionsRouter.route('/')
.get((req,res,next) => {
    res.render('Csessions.ejs', { title: 'Book a new Training Session' });   
})

// Process data from the form sent out
.post((req, res, next) => {
    sessions.create(req.body)
    .then((sessionCreated) => {
        console.log('Session Created:', sessionCreated);
        // render the confirmation site
        res.render('confirmation', { 
            title: 'Booking Confirmed!', 
            session: sessionCreated
        });
        
    })
    .catch((err) => next(err));
});

module.exports = sessionsRouter;

