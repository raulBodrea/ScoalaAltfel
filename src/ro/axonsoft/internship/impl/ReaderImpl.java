package ro.axonsoft.internship.impl;

import sun.security.krb5.internal.crypto.Des;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderImpl implements ro.axonsoft.internship.api.Reader {



    /**
     * Reads a file that contains information.
     * @param filename the name of the file.
     * @return a list of descriptions with the information parsed from the file
     */
    public List<Descriptor> readFile(final String filename){
        List<Descriptor> repo = new ArrayList<>();
        Path path = Paths.get(filename);
        List<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(path);
            while(scanner.hasNextLine()){
                lines.add(scanner.nextLine());
            }
        }
        catch(IOException ex){
            System.out.println("Exception: File not found.");
        }

        for(String x: lines){
            try {
                repo.add((Descriptor) readLine(x));
            }
            catch (ReaderException e){
                System.out.println(e);
            }
        }
        return repo;
    }



    /**
     * Reads a single line from the file.
     * @param line the line to read
     * @return a description
     * @throws ReaderException
     */
    public Object readLine(String line) throws ReaderException {
        String[] element = line.split(";");
        try{
            if(!element[1].contains(":")){
                String name = element[0];
                String topic = element[1];
                String classroom = element[2];
                String startTime = element[3];
                int length = Integer.parseInt(element[4]);
                return new WorkshopDescriptor(name, topic, classroom, startTime, length);
            }
            else{
                String name = element[0];
                String startTime = element[1];
                String endTime = element[2];
                String[] interests = new String[(element.length-3)];
                if(element.length > 3){
                    int temp = 0;
                    for(int i = 3; i<element.length; i++){
                        interests[temp] = element[i];
                        temp++;
                    }
                }
                return new StudentDescriptor(name, startTime, endTime, interests);
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new ReaderException("The row does not fit either descriptors.");
        }
    }

}
