package io.github.thinkframework.commons.web.rest;

import io.github.thinkframework.commons.service.CrudService;
import io.github.thinkframework.commons.service.dto.AbstractAuditingDTO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * 支持CRUD的资源
 * 和条件查询分离
 * tips: feignClient不支持条件查询
 * @param <SERVICE>
 * @param <DTO>
 * @param <ID>
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractCrudRestResource<SERVICE extends CrudService<DTO, ID>,
    DTO extends AbstractAuditingDTO<ID>, ID> implements CrudResource<DTO, ID> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected SERVICE service;

    /**
     * 创建
     * @param dto 数据传输对象
     * @return
     */
    @ApiOperation("创建")
    @PostMapping
    @Override
//    @Validated(AbstractAuditingDTO.CREATED.class)
    public ResponseEntity<DTO> create(@RequestBody DTO dto) {
        return ResponseEntity.created(null)
        .body(service.create(dto));
    }
    /**
     * 更新
     * @param dto 数据传输对象
     * @return
     */
    @ApiOperation("更新")
    @PutMapping("{id}")
    @Override
//    @Validated(AbstractAuditingDTO.UPDATE.class)
    public ResponseEntity<DTO> update(@PathVariable ID id, @RequestBody DTO dto) {
        return ResponseEntity.ok()
            .body(service.update(dto));
    }


    /**
     * 局部更新
     * @param dto 数据传输对象
     * @return
     */
    @ApiOperation("局部更新")
    @PatchMapping("{id}")
    @Override
//    @Validated(AbstractAuditingDTO.UPDATE.class)
    public ResponseEntity<DTO> partialUpdate(@PathVariable ID id, @RequestBody DTO dto) {
        return ResponseEntity.ok()
            .body(service.partialUpdate(dto));
    }

    /**
     * 查找
     * @param id id
     * @return
     */
    @ApiOperation("获取明细")
    @GetMapping("{id}")
    @Override
    public ResponseEntity<DTO> findById(@PathVariable ID id) {
        return service.findById(id)
            .map(dto -> ResponseEntity.ok().body(dto))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * 查找
     * @param id id
     * @return
     */
    @ApiOperation("删除")
    @DeleteMapping("{id}")
    @Override
    public ResponseEntity<Void> deleteById(@PathVariable ID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public SERVICE getService() {
        return service;
    }

    public void setService(SERVICE service) {
        this.service = service;
    }
}
