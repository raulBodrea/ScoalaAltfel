package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.SearchResult;

import java.util.List;

public class SearchResultImpl implements SearchResult {
    private String name;
    private List<WorkshopDescriptor> workshops;

    public SearchResultImpl(String name, List<WorkshopDescriptor> workshops) {
        this.name = name;
        this.workshops = workshops;
    }
    /**
     * The student name for which the search was made
     */
    public String getStudentName() {
        return this.name;
    }
    /**
     * The descriptions of the workshops found for the student
     */
    public List<WorkshopDescriptor> getWorkshops() {
        return this.workshops;
    }
}
