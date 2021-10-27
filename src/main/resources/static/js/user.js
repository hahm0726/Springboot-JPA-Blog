let index = {
    init:function (){
        $("#btn-save").on("click",()=>{ // function(){}, ()=>{} this를 바인딩하기 위해서!!
            this.save();
        });

        $("#btn-update").on("click",()=>{ // function(){}, ()=>{} this를 바인딩하기 위해서!!
            this.update();
        });

        // $("#btn-login").on("click",()=>{ // function(){}, ()=>{} this를 바인딩하기 위해서!!
        //     this.login();
        // });
    },

    save:function(){
        //alert("user의 save함수 호출됨");
        let data={
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        };

        //ajax호출 시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 파라미터를 json으로 변경하여 insert
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환
        $.ajax({
            //회원가입 수행 요청
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType:"json" //응답의 결과가 생긴게 json 이라면 => javascript 오브젝트로 변경(done에 res)
        }).done(function(res){
            if(res.status==500){
                alert("회원가입에 실패했습니다.");
            }
            alert("회원가입이 완료되었습니다");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    update:function(){

        let data={
            id:$("#userId").val(),
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        };

        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType:"json" //응답의 결과가 생긴게 json 이라면 => javascript 오브젝트로 변경(done에 res)
        }).done(function(res){
            alert("수정이 완료되었습니다");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    // login:function(){
    //     let data={
    //         username:$("#username").val(),
    //         password:$("#password").val(),
    //     };
    //
    //     $.ajax({
    //
    //         type: "POST",
    //         url: "/api/user/login",
    //         data: JSON.stringify(data), // http body 데이터
    //         contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
    //         dataType:"json" //응답의 결과가 생긴게 json 이라면 => javascript 오브젝트로 변경(done에 res)
    //     }).done(function(res){
    //         alert("로그인이 완료되었습니다");
    //         location.href="/";
    //     }).fail(function(error){
    //         alert(JSON.stringify(error));
    //     });
    // }
}

index.init();