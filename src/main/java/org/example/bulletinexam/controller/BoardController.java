package org.example.bulletinexam.controller;



import lombok.RequiredArgsConstructor;
import org.example.bulletinexam.domain.Board;
import org.example.bulletinexam.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String listBoards(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 5; // 페이지당 보여질 게시글 수
        List<Board> boards = boardService.findBoards(page, pageSize);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/view")
    public String viewBoard(@RequestParam Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "board/view";
    }

    @GetMapping("/writeform")
    public String writeForm() {
        return "board/writeform";
    }

    @PostMapping("/write")
    public String writeBoard(Board board) {
        boardService.saveBoard(board);
        return "redirect:/list";
    }

    @GetMapping("/updateform")
    public String updateForm(@RequestParam Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "board/updateform";
    }

    @PostMapping("/update")
    public String updateBoard(Board board) {
        boardService.updateBoard(board);
        return "redirect:/list";
    }

    @GetMapping("/deleteform")
    public String deleteForm(@RequestParam Long id, Model model) {
        model.addAttribute("id", id);
        return "board/deleteform";
    }

    @PostMapping("/delete")
    public String deleteBoard(@RequestParam Long id, @RequestParam String password) {
        boardService.deleteBoard(id, password);
        return "redirect:/list";
    }
}
