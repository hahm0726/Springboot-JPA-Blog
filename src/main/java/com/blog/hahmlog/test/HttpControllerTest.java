package com.blog.hahmlog.test;


import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답(HTML 파일) ==> @Controller 사용

//사용자가 요청 -> 응답(Data)

@RestController
public class HttpControllerTest {

    //인터넷 브라우저 요청은 무조건 GET 요청밖에 할 수 없다
    // http://localhost:8080//http/get (select)
    @GetMapping("/http/get")
    public String getTest(Member m){
        return "get 요청 : " + m.getId() +", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }

    // http://localhost:8080//http/get (insert)
    // raw 데이터 => MIMEType: text/plain @RequestBody로 받음
    // application/json => json의 키값들이 일치하는 클래스로 받아 객체로 처리 가능
    // application/json으로 날라온 requestBody 데이터를 스프링부트의 MessageConverter가
    // 명시한 클래스 객체로 파싱해서 데이터를 처리해준다
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){

        return "post 요청 : " + m.getId() +", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }

    // http://localhost:8080//http/get (update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m){

        return "put 요청" + m.getId() +", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
    }

    // http://localhost:8080//http/get (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }

    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member m = new Member(1,"test","1234","aaaa");
        System.out.println(m.getId());
        m.setId(5000);
        System.out.println(m.getId());
        return "lombok test 완료";
    }
}
