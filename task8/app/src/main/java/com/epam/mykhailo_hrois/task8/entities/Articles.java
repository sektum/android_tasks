package com.epam.mykhailo_hrois.task8.entities;

import java.io.Serializable;
import java.util.List;

public class Articles implements Serializable {
    private List<Article> articles;

    public Articles() {
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
