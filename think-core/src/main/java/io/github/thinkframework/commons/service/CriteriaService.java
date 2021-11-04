package io.github.thinkframework.commons.service;

import io.github.thinkframework.commons.service.dto.AbstractAuditingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 *
 * @param <DTO>
 * @param <CRITERIA>
 * @param <ID>
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CriteriaService<DTO extends AbstractAuditingDTO<ID>,CRITERIA, ID> extends CrudService<DTO,ID> {
    /**
     *
     * @param criteria
     * @return
     */
    List<DTO> findAllByCriteria(CRITERIA criteria);

    /**
     *
     * @param criteria
     * @param sort
     * @return
     */
    List<DTO> findAllByCriteria(CRITERIA criteria, Sort sort);

    /**
     *
     * @param criteria
     * @param pageable
     * @return
     */
    Page<DTO> findAllByCriteria(CRITERIA criteria, Pageable pageable);

}

