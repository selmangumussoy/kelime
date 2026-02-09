package com.software.mywordbox.library.abstraction;


import com.software.mywordbox.library.enums.MessageCodes;
import com.software.mywordbox.library.exception.CoreException;
import com.software.mywordbox.library.rest.AbstractEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
public abstract class AbstractServiceImpl<T extends AbstractEntity, D>
        implements AbstractService<D>{

    protected final AbstractRepository<T> repository;
    protected final AbstractEntityMapper<T, D> mapper;


    @Override
    public Page<D> getAllList(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::entityToDto);
    }

    @Override
    public D getById(String id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_ALREADY_EXISTS, getEntityName(), id.toString()));
        return mapper.entityToDto(entity);
    }//todo .ENTITY_NOT_FOUND message code yaz

    @Override
    public D create(D dto) {
        T entity = mapper.toEntity(dto);
        return mapper.entityToDto(repository.save(entity));
    }

    @Override
    public D update(String id, D dto) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_ALREADY_EXISTS, getEntityName(), id.toString()));

        updateEntityFields(entity, dto);

        return mapper.entityToDto(repository.save(entity));
    }

    @Override
    public void delete(String id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, getEntityName(), id));

        repository.delete(entity);
    }

    protected abstract String getEntityName();

    protected abstract void updateEntityFields(T entity, D dto);
}