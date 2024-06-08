package com.niksaen.doc_manager.models;

import java.util.Date;

public class Document{
    public int id;
    public String name;
    public Date createDate;
    public String type;

    public Document(int id, String name, Date createDate, String type) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.type = type;
    }

    public Document() {}
}
