package com.niksaen.doc_manager.services;

import com.niksaen.doc_manager.configurations.DatabaseConfiguration;
import com.niksaen.doc_manager.models.Document;
import com.niksaen.doc_manager.rowmappers.DocumentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DocumentService {
    JdbcTemplate jdbcTemplate;
    DocumentRowMapper documentRowMapper =  new DocumentRowMapper();
    public DocumentService(){
        jdbcTemplate = new JdbcTemplate(new DatabaseConfiguration().getDataSource());
    }
    public List<Document> getAllDocuments(){
        return jdbcTemplate.query("SELECT * FROM `documents`;",documentRowMapper);
    }

    public List<Document> getFilteredDocuments(String value){
        String sql = "SELECT * FROM `documents` " +
                "WHERE `id` = \""+value+"\" " +
                "OR `name` LIKE '%"+value+"%' " +
                "OR `type` LIKE '%"+value+"%'";
        return jdbcTemplate.query(sql,documentRowMapper);
    }
    public Document getDocumentById(int id){
        String sql = "SELECT * FROM `documents` WHERE `id` = "+id+";";
        return jdbcTemplate.query(sql,documentRowMapper).get(0);
    }


    public void addDocument(Document document){
        String date = LocalDate.now().getYear()+"-"+LocalDate.now().getMonth().getValue()+"-"+LocalDate.now().getDayOfMonth();
        String sql = "INSERT INTO `documents` " +
                "(`id`, `name`, `create_date`, `type`) VALUES " +
                "(NULL, '"+document.name+"', '"+date+"', '"+document.type+"');";
        jdbcTemplate.execute(sql);
    }

    public void editDocument(Document document){
        String sql = "UPDATE `documents` SET `name` = \""+document.name+"\", `type` = \""+document.type+"\" WHERE `id` = "+document.id+";";
        jdbcTemplate.execute(sql);
    }
    public void deleteDocument(int id){
        String sql = "DELETE FROM `documents` WHERE `id` = "+id+";";
        jdbcTemplate.execute(sql);
    }
}
