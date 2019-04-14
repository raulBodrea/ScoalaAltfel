package ro.axonsoft.internship.main;
import ro.axonsoft.internship.impl.*;

import java.util.ArrayList;
import java.util.List;

public class Launcher {
    public static void main(String[] args){
        int i;
        ReaderImpl readerImpl = new ReaderImpl();
        WriterImpl writerImpl = new WriterImpl();

        WorkshopFinderImpl workshopFinderImpl = new WorkshopFinderImpl();

        List<Descriptor> sDescriptor = readerImpl.readFile("src\\ro\\axonsoft\\internship\\impl\\students.csv");
        List<StudentDescriptor> students = new ArrayList<>();

        for(i = 0; i < sDescriptor.size(); i++){
            students.add((StudentDescriptor) sDescriptor.get(i));
        }

        List<SearchResultImpl> results = new ArrayList<>();
        for(StudentDescriptor student: students){
            results.add(workshopFinderImpl.getWorkshops(student));
        }
        writerImpl.writeResult(results);

    }
}
