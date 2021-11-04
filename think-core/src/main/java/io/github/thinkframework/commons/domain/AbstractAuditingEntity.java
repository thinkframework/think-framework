package io.github.thinkframework.commons.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * 实体的基础抽象类，这些实体将保存关于创建，上次修改和创建，最后按日期修改的定义。
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class AbstractAuditingEntity<ID> implements Entity {

    private static final long serialVersionUID = 1L;

    @Id
    private ID id;

    @CreatedBy
    private ID createdBy;

    @CreatedDate
    private Instant createdDate;// = Instant.now();

    @LastModifiedBy
    private ID lastModifiedBy;

    @LastModifiedDate
    private Instant lastModifiedDate;// = Instant.now();

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
}
