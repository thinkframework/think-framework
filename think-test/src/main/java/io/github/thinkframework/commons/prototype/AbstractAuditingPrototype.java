package io.github.thinkframework.commons.prototype;

import java.time.*;

/**
 * 测试时用到的实体和数据源传对象的原型
 * 负责创建和更新实体和数据传输对象
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractAuditingPrototype {

    private static final long serialVersionUID = 1L;

    protected Long id;

    protected Long createdBy;

    protected Instant createdDate;// = Instant.now();

    protected Long lastModifiedBy;

    protected Instant lastModifiedDate;// = Instant.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
