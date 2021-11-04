package io.github.thinkframework.commons.service.creteria;

import org.springframework.data.domain.Pageable;

/**
 *
 * @author unascribed
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractCreteria implements Creteria {
    private Pageable pageable;
}
