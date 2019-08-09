package com.katsubo.finaltask.util.page;

import java.util.List;

public interface Pagination<T> {
    List<T> getPage(List<T> items, int page);

    int getCountOfPages();

    int getPage();
}
