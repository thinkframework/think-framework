package io.github.thinkframework.commons.repository;

import io.github.thinkframework.commons.domain.AbstractAuditingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 *
 * @param <T>
 * @param <CRITERIA>
 * @param <ID>
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CriteriaRepository<T extends AbstractAuditingEntity<ID>, CRITERIA, ID> extends CrudRepository<T,ID>{

    /**
     * 条件查询
     * @param criteria
     * @return
     */
    long count(CRITERIA criteria);

    /**
     * 条件查询
     * @param criteria
     * @return
     */
    List<T> findAllByCriteria(CRITERIA criteria);


    /**
     * 分页条件查询
     * @param criteria
     * @param sort
     * @return
     */
    List<T> findAllByCriteria(CRITERIA criteria, Sort sort);

    /**
     * 分页条件查询
     * @param criteria
     * @param pageable
     * @return
     */
    Page<T> findAllByCriteria(CRITERIA criteria, Pageable pageable);
}

