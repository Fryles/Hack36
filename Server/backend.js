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
      json[uuid] = {'img':data.img,'hashes':data.hashes};
      fs.writeFile("./posts.json", JSON.stringify(json), "utf8", (err) => {
        if (err) throw err;
      });
      res.status(200).end("Succ");
  });
});


app.get( '/', (req,res) => {
  var hash = req.query.hash

  res.status(200).end("Succ");
});

app.listen(80, () => {
    console.log('Server is listening on port 80')
});