var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var mongoose = require('mongoose'); //ORM for MongoDb--allows to interact with mongodb

// Routes ------------------------------------------
var indexRouter = require('./routes/index');
var aboutRouter = require('./routes/about')
var sessionsRouter = require('./routes/sessions')
var viewRouter = require('./routes/view')
var helpRouter = require('./routes/help')
// Routes ------------------------------------------

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
//////// Database connection ////////////////////
const url = 'mongodb://localhost:27017/Fitness-Company'; 
const connect = mongoose.connect(url);

connect.then((db) => {
    console.log("Connected correctly to server");
}, (err) => { console.log(err); });
////////////////////////////////////////////

// App Use ------------------------------------------
app.use('/', indexRouter);
app.use('/about', aboutRouter)
app.use('/create', sessionsRouter)
app.use('/view', viewRouter)
app.use('/help', helpRouter)
// App Use ------------------------------------------

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
