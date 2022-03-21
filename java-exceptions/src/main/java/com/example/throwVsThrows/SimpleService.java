package com.example.throwVsThrows;

import java.sql.SQLException;

/*
    Create by Atiye Mousavi 
    Date: 3/19/2022
    Time: 11:00 AM
**/
public class SimpleService {
    private PersonRepository personRepository = new PersonRepository();

    public void wrappingException() {
        try {
            personRepository.findAll();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Exception", e);
        }
    }
    public void runtimeNullPointerException(){
        String a=null;
        a.length();
    }
}
