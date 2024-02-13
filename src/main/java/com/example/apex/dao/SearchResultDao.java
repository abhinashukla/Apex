package com.example.apex.dao;

import com.example.apex.entity.SearchResult;
import com.example.apex.exception.NoOccurencePresent;
import com.example.apex.exception.NoSearchesFound;
import com.example.apex.repository.SearchResultRepository;
import com.example.apex.utils.Constants;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchResultDao {

    @Autowired
    private SearchResultRepository searchResultRepository;

    /**
     * A method to save an search operation result into the db
     * @param searchResult
     * @throws InvalidFormatException
     */
    public void saveSearchResult(SearchResult searchResult) throws InvalidFormatException {
        try {
            searchResultRepository.save(searchResult);
            return;
        }
        catch (DataIntegrityViolationException ex){
            Throwable rootcause = ex.getRootCause();
            if(rootcause instanceof InvalidFormatException) {
                throw new InvalidFormatException("Invalid format of search result");
            } else {
                throw ex;
            }
        }
    }

    /**
     * @param word
     * @return a list of SearchResult objects where each object search term is word
     */
    public List<SearchResult> findAllByWord(String word){
        try{
            if(!searchResultRepository.findAllByWord(word).isEmpty()){
                return searchResultRepository.findAllByWord(word);
            } else {
                throw new NoOccurencePresent(Constants.NO_OCCURENCE);
            }
        } catch (NullPointerException ex){
            throw ex;
        }
    }

    /**
     *
     * @return all the searched keywords
     */
    public List<SearchResult> listAllSearches(){
        try{
            if(!searchResultRepository.findAll().isEmpty()){
                return searchResultRepository.findAll();
            } else {
                throw new NoSearchesFound(Constants.NO_SEARCHES);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     *
     * @param field
     * @return a list of SearchResult objects where the objects are sorted in ascending order based on the field passed
     */
    public List<SearchResult> listAllSearchesSorted(String field){
        try{
            if(!searchResultRepository.findAll().isEmpty()){
                return searchResultRepository.findAll(Sort.by(Sort.Direction.ASC, field));
            } else {
                throw new NoSearchesFound(Constants.NO_SEARCHES);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     *
     * @param pageNumber
     * @param pageSize
     * @param field
     * @return a list of SearchResult objects on index 'pageNumber' where the objects are sorted in ascending order based on the field passed and only 'pageSize'
     *         number of objects are displayed on one page
     */
    public Page<SearchResult> listAllSearchesPaginationAndSorted(int pageNumber, int pageSize, String field) {
        try{
            if(!searchResultRepository.findAll().isEmpty()){
                return (Page<SearchResult>) searchResultRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.Direction.ASC, field));
            } else {
                throw new NoSearchesFound(Constants.NO_SEARCHES);
            }
        } catch (Exception ex) {
            throw ex;
        }

    }
}
