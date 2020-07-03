package com.fancyliu.learning.elasticsearch.repository;

import com.fancyliu.learning.elasticsearch.LearningElasticsearchApplicationTests;
import com.fancyliu.learning.elasticsearch.entity.Article;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleRepositoryTest extends LearningElasticsearchApplicationTests {

    @Autowired
    private ArticleRepository repository;

    /**
     * 新增数据
     */
    @Test
    public void saveArticles() {
        repository.save(new Article("百年孤独666", "人文社科", "马尔克斯", "诺贝尔文学奖作品", 2000));
        repository.save(new Article("凡人修仙传666", "玄幻", "忘语", "网络文学", 242));
    }

    /**
     * 查询所有
     */
    @Test
    public void fetchAllArticles() {
        System.out.println("Articles found with findAll():");
        System.out.println("-------------------------------");
        for (Article article : repository.findAll()) {
            System.out.println(article);
        }
    }

    /**
     * 删除
     */
    @Test
    public void deleteArticles() {
        repository.deleteByAuthor("忘语");
//        repository.deleteAll();
    }

    /**
     * 更新
     */
    @Test
    public void updateArticles() {
        Article article = repository.findByAuthor("马尔克斯");
        System.out.println(article);
        article.setContent("北京市海淀区西直门");
        repository.save(article);
        Article xarticle = repository.findByAuthor("马尔克斯");
        System.out.println(xarticle);
    }

    @Test
    public void fetchIndividualArticles() {
        for (Article article : repository.findByTitle("百年孤独")) {
            System.out.println(article);
        }
    }


}