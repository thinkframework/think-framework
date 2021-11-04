package io.github.thinkframework.commons.repository;

import io.github.thinkframework.commons.domain.AbstractAuditingEntity;

import java.util.List;
import java.util.Optional;

/**
 * CRUD仓库
 * @param <ENTITY>
 * @param <ID>
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CrudRepository<ENTITY extends AbstractAuditingEntity<ID>, ID> {

    /**
     *
     * @param entity
     * @return
     */
    ENTITY create(ENTITY entity);

    /**
     *
     * @param entity
     * @return
     */
    ENTITY update(ENTITY entity);

    /**
     *
     * @param entity
     * @return
     */
    ENTITY save(ENTITY entity);

    /**
     *
     * @param iterable
     * @return
     */
    List<ENTITY> saveAll(List<ENTITY> iterable);

    /**
     *
     * @param id
     * @return
     */
    Optional<ENTITY> findById(ID id);

    /**
     *
     * @param id
     * @return
     */
    boolean existsById(ID id);

    /**
     *
     * @return
     */
    List<ENTITY> findAll();

    /**
     *
     * @param iterable
     * @return
     */
    List<ENTITY> findAllById(List<ID> iterable);

    /**
     *
     * @return
     */
    long count();

    /**
     *
     * @param id
     */
    void deleteById(ID id);

    /**
     *
     * @param entity
     */
    void delete(ENTITY entity);

    /**
     *
     * @param iterable
     */
    void deleteAll(List<? extends ENTITY> iterable);

    /**
     *
     */
    void deleteAll();
}

