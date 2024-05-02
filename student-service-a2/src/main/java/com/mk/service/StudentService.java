package com.mk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mk.entity.Student;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private ResourceLoader resourceLoader;

    public String readJson() throws IOException {

        File f=new File("C:/MK-Microservices-IntlliJ/Student-a2");
        File[] files=f.listFiles();

        Writer writer=new PrintWriter("StudentCSV.csv");

        for (File f1:files){

        try {
            Resource resource = resourceLoader.getResource("file:"+f1.getAbsolutePath());
            InputStreamReader inputStreamReader=new InputStreamReader(resource.getInputStream());
            String file= FileCopyUtils.copyToString(inputStreamReader);

            ObjectMapper objectMapper=new ObjectMapper();
           Student student= objectMapper.readValue(file, Student.class);

            ColumnPositionMappingStrategy strategy=new ColumnPositionMappingStrategy();
            strategy.setType(Student.class);

            String[] columns={"firstName","lastName","clasS","address","marks","totalMarks","percentage"};

            strategy.setColumnMapping(columns);

            StatefulBeanToCsvBuilder<Student> csvBuilder=new StatefulBeanToCsvBuilder<>(writer);

            StatefulBeanToCsv<Student> beanToCsv=csvBuilder.withMappingStrategy(strategy).build();

           Integer totalMarks=totalMarks(student.getMarks());
           student.setTotalMarks(totalMarks);
           Double percentage=percentage(student.getMarks());
           student.setPercentage(percentage);

           List<Student> list=List.of(student);
            beanToCsv.write(list);


        } catch (Exception e) {
            return "Exception occured "+e;
        }
        }
        writer.close();
        return "Directory found";
    }


    private Integer totalMarks(Map<String, Integer> marks) {
        int total=0;
        for (int i: marks.values())
        {
            total += i;
        }
        return total;
    }

    private Double percentage(Map<String, Integer> marks){
        Double total=0.0;
        int count=0;
        for (int i: marks.values())
        {
            total += i;
            count++;
        }
        return total/count;

    }
}



