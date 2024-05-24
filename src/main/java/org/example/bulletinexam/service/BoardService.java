package org.example.bulletinexam.service;

import lombok.RequiredArgsConstructor;
import org.example.bulletinexam.domain.Board;
import org.example.bulletinexam.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> findBoards(int page, int pageSize) {
        int offset = page * pageSize;
        return boardRepository.findLatestBoards(offset, pageSize);
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
