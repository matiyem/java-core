package com.example.spliteratorAPI;

import java.util.List;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 2:10 PM
 **/
public class Article {
    private List<Author> listOfAuthors;
    private int id;
    private String name;

    public Article(List<Author> listOfAuthors, int id) {
        super();
        this.listOfAuthors = listOfAuthors;
        this.id = id;
    }

    public Article(String name) {
        this.name = name;
    }

    public List<Author> getListOfAuthors() {
        return listOfAuthors;
    }

    public void setListOfAuthors(List<Author> listOfAuthors) {
        this.listOfAuthors = listOfAuthors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
