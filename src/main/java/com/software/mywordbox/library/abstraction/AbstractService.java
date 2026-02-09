package com.software.mywordbox.library.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AbstractService<D> {
    Page<D> getAllList(Pageable pageable);
    D getById(String id);
    D create(D dto);
    D update(String id, D dto);
    void delete(String id);
}
