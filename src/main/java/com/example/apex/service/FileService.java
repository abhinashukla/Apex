package com.example.apex.service;

import com.example.apex.dao.SearchResultDao;
import com.example.apex.dto.SearchDto;
import com.example.apex.entity.SearchResult;
import com.example.apex.exception.NoFilePresent;
import com.example.apex.exception.NoOccurencePresent;
import com.example.apex.utils.Constants;
import com.example.apex.utils.FileReader;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class FileService {

    @Autowired
    private FileReader fileReader;

    @Autowired
    private SearchResultDao searchResultDao;

    public List<String> readFile() {
        return fileReader.readFile();
    }

    public List<SearchResult> searchWord(String search) throws OpenXML4JException, XmlException, IOException, NoOccurencePresent, NoFilePresent {
        String folder = Constants.ABSOLUTE_FOLDER_PATH;
        List<SearchResult> searchResultList = new ArrayList();
        Boolean found = false;

        File files = new File(folder);
        File[] fileList = files.listFiles();

        if(fileList.length == 0) {
//            return ResponseEntity() // 200 with response message
            throw new NoFilePresent(Constants.NO_OFFICE_FILE_MSG);
        } else {
            for(File file:fileList){

                XWPFWordExtractor wordExtractor = new XWPFWordExtractor(OPCPackage.open(file));
                String contents = wordExtractor.getText();
                int count = (int) Arrays.stream(contents.split("\\s+")).filter(s -> s.equalsIgnoreCase(search)).count();
                if(count > 0) found = true;
                SearchResult searchResult = new SearchResult();
                searchResult.setWord(search);
                searchResult.setFilename(file.getName());
                searchResult.setCount(count);
                searchResult.setCreatedBy("Abhinav");
                searchResult.setCreatedDate(Calendar.getInstance().getTime());
                searchResultDao.saveSearchResult(searchResult);
                searchResultList.add(searchResult);
            }
            if(found==false) throw new NoOccurencePresent(Constants.NO_OCCURENCE);
        }

        return searchResultList;
    }

    public List<SearchDto> searchUser(String search) {
        List<SearchResult> searchResults = searchResultDao.findAllByWord(search);
        List<SearchDto> res = new ArrayList<>();

        for(SearchResult searchResult:searchResults){
            SearchDto searchDto = new SearchDto();
            searchDto.setSearchWord(searchResult.getWord());
            searchDto.setSearchedBy(searchResult.getCreatedBy());
            searchDto.setSearchTime(searchResult.getCreatedDate());
            res.add(searchDto);
        }

        return res;
    }

    public List<SearchResult> listAllSearches() {
        List<SearchResult> searchResults = searchResultDao.listAllSearches();
        return searchResults;
    }

    public List<SearchResult> listAllSearchesSorted(String field) {
        List<SearchResult> searchResults = searchResultDao.listAllSearchesSorted(field);
        return searchResults;
    }

    public Page<SearchResult> listAllSearchesPaginationAndSorted(int pageNumber, int pageSize, String field) {
        Page<SearchResult> searchResults = searchResultDao.listAllSearchesPaginationAndSorted(pageNumber, pageSize, field);
        return searchResults;
    }
}
