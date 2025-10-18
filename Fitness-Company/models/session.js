const mongoose = require('mongoose');
const Schema = mongoose.Schema;

// Seperate Schema for card details, to use different tables in the DB, NOT SHOWN PUBLICLY FOR SECURITY AND AVOIDING BAD PRACTICE
const cardSchema = new Schema({
  cardNum: {
    type: String,
    required: true,     // The below throws exception if mongoose rejects//
    match: [/^\d{16}$/, "Card number must be 16 digits"]
  },
  expiry: {
    type: String,
    required: true,
    match: [/^(0[1-9]|1[0-2])\/\d{2}$/, "Expiry must be in MM/YY format"]
  },
  securityCode: {
    type: String,
    required: true,
    match: [/^\d{3,4}$/, "Security code must be 3 or 4 digits"]
  }
});
// Seperate "table" for ease 
const personTrainingSchema = new Schema({
  name: {
    type: String,
    required: true,
    trim: true
  },
    ID: {
    type: String,
    required: true,
    match: [/^.{10}$/, "ID must be exactly 10 characters long"]
  },
    DateTime: {
    type: Date,
    required: true
  },
  cardDetails: {
    type: cardSchema,
    required: true
  }
}, {
  timestamps: true
});

var sessions = mongoose.model('sessions', personTrainingSchema);

module.exports = sessions;
