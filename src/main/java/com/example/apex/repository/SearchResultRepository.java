package com.example.apex.repository;

import com.example.apex.entity.SearchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchResultRepository extends JpaRepository<SearchResult, Integer> {

    @Query("SELECT s FROM SearchResult s WHERE s.word = :word")
    List<SearchResult> findAllByWord(String word);
}
