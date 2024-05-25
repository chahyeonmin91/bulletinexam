package org.example.bulletinexam.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("board")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class Board {

    @Id

    private Long id;
    private String name;
    private String title;
    private String password;
    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Board(String name, String title, String content, String password) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.password = password;
    }
}
