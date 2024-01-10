package io.github.thinkframework.commons.web.rest;

import io.github.thinkframework.commons.repository.criteria.AbstractCriteria;
import io.github.thinkframework.commons.service.CriteriaService;
import io.github.thinkframework.commons.service.dto.AbstractAuditingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 支持条件查询的资源
 * @param <SERVICE>
 * @param <DTO>
 * @param <CRITERIA>
 * @param <ID>
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractRestResource<SERVICE extends CriteriaService<DTO,CRITERIA,ID>,
    DTO extends AbstractAuditingDTO<ID>, CRITERIA extends AbstractCriteria, ID> extends AbstractCrudRestResource<SERVICE, DTO, ID> implements CrudResource<DTO, ID>, CriteriaResource<DTO, CRITERIA, ID> {

    /**
     * 查找
     * 迭代器同事支持返回List和Page对象
     * tips: swagger不支持生成Iterable类型的文档
     * @param criteria id
     * @return 迭代器
     */
    @GetMapping
    @Override
    public ResponseEntity<Iterable<DTO>> findAll(CRITERIA criteria) {
        if (criteria.isUnpaged()){
            return ResponseEntity.ok()
                .body(service.findAllByCriteria(criteria));
        }
        // fixme 想好了再实现
        return ResponseEntity.ok()
            .body(service.findAllByCriteria(criteria,criteria.paged()));
    }

}
