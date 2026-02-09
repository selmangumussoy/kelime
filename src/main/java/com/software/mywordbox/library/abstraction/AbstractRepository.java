package com.software.mywordbox.library.abstraction;

import com.software.mywordbox.library.rest.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, String> {
}
