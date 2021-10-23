package com.blog.hahmlog.test;


import com.blog.hahmlog.model.Role;
import com.blog.hahmlog.model.User;
import com.blog.hahmlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired //의존성 주입(DI)
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            return "삭제에 실패하였습니다. 해당 id는 존재하지 않습니다.";
        }

        return "삭제되었습니다. id: " +id;
    }

    // save 함수는 id를 전달하지 않으면 insert를 해주고
    // save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
    // save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 수행
    //email, password

    @Transactional //함수 종료시 자동 commit
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){ // json 데이터 -> Java Object(MessageConverter의 Jackson 라이브러리가 변환)
        System.out.println("id = " + id);
        System.out.println("password = " + requestUser.getPassword());
        System.out.println("email = " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패했습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        //userRepository.save(user);

        //더티 체킹
        return user;
    }

    // http://localhost:8000/blog/dummy/user
    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    // Pageable 객체 공부
    // 한페이지당 2건의 데이터를 리턴
    @GetMapping("/dummy/user")
    public Page<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return pagingUser;
    }

    // {id}주소로 파라미터를 전달 받을 수 있음
    // 이때 파라미터의 이름을 해당 키값과 동일하게 적어야 파싱됨
    // http://localhost:8000/blog/dummy/user/2
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){

        // DB에 없는 값을 찾게되면 null이 반환
        // 그럼 프로그램에 문제 발생
        // 때문에 Optional로 User 객체를 감싸서 반환해 null인지, 아닌지 판단 후 return;
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당하는 유저는 없습니다. id : " + id);
            }
        });
        // 요청: 웹브라우저
        // user객체 = 자바 오브젝트
        // 변환 필요 (웹 브라우저가 이해할 수 있는 데이터형식) -> json (Gson 라이브러리)
        // 스프링부트 -> MessageConverter 라는 애가 응답시 자동으로 작동
        // 만약 자바 오브젝트를 반환하면 MessageConverter가 Jackson 라이브러리를 호출해
        // user 오브젝트를 json으로 변환헤서 브라우저로 반환
        return user;
    }


    // http://localhost:8000/blog/dummy/join (요청)
    // http의 body에 username, password, email 데이터를 가지고 (요청)
    // 파라미터의 이름이 받을 데이터의 key 값들과 같은 경우
    // @RequestParam을 써주지 않아도 해당 파라미터에 파싱해서 자동으로 들어감
    @PostMapping("/dummy/join")
    public String join(User user){ //key-value(form데이터 형식 한정으로 약속된 규칙)
        System.out.println("username = " + user.getUsername());
        System.out.println("password = " + user.getPassword());
        System.out.println("email = " + user.getEmail());

        user.setRole(Role.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
