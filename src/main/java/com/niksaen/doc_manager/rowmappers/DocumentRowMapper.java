package com.niksaen.doc_manager.rowmappers;

import com.niksaen.doc_manager.models.Document;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentRowMapper implements RowMapper<Document> {
    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Document(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDate("create_date"),
                rs.getString("type"));
    }
}
