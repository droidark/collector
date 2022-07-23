package com.comixtorm.collector.util;

import org.springframework.data.domain.Pageable;

public class Utilities {
    public static long getSkip(Pageable pageable) {
        return pageable.getPageNumber() > 0 ? pageable.getPageNumber() * pageable.getPageSize() : 0;
    }
}
