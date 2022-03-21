package com.example.throwVsThrows;

import java.sql.SQLException;
import java.util.List;

/*
    Create by Atiye Mousavi 
    Date: 3/19/2022
    Time: 10:58 AM
**/
public class PersonRepository {

    public List<String> findAll() throws SQLException {
        throw new SQLException();
    }
}
