package io.github.thinkframework.commons.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * 实体的基础抽象类，这些实体将保存关于创建，上次修改和创建，最后按日期修改的定义。
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class AbstractAuditingDTO<ID> implements DTO {

    private static final long serialVersionUID = 1L;

    /**
     * 后端返回的ID
     * 转换成string
     * 解决postman精度丢失的问题
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(description = "ID")
    private ID id;

    @Schema(description = "创建人")
    @JsonIgnoreProperties
    @JsonIgnore
    private ID createdBy;

    @Schema(description = "创建时间")
    @JsonIgnoreProperties
    @JsonIgnore
    private Instant createdDate;

    @Schema(description = "最后修改人")
    @JsonIgnoreProperties
    @JsonIgnore
    private ID lastModifiedBy;

    @Schema(description = "最后修改时间")
    @JsonIgnoreProperties
    @JsonIgnore
    private Instant lastModifiedDate;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(ID createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public ID getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(ID lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

//    public static class CREATED{
//
//    }
//
//    public static class UPDATE{
//
//    }
}
