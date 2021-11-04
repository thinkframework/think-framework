package io.github.thinkframework.commons.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @param <ENTITY>
 * @param <DTO>
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 * */
public interface Mapper <ENTITY extends io.github.thinkframework.commons.domain.Entity,
    DTO extends io.github.thinkframework.commons.service.dto.DTO>  {

    ENTITY toEntity(DTO dto);

    DTO toDto(ENTITY entity);

    void updateEntity(DTO dto,@MappingTarget ENTITY entity);

    void updateDto(ENTITY entity,@MappingTarget DTO dto);

    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ENTITY partialUpdateEntity(ENTITY dto,@MappingTarget ENTITY entity);

    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    DTO partialUpdateDto(DTO entity,@MappingTarget DTO dto);

    default List<ENTITY> toEntity(List<DTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    default List<DTO> toDto(List<ENTITY> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }


}
