package com.learning.domains.utils;

import com.learning.apps.dtos.PageCustomDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class PageUtils<E> {
    public PageCustomDTO<E> getPage(List<E> contents, int page, int size, long totalElement, long totalPage) {
        return new PageCustomDTO<>(contents, page, size, totalElement, totalPage);
    }

    public PageCustomDTO<E> getPageFromList(List<E> contents, int page, int size, long totalElement) {
        int fromIndex = (page - 1) * size;
        List<E> pageList = new ArrayList<>();
        if (fromIndex <= contents.size()) {
            pageList = contents.subList(fromIndex, Math.min(fromIndex + size, contents.size()));
        }
        long totalPage = 0;
        if (totalElement % size == 0) {
            totalPage = totalElement / size;
        } else {
            totalPage = totalElement / size + 1;
        }
        return new PageCustomDTO<>(pageList, page, size, totalElement, totalPage);
    }
}
