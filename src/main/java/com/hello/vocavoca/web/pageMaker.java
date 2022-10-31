package com.hello.vocavoca.web;

import lombok.Data;
import lombok.ToString;

@Data
public class pageMaker {

    private boolean hasPrev;
    private boolean hasNext;

    private int startPage;
    private int endPage;

    public pageMaker(int currentPage, int totalPages) {

        currentPage++;

        hasPrev = currentPage <= 1 ? false : true;
        hasNext = totalPages <= currentPage ? false : true;

        startPage = ((int) (currentPage-1) / 5) * 5 + 1;
        endPage = totalPages < startPage + 4 ? totalPages : startPage + 4;
    }
}
