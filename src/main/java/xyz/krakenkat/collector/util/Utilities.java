package xyz.krakenkat.collector.util;

import org.springframework.data.domain.Pageable;

public class Utilities {

    private Utilities() {}
    public static long getSkip(Pageable pageable) {
        return pageable.getPageNumber() > 0 ? pageable.getPageNumber() * pageable.getPageSize() : 0;
    }
}
