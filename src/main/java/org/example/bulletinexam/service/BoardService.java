package org.example.bulletinexam.service;

import lombok.RequiredArgsConstructor;
import org.example.bulletinexam.domain.Board;
import org.example.bulletinexam.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> findAllBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return boardRepository.findAll(pageable);
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
