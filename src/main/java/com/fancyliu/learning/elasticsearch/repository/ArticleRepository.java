package com.fancyliu.learning.elasticsearch.repository;

import com.fancyliu.learning.elasticsearch.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    List<Article> findByTitle(String title);

    Article findByAuthor(String author);

    int deleteByAuthor(String author);
}