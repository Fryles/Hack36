const express = require('express')
const fs = require('fs')
const bodyParser = require('body-parser')
const app = express()

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());


app.post('/post', (req, res) => {
  fs.readFile("./posts.json", function (err, existing) {
    var uuid = uuidv4();
    var data = req.body;
      var json = JSON.parse(existing);
      json[uuid] = data;
      fs.writeFile("./posts.json", JSON.stringify(json), "utf8", (err) => {
        if (err) throw err;
      });
      res.status(200).end("Succ");
    fs.readFile("./timetable.json", function (err, tt) {
      var json = JSON.parse(tt);
      json.unshift(uuid);
      fs.writeFile("./timetable.json", JSON.stringify(json), "utf8", (err) => {
        if (err) throw err;
      });
    });
  });
});


app.post('/auth', (req, res) => {
});

app.get('/json/*', (req, res) => {
});


app.use(express.static(__dirname + '/'));

app.listen(80, () => {
    console.log('Server is listening on port 80')
});