package io.github.thinkframework.commons.repository;

import io.github.thinkframework.commons.domain.AbstractAuditingEntity;
import io.github.thinkframework.util.IDUtil;
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

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @param <MAPPER>
 * @param <T>
 * @param <CRITERIA>
 * @param <ID>
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractRepository<MAPPER extends MybatisMapper<T, CRITERIA,ID>,T extends AbstractAuditingEntity<ID>,CRITERIA, ID> implements CriteriaRepository<T, CRITERIA, ID> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @NonNull
    protected MAPPER mapper;

    @Override
    public T create(T t) {
        t.setId((ID) IDUtil.getId());
        t.setCreatedBy((ID)Long.valueOf(0L));
        t.setCreatedDate(Instant.now());
        t.setLastModifiedBy((ID)Long.valueOf(0L));
        t.setLastModifiedDate(Instant.now());
        mapper.create(t);
        return mapper.findById(t.getId());
    }

    @Override
    public T update(T t) {;
        t.setLastModifiedBy((ID)Long.valueOf(0L));
        t.setLastModifiedDate(Instant.now());
        mapper.update(t);
        return mapper.findById(t.getId());
    }

    @Override
    public T save(T t) {
        if(t.getId() == null || existsById(t.getId())){
            return create(t);
        }else{
            if(existsById(t.getId())){
                return create(t);
            }else{
                return update(t);
            }
        }
    }

    @Override
    public List<T> saveAll(List<T> iterable) {
        return iterable
            .stream().map(this::save)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(mapper.findById(id));
    }

    @Override
    public boolean existsById(ID id) {
        return mapper.existsById(id) > 0;
    }

    @Override
    public List<T> findAll() {
        return mapper.findAll();
    }

    @Override
    public List<T> findAllByCriteria(CRITERIA criteria) {
        return mapper.findAllByCriteria(criteria);
    }


    @Override
    public List<T> findAllByCriteria(CRITERIA criteria, Sort sort) {
        return mapper.findAllByCriteria(criteria);
    }

    @Override
    public Page<T> findAllByCriteria(CRITERIA criteria, Pageable pageable) {
        return new PageImpl<>(findAllByCriteria(criteria),pageable,count(criteria));
    }

    @Override
    public List<T> findAllById(List<ID> iterable) {
        return iterable.stream().map(this::findById)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return mapper.count();
    }


    @Override
    public long count(CRITERIA criteria) {
        return mapper.count(criteria);
    }

    @Override
    public void deleteById(ID id) {
        mapper.deleteById(id);
    }

    @Override
    public void delete(T t) {
        mapper.deleteById(t.getId());
    }

    @Override
    public void deleteAll(List<? extends T> iterable) {
        iterable.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        mapper.deleteAll();
    }

    public MAPPER getMapper() {
        return mapper;
    }

    public void setMapper(MAPPER mapper) {
        this.mapper = mapper;
    }
}
