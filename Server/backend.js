const express = require('express')
const fs = require('fs')
const app = express()

app.use(express.json({limit: '25mb'}));
app.use(express.urlencoded({limit: '25mb'}));


app.post('/post', (req, res) => {
  fs.readFile("./posts.json", function (err, existing) {
    var data = req.body;
      var json = JSON.parse(existing);
      console.log(json);
      json.unshift({'img':data.img,'hashes':JSON.parse(data.hashes),'email':data.email,'user':data.user});
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