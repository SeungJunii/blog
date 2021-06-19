package com.jun.blog.controller;

import com.jun.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    /*@Autowired
    private PrincipalDetail principal;*/

    //@AuthenticationPrincipal PrincipalDetail principal
    //컨트롤러에서 세션을 어떻게 찾는지?
    @GetMapping({"","/"})
    public String index(Model model){
        model.addAttribute("boards",boardService.글목록());
        //WEB-INF/views/index.jsp
        /*System.out.println("로그인 사용자 아이디 : "+principal.getUsername());*/
        return "index";//viewResolver 작동
    }

    @GetMapping("/board/saveForm")
    public String saveFrom(){
        return "board/saveForm";
    }
}



