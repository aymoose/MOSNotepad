package com.mos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mos.po.Article;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-23
 * Time:15:21
 */
public class TestJson {

    @Test
    public void testJackson() {
        List<Article> articleList = new ArrayList<>();
        Article article1 = new Article();
        article1.setId(1);
        article1.setTitle("博客1");
        article1.setContent("内容1");
        article1.setCreateTime(new Date());
        articleList.add(article1);
        Article article2 = new Article();
        article2.setId(1);
        article2.setTitle("博客2");
        article2.setContent("内容2");
        article2.setCreateTime(new Date());
        articleList.add(article2);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            String result = objectMapper.writeValueAsString(articleList);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
