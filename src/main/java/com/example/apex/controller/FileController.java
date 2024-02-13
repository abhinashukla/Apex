package com.example.apex.controller;

import com.example.apex.dto.SearchDto;
import com.example.apex.entity.SearchResult;
import com.example.apex.exception.NoFilePresent;
import com.example.apex.exception.NoOccurencePresent;
import com.example.apex.service.FileService;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RestControllerAdvice
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/readfile")
    public ResponseEntity<List<String>> readFileMethod(){
        return new ResponseEntity(fileService.readFile(), HttpStatus.OK);
    }

    @GetMapping("/search/{word}")
    public ResponseEntity<List<SearchResult>> wordSearch(@PathVariable String word) throws OpenXML4JException, XmlException, IOException, NoOccurencePresent, NoFilePresent {
        return new ResponseEntity(fileService.searchWord(word), HttpStatus.OK);
    }

    @GetMapping("/search-user/{word}")
    public ResponseEntity<SearchDto> searchUser(@PathVariable String word){
        return new ResponseEntity(fileService.searchUser(word), HttpStatus.OK);
    }

    @GetMapping("/all-searches")
    public ResponseEntity<List<SearchResult>> listAllSearches(){
        return new ResponseEntity(fileService.listAllSearches(), HttpStatus.OK);
    }

    @GetMapping("/all-searches/{field}")
    public ResponseEntity<List<SearchResult>> listAllSearchesSorted(@PathVariable String field){
        return new ResponseEntity(fileService.listAllSearchesSorted(field), HttpStatus.OK);
    }

    @GetMapping("/all-searches/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<Page<SearchResult>> listAllSearchesPaginationAndSorted(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field){
        return new ResponseEntity(fileService.listAllSearchesPaginationAndSorted(pageNumber, pageSize, field), HttpStatus.OK);
    }


}
