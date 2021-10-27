let index = {
    init:function (){
        //게시글 관련
        $("#btn-save").on("click",()=>{ // function(){}, ()=>{} this를 바인딩하기 위해서!!
            this.save();
        });

        $("#btn-update").on("click",()=>{
            this.update();
        });

        $("#btn-delete").on("click",()=>{
            this.deleteById();
        });

        //댓글 관련
        $("#btn-delete").on("click",()=>{
            this.deleteById();
        });

        $("#btn-reply-save").on("click",()=>{
            this.replySave();
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
            //글쓰기 수행 요청
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

    deleteById:function(){

        let boardId = $("boardId").text();

        $.ajax({
            //삭제수행 요청
            type: "DELETE",
            url: "/api/board/"+ boardId,
            dataType:"json"
        }).done(function(res){
            alert("게시글이 삭제되었습니다.");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    update:function(){
        let boardId = $("#boardId").val();

        let data={
            title:$("#title").val(),
            content:$("#content").val()
        };

        $.ajax({
            //글수정 요청
            type: "PUT",
            url: "/api/board/" + boardId,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType:"json"
        }).done(function(res){
            alert("수정이 완료되었습니다.");
            location.href="/board/"+boardId;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    replySave:function(){

        let data={
            userId:$("#userId").val(),
            boardId:$("#boardId").val(),
            content:$("#reply-content").val()
        };

        $.ajax({

            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType:"json"
        }).done(function(res){
            alert("댓글 작성이 완료되었습니다.");
            location.href=`/board/${data.boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    replyDelete:function(boardId,replyId){
        $.ajax({

            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType:"json"
        }).done(function(res){
            alert("댓글 삭제 성공.");
            location.href=`/board/${boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

}

index.init();