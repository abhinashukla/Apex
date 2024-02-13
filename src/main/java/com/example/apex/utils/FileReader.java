package com.example.apex.utils;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileReader {

    public List<String> readFile(){
        String file = Constants.ABSOLUTE_FILE_PATH;
        List<XWPFParagraph> list = new ArrayList<>();
        List<String> strlist = new ArrayList<>();

        try (XWPFDocument doc = new XWPFDocument(
                Files.newInputStream(Paths.get(file)))) {

            list = doc.getParagraphs();

            for(XWPFParagraph para : list){
                String s = para.getText();
                strlist.add(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return strlist;
    }

}
