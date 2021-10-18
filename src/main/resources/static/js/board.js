let index = {
    init:function (){
        $("#btn-save").on("click",()=>{ // function(){}, ()=>{} this를 바인딩하기 위해서!!
            this.save();
        });
    },

    save:function(){

        let data={
            title:$("#title").val(),
            content:$("#content").val()
        };

        //ajax호출 시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 파라미터를 json으로 변경하여 insert
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환
        $.ajax({
            //회원가입 수행 요청
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType:"json"
        }).done(function(res){
            alert("글쓰기가 완료되었습니다.");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },


}

index.init();