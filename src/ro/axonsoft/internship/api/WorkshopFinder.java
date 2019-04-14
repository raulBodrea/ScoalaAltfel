package ro.axonsoft.internship.api;

import ro.axonsoft.internship.impl.StudentDescriptor;

public interface WorkshopFinder {
    /**
     * Search the maximum set of workshops the student can attend
     * @param studentDescriptor the description of the student
     * @return the result of the search made for the student
     */
    SearchResult getWorkshops(StudentDescriptor studentDescriptor);
}

