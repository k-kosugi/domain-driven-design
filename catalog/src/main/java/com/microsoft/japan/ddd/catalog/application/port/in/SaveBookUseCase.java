package com.microsoft.japan.ddd.catalog.application.port.in;

import com.microsoft.japan.ddd.catalog.application.dto.BookDTO;

public interface SaveBookUseCase {
    void saveBook(BookDTO bookDTO);
}
