package com.fancyliu.learning.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "blog", type = "article", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
public class Article {

    @Id
    private String id;
    private String title;
    private String classify;
    private String author;
    private String content;
    private int likenum;

    public Article(String id, String title, String classify, String author, String content, int likenum) {
        this.id = id;
        this.title = title;
        this.classify = classify;
        this.author = author;
        this.content = content;
        this.likenum = likenum;
    }

    public Article() {
    }

    public Article(String title, String classify, String author, String content, int likenum) {
        this.title = title;
        this.classify = classify;
        this.author = author;
        this.content = content;
        this.likenum = likenum;
    }
}
