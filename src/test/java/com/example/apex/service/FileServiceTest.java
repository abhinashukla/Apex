package com.example.apex.service;

import com.example.apex.dao.SearchResultDao;
import com.example.apex.entity.SearchResult;
import com.example.apex.utils.FileReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class FileServiceTest {

    @Mock
    private FileReader fileReader;

    @Mock
    private SearchResultDao searchResultDao;

    @InjectMocks
    private FileService fileService;

    @Test
    void readFileTest(){

        String s1 = "This is a unit test";
        String s2 = "Writing few test cases";

        Mockito.when(fileReader.readFile()).thenReturn(List.of(s1, s2));
        List<String> expected = List.of(s1, s2);
        List<String> actual = fileService.readFile();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void searchWordTest() throws OpenXML4JException, XmlException, IOException {
        String str = "HashedIn";
        SearchResult search1 = new SearchResult();
        search1.setWord(str);
        search1.setFilename("Apex Word File.docx");
        search1.setCount(1);
        search1.setCreatedBy("Abhinav");
        search1.setCreatedDate(Calendar.getInstance().getTime());

        SearchResult search2 = new SearchResult();
        search2.setWord(str);
        search2.setFilename("Apex Word File_2.docx");
        search2.setCount(3);
        search2.setCreatedBy("Abhinav");
        search2.setCreatedDate(Calendar.getInstance().getTime());

        Mockito.doNothing().when(searchResultDao).saveSearchResult(any(SearchResult.class));
        List<SearchResult> expected = List.of(search1, search2);
        List<SearchResult> actual = fileService.searchWord(str);

        Assertions.assertEquals(expected.size(), actual.size());
    }

}
