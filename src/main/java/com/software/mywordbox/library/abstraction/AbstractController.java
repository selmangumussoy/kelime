package com.software.mywordbox.library.abstraction;


import com.software.mywordbox.library.rest.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
public abstract class AbstractController<D, Req, Res> extends BaseController {

    protected final AbstractService<D> service;
    protected final AbstractWebMapper<D, Req, Res> mapper;


    @GetMapping("/page")
    public Response<PageResponse<Res>> getAllList(Pageable pageable) {
        Page<Res> page = service.getAllList(pageable)
                .map(mapper::toResponse);
        return respond(page);
    }

    @GetMapping("/{id}")
    public Response<Res> getById(@PathVariable String id) {
        return respond(mapper.toResponse(service.getById(id)));
    }

    @PostMapping
    public Response<Res> create(@RequestBody @Valid Req request) {
        D dto = service.create(mapper.requestToDto(request));
        return respond(mapper.toResponse(dto));
    }

    @PutMapping("/{id}")
    public Response<Res> update(@PathVariable String id, @RequestBody @Valid Req request) {
        D dto = service.update(id, mapper.requestToDto(request));
        return respond(mapper.toResponse(dto));
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }
}