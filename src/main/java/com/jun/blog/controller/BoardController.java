package com.jun.blog.controller;

import com.jun.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    /*@Autowired
    private PrincipalDetail principal;*/

    //@AuthenticationPrincipal PrincipalDetail principal
    //컨트롤러에서 세션을 어떻게 찾는지?
    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 3,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        model.addAttribute("boards",boardService.글목록(pageable));
        //WEB-INF/views/index.jsp
        /*System.out.println("로그인 사용자 아이디 : "+principal.getUsername());*/
        return "index";//viewResolver 작동
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id,Model model){
        model.addAttribute("board",boardService.글상세보기(id));
        return "board/detail";
    }

    //user권한이 필요
    @GetMapping("/board/saveForm")
    public String saveFrom(){
        return "board/saveForm";
    }
}



