const express = require("express");
const cors = require("cors");
const port = 3000;

const router = require("./api");    // api 가져오기

const app = express();
app.use(express.json());

app.use(cors({origin: 'http://localhost:3000'}));
    // 사용할 url이 api 엔드포인트에 엑세스하도록 허요

app.get('/', (req, res) => {
    res.send('hello world');
});

app.use('/data', router);

app.listen(port, () => {
    console.log(port, "번 포트에서 대기 중");
})