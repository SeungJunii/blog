package com.jun.blog.test;

import com.jun.blog.model.Board;
import com.jun.blog.model.Reply;
import com.jun.blog.repository.BoardRepository;
import com.jun.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReplyControllerTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id) {
        return boardRepository.findById(id).get(); // jackson 라이브러리 (오브젝트를 json으로 리턴) => 모델의 getter를 호출
    }

    @GetMapping("/test/reply")
    public List<Reply> getReply() {//리플을 직접호출
        return replyRepository.findAll(); // jackson 라이브러리 (오브젝트를 json으로 리턴) => 모델의 getter를 호출
    }
}
