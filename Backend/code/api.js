const express = require("express");
const router = express();


let datas = [
    { id: 1, email: "sinabro@gmail.com", password: "1234567" },
    { id: 2, email: "gram@gmial.com", password: "1234567" }
    // 자유롭게 추가해주세요.
]

router.get("/", function(req, res){
    res.status(200).json(datas);
})

router.post('/signup', function(req, res){
    // datas에 있는 id들 불러오기
    let itemId = datas.map(data => data.id);

    //  itemId 배열 중 가장 큰 ID에 +1해서 새 ID 생성하기 
    let newId = itemId.length > 0? Math.max.apply(Math, itemId) + 1 : 1;

    // 회원가입 하려는 이메일이 이미 있는지 확인
    let findemail = datas.find(function(item) {
        return item.email == req.body.email
    });

    // 회원가입 하려는 이메일이 만약 이미 있으면 에러 띄워주기
    if(findemail)
    {
        res.status(400).json({
            message: "이미 존재하는 사용자"
        });
    }
    else {
        // 새 데이터 만들기
        const newData = {
            id: newId,  // 위 단계에서 생성
            email: req.body.email,     // "email"의 값으로 post요청에서 가져오기
            password: req.body.password
        };

        datas.push(newData);
        res.status(201).json(newData);
    }
});

router.get("/login", function(req, res) {

    // 로그인을 하려는 이메일 찾기
    let findemail = datas.find(function(item) {
        return item.email == req.body.email;
    });

    if(findemail) {
        if(findemail.password == req.body.password) {   // 로그인하려는 이메일의 비밀번호와 회원가입에 등록된 비밀번호와 비교
        res.status(200).json({
            message: "로그인 성공"
        });
        }
        else res.status(400).json({ // 만약 비밀번호가 다르다면 400(잘못된 요청) 띄워주기
            message: "로그인 실패"
        });
    } else {
        res.status(404).json({  // 등록되지 않은 이메일이면 404(찾을 수 없음) 띄워주기
            message: "존재하지 않는 사용자"
        });
    };
});

module.exports = router;