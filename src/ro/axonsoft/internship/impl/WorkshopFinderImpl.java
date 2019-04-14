package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.WorkshopFinder;

import java.util.ArrayList;
import java.util.List;

public class WorkshopFinderImpl implements WorkshopFinder {
    private int max = 0;                     //stores the maximum number of workshops the student attends to
    private List<WorkshopDescriptor> listOfCourses = new ArrayList<>();     //stores the workshops
    private ReaderImpl readerImpl = new ReaderImpl();
    private List<Descriptor> workshopsAsDescriptor = readerImpl.readFile("src\\ro\\axonsoft\\internship\\impl\\workshops.csv");

    /**
     * Iterates through all possible permutations of the workshops list and changes the class fields so that the most suitable permutation is always remembered
     * @param arr the array to permute through
     * @param index the current index (int that will change position)
     * @param studentDescriptor the student for which we are checking through the permutations
     */
    private void searchThroughAllPermutations (int[] arr, int index, StudentDescriptor studentDescriptor){
        if(index >= arr.length - 1){ //If we are at the last element - nothing left to permute
            int i;          //looper
            List<WorkshopDescriptor> attendsTo = new ArrayList<>();
            String classroom = null;


            //gets the student's start & leave time and then parsrs it as an int
            String[] stringStartTime = studentDescriptor.getStartTime().split(":");
            String[] stringEndTime = studentDescriptor.getEndTime().split(":");

            int[] startTime = new int[2];
            int[] endTime = new int[2];

            for(i = 0; i < 2; i++){
                startTime[i] = Integer.parseInt(stringStartTime[i]);
                endTime[i] = Integer.parseInt(stringEndTime[i]);
            }

            // adds the workshops to an array - in the order of the current permutation
            WorkshopDescriptor[] workshops = new WorkshopDescriptor[arr.length];
            for (i = 0; i < arr.length; i++) {
                workshops[i] = (WorkshopDescriptor) workshopsAsDescriptor.get(arr[i]);
            }
            for(WorkshopDescriptor workshop: workshops){                        //determines the max number of workshops a student is able to attend
                //If the student is interested in certain topics
                if(studentDescriptor.getInterests().length > 0){                //checks if the student has any specific interests
                    int found = 0;
                    for(String interest: studentDescriptor.getInterests()) {     //if yes - checks if the interests coincide with the current workshop's topic
                        if (interest.equals(workshop.getTopic())) {
                            found = 1;
                        }
                    }
                    if(found == 0) continue;
                    //get the workshop start time and parse it as ints
                }
                String[] workshopStringStartTime = workshop.getStartTime().split(":");
                int[] workshopStartTime  = new int[2];
                workshopStartTime[0] = Integer.parseInt(workshopStringStartTime[0]);
                workshopStartTime[1] = Integer.parseInt(workshopStringStartTime[1]);
                if(startTime[0] < workshopStartTime[0] && endTime[0] > workshopStartTime[0] || startTime[0] == workshopStartTime[0] && startTime[1] <= workshopStartTime[1] || endTime[0] == workshopStartTime[0] && startTime[1] <= workshopStartTime[1]){ //checks if the student is at school when the workshop starts
                    int[] workshopEndTime = new int[2];                                                             //stores the workshop closing time
                    workshopEndTime[1] = workshopStartTime[1] + workshop.getLength()%60;                            //determines te workshop closing time
                    if(classroom != null && !classroom.equals(workshop.getClassroom())) workshopEndTime[1] += 10;
                    workshopEndTime[0] = workshopStartTime[0]+workshop.getLength()/60+workshopEndTime[1]/60;
                    workshopEndTime[1] = workshopEndTime[1]%60;

                    if(endTime[0] > workshopEndTime[0] || endTime[0] == workshopEndTime[0] && endTime[1] >= workshopEndTime[1]){//if the workshop ends before the student leaves
                        attendsTo.add(workshop);                                                //add workshop to array
                        startTime = workshopEndTime;                                            //updates the student's start time
                        classroom = workshop.getClassroom();                                    //updates the classroom
                    }
                }
            }
            if(attendsTo.size() > max){                 //checks if this permutation allows student to attend to more workshops
                max = attendsTo.size();                 //updates max
                listOfCourses = attendsTo;              //updates the array
            }
            return;
        }
        //if we are not at the last element
        for(int i = index; i < arr.length; i++){ //For each index in the sub array arr[index...end]
            //Swap the elements at indices index and i
            int t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;

            //Recurse on the sub array arr[index+1...end]
            searchThroughAllPermutations(arr, index+1, studentDescriptor);

            //Swap the elements back
            t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;
        }
    }

    /**
     * Search the maximum set of workshops the student can attend
     * @param studentDescriptor the description of the student
     * @return the result of the search made for the student
     */
    public SearchResultImpl getWorkshops(StudentDescriptor studentDescriptor) {
        this.max = 0;                                                   //resets the value of max
        int[] arr = new int[workshopsAsDescriptor.size()];              //creates a new array of ints from 0 to the number of workshops - 1
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        searchThroughAllPermutations(arr, 0, studentDescriptor); //calls the recursive function
        return new SearchResultImpl(studentDescriptor.getName(), listOfCourses);//returns the SearchResult for this one student
    }
}
