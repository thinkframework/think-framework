package io.github.thinkframework.commons.repository;

import io.github.thinkframework.commons.domain.AbstractAuditingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 *
 * @param <ENTITY>
 * @param <CRITERIA>
 * @param <ID>
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public interface MybatisMapper<ENTITY extends AbstractAuditingEntity<ID>, CRITERIA, ID> {

    long create(ENTITY entity);

    long update(ENTITY entity);

    <S extends ENTITY> S save(S entity);

    <S extends ENTITY> List<S> saveAll(List<S> iterable);

    ENTITY findById(ID id);

    long existsById(ID id);

    List<ENTITY> findAll();

    List<ENTITY> findAllByCriteria(CRITERIA criteria);

    List<ENTITY> findAllByCriteria(CRITERIA criteria, Sort sort);

    List<ENTITY> findAllByCriteria(CRITERIA criteria, Pageable pageable);

    List<ENTITY> findAllById(List<ID> iterable);

    long count();

    long count(CRITERIA criteria);

    void deleteById(ID var1);

    void delete(ENTITY entity);

    void deleteAll(List<? extends ENTITY> iterable);

    void deleteAll();
}

