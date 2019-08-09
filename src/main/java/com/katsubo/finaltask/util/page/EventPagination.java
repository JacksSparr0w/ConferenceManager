package com.katsubo.finaltask.util.page;

import com.katsubo.finaltask.entity.Event;

import java.util.List;

public class EventPagination implements Pagination<Event>{
    private final Integer NOTES_PER_PAGE;
    private long countOfPages;
    private int page;

    public EventPagination(int notesPerPage) {
        NOTES_PER_PAGE = notesPerPage;
    }

    @Override
    public List<Event> getPage(List<Event> items, int page) {
        countOfPages = Math.round(items.size() / NOTES_PER_PAGE.doubleValue());
        if (page > 0 && page <= countOfPages){
            this.page = page;
            return findItems(items, page);
        } else {
            this.page = 1;
            return findItems(items, 1);
        }

    }

    @Override
    public int getCountOfPages() {
        return (int) countOfPages;
    }

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
