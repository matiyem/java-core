package com.example.spliteratorAPI;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 2:09 PM
 **/
public class Author {
    private String name;
    private int relatedArticleId;

    public Author(String name, int relatedArticleId) {
        this.name = name;
        this.relatedArticleId = relatedArticleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRelatedArticleId() {
        return relatedArticleId;
    }

    public void setRelatedArticleId(int relatedArticleId) {
        this.relatedArticleId = relatedArticleId;
    }
    @Override
    public String toString() {
        return "[name: " + name + ", relatedId: " + relatedArticleId + "]";
    }
}
