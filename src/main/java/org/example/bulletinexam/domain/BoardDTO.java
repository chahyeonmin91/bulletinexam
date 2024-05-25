package org.example.bulletinexam.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private Long id;
    private String name;
    private String title;
    private String content;
    private String createdAt;

    public BoardDTO(Long id, String name, String title, String content, String createdAt) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

}
