package io.github.thinkframework.commons.web.rest;

import io.github.thinkframework.commons.service.dto.AbstractAuditingDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * CRUD资源
 * @param <DTO>
 * @param <ID>
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CrudResource<DTO extends AbstractAuditingDTO<ID>, ID> {

    /**
     * 创建
     *
     * @param dto 数据传输对象
     * @return
     */
    @PostMapping
    ResponseEntity<DTO> create(@RequestBody DTO dto);

    /**
     * 更新
     *
     * @param id id
     * @param dto 数据传输对象
     * @return
     */
    @PutMapping("{id}")
    ResponseEntity<DTO> update(@PathVariable(name = "id") ID id, @RequestBody DTO dto);


    /**
     * 局部更新
     *
     * @param id id
     * @param dto 数据传输对象
     * @return
     */
    @PatchMapping("{id}")
    ResponseEntity<DTO> partialUpdate(@PathVariable(name = "id") ID id, @RequestBody DTO dto);


    /**
     * 查找
     *
     * @param id id
     * @return
     */
    @GetMapping("{id}")
    ResponseEntity<DTO> findById(@PathVariable(name = "id") ID id);

    /**
     * 删除
     *
     * @param id id
     * @return
     */
    @ApiOperation("删除")
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteById(@PathVariable(name = "id") ID id);
}

