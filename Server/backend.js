const express = require('express')
const fs = require('fs')
const bodyParser = require('body-parser')
const app = express()
const { v4: uuidv4 } = require("uuid");

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());


app.post('/post', (req, res) => {
  fs.readFile("./posts.json", function (err, existing) {
    var data = req.body;
      var json = JSON.parse(existing);
      json.unshift({'img':data.img,'hashes':JSON.parse(data.hashes)});
      fs.writeFile("./posts.json", JSON.stringify(json), "utf8", (err) => {
        if (err) throw err;
      });
      res.status(200).end("Succ");
  });
});


app.get( '/', (req,res) => {
  var tobe = [];
  var reqhash = req.query.hash
  fs.readFile("./posts.json", function (err, existing) {
    var json = JSON.parse(existing);
    for(let i = 0;i<json.length;i++){
      for(let u = 0;u<json[i].hashes.length;u++){
        console.log(i+":  "+json[i].hashes[u]);
        if(json[i].hashes[u] == reqhash){
          tobe.push(json[i]);
        }
      }
    }
    res.status(200).end(JSON.stringify(tobe));
  })
  
});

app.listen(80, () => {
    console.log('Server is listening on port 80')
});