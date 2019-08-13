package com.katsubo.finaltask.util.page;

import com.katsubo.finaltask.entity.Event;

import java.util.List;

/**
 * Class to calculating pages with events
 */
public class EventPagination implements Pagination<Event>{
    private final Integer NOTES_PER_PAGE;
    private int countOfPages;
    private int page;

    /**
     * Instantiates a new Event pagination.
     *
     * @param notesPerPage number of notes that show by one page
     */
    public EventPagination(int notesPerPage) {
        NOTES_PER_PAGE = notesPerPage;
    }

    /**
     * @param items list of items
     * @param page the number of page
     * @return sublist of items that shown on page
     */
    @Override
    public List<Event> getPage(List<Event> items, int page) {
        int rez = items.size() / NOTES_PER_PAGE;
        if (items.size() % NOTES_PER_PAGE != 0) {
            countOfPages = ++rez;
        } else {
            countOfPages = rez;
        }
        if (page > 0 && page <= countOfPages){
            this.page = page;
            return findItems(items, page);
        } else {
            this.page = 1;
            return findItems(items, 1);
        }

    }

    /**
     * @return common number of pages
     */
    @Override
    public int getCountOfPages() {
        return countOfPages;
    }

    /**
     * @return page
     */
    @Override
    public int getPage() {
        return page;
    }

    private List<Event> findItems(List<Event> items, int page){
        if (items.size() > page * NOTES_PER_PAGE){
            return items.subList((page - 1) * NOTES_PER_PAGE, page * NOTES_PER_PAGE);
        } else {
            return items.subList((page - 1) * NOTES_PER_PAGE, items.size());
        }
    }
}
