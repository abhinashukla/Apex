package com.example.apex.service;

import com.example.apex.dao.PersonDao;
import com.example.apex.dto.PersonStatsDto;
import com.example.apex.dto.PersonStatsInputDto;
import com.example.apex.entity.Address;
import com.example.apex.entity.Person;
import com.example.apex.utils.Constants;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    public Person createPerson(Person person) {
        personDao.savePerson(person);
        return person;
    }

    public Person getPerson(int id) {
        Person person = personDao.getPerson(id);
        return person;
    }

    public Long getPersonCount() {
        return personDao.getPersonCount();
    }

    public List<String> getPersonNames() {
        return personDao.getPersonNames();
    }

    public List<Person> getAllPerson() {
        return personDao.getAllPerson();
    }

    public PersonStatsDto getPersonStats(PersonStatsInputDto input) {
        return personDao.getPersonStats(input);
    }

    public void createPersonInBatch(){
        try
        {
            FileInputStream file = new FileInputStream(Constants.ABSOLUTE_EXCEL_FILE_PATH);
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet ws = wb.getSheetAt(0);
            Iterator<Row> rowIterator = ws.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                int lastCell = row.getLastCellNum();
                Person person = new Person();
                for(int i=0;i<lastCell;++i){
                    List<Address> addresses = new ArrayList<>();
                    person.setName(row.getCell(0).getStringCellValue().trim());
                    person.setEmail(row.getCell(1).getStringCellValue().trim());
                    person.setPhoneNum(String.valueOf(row.getCell(2).getNumericCellValue()).trim());
                    if(i>2) {
                        Address address = new Address();
                        String addressString = row.getCell(i).getStringCellValue();
                        String[] arr = addressString.split(",");
                        for(int j=0;j<arr.length;++j){
                            address.setStreet(arr[0]);
                            address.setState(arr[1]);
                            address.setPinCode(Long.valueOf(arr[2].trim()));
                        }
                        addresses.add(address);
                    }
                    person.setAddresses(addresses);
                    personDao.savePerson(person);
                }
            }
            file.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
