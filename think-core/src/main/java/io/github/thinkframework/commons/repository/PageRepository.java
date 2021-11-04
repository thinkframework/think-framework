package io.github.thinkframework.commons.repository;

import io.github.thinkframework.commons.domain.AbstractAuditingEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @param <T>
 * @param <CRITERIA>
 * @param <ID>
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 * */
public interface PageRepository<T extends AbstractAuditingEntity<ID>, CRITERIA, ID> extends CrudRepository<T,ID>{

    List<T> findAllByCriteria(Pageable pageable);
}

