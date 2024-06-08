package com.niksaen.doc_manager.controllers;

import com.niksaen.doc_manager.models.Document;
import com.niksaen.doc_manager.services.DocumentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Controller
public class DocManagerController {

    DocumentService documentService;
    DocManagerController(){
        this.documentService = new DocumentService();
    }

    @GetMapping("/")
    public String getMainPage(Model model){
        model.addAttribute("documents",documentService.getAllDocuments());
        return "index";
    }

    @GetMapping("/filter_by_value")
    public String getFilteredList(@RequestParam(value = "value") String value,Model model){
        model.addAttribute("documents",documentService.getFilteredDocuments(value));
        if(Objects.equals(value, "")) return "redirect:/";
        return "index";
    }

    @GetMapping("/add_document_page")
    public String getAddDocumentPage(){
        return "add_document_page";
    }

    @PostMapping("/add_document")
    public String addDocument(@RequestParam(value = "name") String name, @RequestParam(value = "type") String type ,Model model){
        Document document = new Document();
        if(!name.isEmpty() && !type.isEmpty()) {
            document.name = name;
            document.type = type;
            documentService.addDocument(document);
            return "redirect:/";
        }
        else{
            model.addAttribute("message","Name or type is empty");
            return "error_page";
        }
    }

    @GetMapping("/edit_document_page")
    public String getEditDocumentPage(@RequestParam(value = "id") int id,Model model){
        Document document = documentService.getDocumentById(id);
        model.addAttribute("id",id);
        model.addAttribute("name",document.name);
        model.addAttribute("createDate",document.createDate);
        model.addAttribute("type",document.type);
        return "edit_document_page";
    }

    @PostMapping(value = "/edit_document")
    public String editDocument(@RequestParam(value = "id") int id,@RequestParam(value = "name") String name,@RequestParam(value = "type") String type,Model model){
        Document document = documentService.getDocumentById(id);
        if(!name.isEmpty()) document.name = name;
        if(!type.isEmpty()) document.type = type;
        documentService.editDocument(document);
        return "redirect:/";
    }

    @PostMapping(value = "/delete_document")
    public String deleteDocument(@RequestParam(value = "id") int id, Model model){
        documentService.deleteDocument(id);
        return "redirect:/";
    }
}
