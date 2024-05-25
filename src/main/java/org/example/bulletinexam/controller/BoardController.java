package org.example.bulletinexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.bulletinexam.domain.Board;
import org.example.bulletinexam.domain.BoardDTO;
import org.example.bulletinexam.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String listBoards(Model model) {
        Iterable<Board> posts = boardService.findAllBoards();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        List<BoardDTO> boardDTOs = ((List<Board>) posts).stream()
                .map(board -> new BoardDTO(
                        board.getId(),
                        board.getName(),
                        board.getTitle(),
                        board.getContent(),
                        board.getCreatedAt().format(formatter)))
                .collect(Collectors.toList());

        model.addAttribute("boards", boardDTOs);
        return "list";
    }

    @GetMapping("/view")
    public String viewBoard(@RequestParam Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "view";
    }

    @GetMapping("/writeform")
    public String writeForm() {
        return "writeform";
    }

    @PostMapping("/write")
    public String writeBoard(
            @RequestParam("name") String name,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("password") String password) {
        Board newBoard = new Board(name, title, content, password);
        boardService.saveBoard(newBoard);
        return "redirect:/list";
    }

    @GetMapping("/updateform")
    public String updateForm(@RequestParam Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "updateform";
    }

    @PostMapping("/update")
    public String updateBoard(Board board) {
        boardService.updateBoard(board);
        return "redirect:/list";
    }

    @PostMapping("/delete")
    public String deleteBoard(@RequestParam Long id, @RequestParam String password) {
        boardService.deleteBoard(id, password);
        return "redirect:/list";
    }
    @GetMapping("/deleteform")
    public String deleteForm(@RequestParam Long id, Model model) {
        model.addAttribute("id", id);
        return "deleteform";
    }
}
