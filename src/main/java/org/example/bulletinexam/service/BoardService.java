package org.example.bulletinexam.service;

import lombok.RequiredArgsConstructor;
import org.example.bulletinexam.domain.Board;
import org.example.bulletinexam.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> findAllBoards() {
        Iterable<Board> boards = boardRepository.findAll();
        return StreamSupport.stream(boards.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    public void updateBoard(Board board) {
        boardRepository.save(board);
    }

    public void deleteBoard(Long id, String password) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board != null && board.getPassword().equals(password)) {
            boardRepository.deleteById(id);
        }
    }



}
