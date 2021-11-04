package io.github.thinkframework.commons.service;

import io.github.thinkframework.commons.service.dto.AbstractAuditingDTO;

import java.util.List;
import java.util.Optional;

/**
 *
 * @param <DTO>
 * @param <ID>
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CrudService<DTO extends AbstractAuditingDTO<ID>, ID> {

    DTO create(DTO dto);

    DTO update(DTO dto);

    DTO partialUpdate(DTO dto);

    DTO save(DTO dto);

    List<DTO> saveAll(List<DTO> iterable);

    Optional<DTO> findById(ID id);

    boolean existsById(ID id);

    List<DTO> findAll();

    List<DTO> findAllById(List<ID> iterable);

    long count();

    void deleteById(ID id);

    void delete(DTO dto);

    void deleteAll(List<? extends DTO> var1);

    void deleteAll();

}

