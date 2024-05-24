package org.example.bulletinexam.repository;



import org.example.bulletinexam.domain.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {

    @Query("SELECT * FROM board ORDER BY id DESC LIMIT :offset, :limit")
    List<Board> findLatestBoards(int offset, int limit);
}
