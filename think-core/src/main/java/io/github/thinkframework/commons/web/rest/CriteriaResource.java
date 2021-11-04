package io.github.thinkframework.commons.web.rest;

import io.github.thinkframework.commons.service.dto.AbstractAuditingDTO;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 条件查询资源
 * @param <DTO> 数据传输对象
 * @param <CRITERIA> 查询条件
 * @param <ID> ID
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CriteriaResource<DTO extends AbstractAuditingDTO<ID>, CRITERIA, ID> extends CrudResource<DTO, ID> {

    /**
     * 条件查询
     *
     * Page和List都实现了Iterable接口
     * @see org.springframework.data.domain.Page
     * @see java.util.List
     * @param criteria
     * @return
     */
    ResponseEntity<Iterable<DTO>> findAll(CRITERIA criteria);
}

