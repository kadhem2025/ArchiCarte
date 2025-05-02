// server/app.js

const express = require('express');
const cors = require('cors');

const app = express();

// Configure CORS with the specific origin of your Angular app
var cors = require('cors');
app.use(cors());

// Your other route handlers go here

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
