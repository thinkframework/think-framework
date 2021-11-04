package io.github.thinkframework.commons.service;

import io.github.thinkframework.commons.domain.AbstractAuditingEntity;
import io.github.thinkframework.commons.mapper.Mapper;
import io.github.thinkframework.commons.repository.CriteriaRepository;
import io.github.thinkframework.commons.service.dto.AbstractAuditingDTO;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @param <REPOSITORY>
 * @param <MAPPER>
 * @param <ENTITY>
 * @param <DTO>
 * @param <CRITERIA>
 * @param <ID>
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
@Transactional(rollbackFor = RuntimeException.class)
public abstract class AbstractService<REPOSITORY extends CriteriaRepository<ENTITY,CRITERIA,ID>, MAPPER extends Mapper<ENTITY,DTO>,
    ENTITY extends AbstractAuditingEntity<ID>,
    DTO extends AbstractAuditingDTO<ID>,
    CRITERIA, ID> implements CriteriaService<DTO , CRITERIA, ID> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @NonNull
    public REPOSITORY repository;

    @Autowired
    @NonNull
    public MAPPER mapper;


    @Override
    public DTO create(DTO dto) {
        return mapper.toDto(repository.create(mapper.toEntity(dto)));
    }

    @Override
    public DTO update(DTO dto) {
        return mapper.toDto(repository.update(mapper.toEntity(dto)));
    }


    @Override
    public DTO partialUpdate(DTO dto) {
        return mapper.toDto(repository.update(repository.findById(dto.getId())
            .map(e -> {
                mapper.partialUpdateEntity(mapper.toEntity(dto),e);
                return e;
            }).get()));
    }

    @Override
    public DTO save(DTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public List<DTO> saveAll(List<DTO> iterable) {
        return mapper.toDto(repository.saveAll(mapper.toEntity(iterable)));
    }

    @Override
    public Optional<DTO> findById(ID id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public List<DTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public List<DTO> findAllByCriteria(CRITERIA criteria) {
        return mapper.toDto(repository.findAllByCriteria(criteria));
    }

    @Override
    public List<DTO> findAllByCriteria(CRITERIA criteria, Sort sort) {
        return mapper.toDto(repository.findAllByCriteria(criteria,sort));
    }

    @Override
    public Page<DTO> findAllByCriteria(CRITERIA criteria, Pageable pageable) {
        return repository.findAllByCriteria(criteria,pageable)
            .map(mapper::toDto);
    }

    @Override
    public List<DTO> findAllById(List<ID> iterable) {
        return repository.findAllById(iterable)
            .stream().map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(DTO dto) {
        repository.delete(mapper.toEntity(dto));
    }

    @Override
    public void deleteAll(List<? extends DTO> iterable) {
        repository.deleteAll(iterable.stream().map(mapper::toEntity).collect(Collectors.toList()));
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    public REPOSITORY getRepository() {
        return repository;
    }

    public void setRepository(REPOSITORY repository) {
        this.repository = repository;
    }

    public MAPPER getMapper() {
        return mapper;
    }

    public void setMapper(MAPPER mapper) {
        this.mapper = mapper;
    }
}
