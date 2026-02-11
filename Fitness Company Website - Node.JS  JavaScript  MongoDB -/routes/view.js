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

// Creating additional routes PHASE 2 ////////////////////////

viewRouter.route('/update/:id') // reads id from url path
    .get((req, res, next) => {
        // Using req.params.id to get the id from the URL path
        sessions.findById(req.params.id) 
            .then((sessionFound) => { 
                if (!sessionFound) { 
                    // handle null case explicitly
                    const err = new Error ('Session not found.');
                    err.status = 404;
                    return next(err);
                }
                res.render("updatePage.ejs", { "session": sessionFound, title: "Editing Session" });
            })
            .catch((err) => next(err));
    });


viewRouter.route('/save-update')
    .post((req, res, next) => {
        const sessionId = req.body._id;
        
        //updating the info
        sessions.findByIdAndUpdate(sessionId, 
            { $set: req.body }, 
            { new: true, runValidators: true } 
        )
        .then(updatedSession => {
            if (updatedSession) {
                // Success: Redirect back to the list view
                res.redirect('/view');
            } else {
                const err = new Error ('Session not found for update.');
                err.status = 404;
                return next(err);
            }
        })
        .catch(err => {
            next(err);
        });
    }); 

viewRouter.route('/delete')
    .post((req,res,next) => {
        // getting session ID from form body
        const sessionId = req.body._id;

        sessions.findByIdAndDelete(sessionId)
        .then((sessionDeleted) => {
            if (sessionDeleted) {
                //if deleted redirect to the list to show its gone
                res.redirect('/view');
            } else{
                const err = new Error('session not found, cannot delete')
                err.status = 404;
                return next(err);
            }
        })
        .catch(err =>{
            next(err);
        })
    });

viewRouter.route('/report')
    .get((req,res,next) =>{
        // Renders the form
        res.render('reportForm', {title: 'Generate Report Form'});
    });

viewRouter.route('/generate-report')
    .post((req,res,next) =>{
        // Preparing the form info
        const {name, startDate, endDate} = req.body;

        const queryFilter = {
            name: name, 
            DateTime:{ // filter data range where datetime >= startdate and datetime <=enddate
                $gte: new Date(startDate),
                $lte: new Date(endDate)
            }
        };

        sessions.find(queryFilter)
            .then((sessionsFound) =>{
                // render report page with the info
                res.render('reportDisplay', {
                    reportName: name,
                    startDate: startDate,
                    endDate: endDate,
                    sessions: sessionsFound,
                    title: `Report for ${name}`
                });
            })
            .catch((err) => next(err));
    })
                       //PHASE 2 ////////////////////////////////
module.exports = viewRouter;