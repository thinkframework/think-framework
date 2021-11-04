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

    private Long id;

    private Long createdBy;

    private Instant createdDate;// = Instant.now();

    private Long lastModifiedBy;

    private Instant lastModifiedDate;// = Instant.now();

}
