package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.SearchResult;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class WriterImpl implements ro.axonsoft.internship.api.Writer {
    /**
     * Writes the result information for each student
     */
    public void writeResult(List<SearchResultImpl> results) {
        for(SearchResult result: results){
            String resultString = "";
            resultString+= "\n" + result.getStudentName();
            if (result.getWorkshops().size() > 0){
                for(WorkshopDescriptor workshop: result.getWorkshops()){
                    resultString+="\n\t\t\t\t\t\t"+workshop.getName();
                }
            }
            else {
                resultString+="\n\t\t\t\t\t\tNU PARTICIPA LA NICI UN WORKSHOP!";
            }
            String[] name = result.getStudentName().split(" ");
            String fileName = name[0].toLowerCase()+"_"+name[1].toLowerCase()+".txt";
            try{
                //System.out.println(resultString+"\n\n\n\n\n");
                Path file = Paths.get(fileName);
                Files.write(file, Collections.singleton(resultString), Charset.forName("UTF-8"));

            }
            catch (IOException ex){
                System.out.println(ex);
            }
        }
    }
}
