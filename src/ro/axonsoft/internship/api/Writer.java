package ro.axonsoft.internship.api;

import ro.axonsoft.internship.impl.SearchResultImpl;

import java.util.List;

public interface Writer {
    /**
     * Writes the result information for each student
     */
    void writeResult(final List<SearchResultImpl> results);
}
