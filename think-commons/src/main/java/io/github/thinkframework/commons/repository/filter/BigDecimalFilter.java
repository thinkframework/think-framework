/*
 * Copyright 2016-2021 the original author or authors from the JHipster project.
 *
 * This file is part of the JHipster project, see https://www.jhipster.tech/
 * for more information.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.thinkframework.commons.repository.filter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Filter class for {@link BigDecimal} type attributes.
 *
 * @see RangeFilter
 */
public class BigDecimalFilter extends RangeFilter<BigDecimal> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for BigDecimalFilter.</p>
     */
    public BigDecimalFilter() {
    }

    public BigDecimalFilter(BigDecimal equals) {
        setEquals(equals);
    }

    /**
     * <p>Constructor for BigDecimalFilter.</p>
     *
     * @param filter a {@link BigDecimalFilter} object.
     */
    public BigDecimalFilter(final BigDecimalFilter filter) {
        super(filter);
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link BigDecimalFilter} object.
     */
    @Override
    public BigDecimalFilter copy() {
        return new BigDecimalFilter(this);
    }

    public BigDecimalFilter equals(BigDecimal equals) {
        super.setEquals(equals);
        return this;
    }

    public BigDecimalFilter notEquals(BigDecimal notEquals) {
        super.setNotEquals(notEquals);
        return this;
    }

    public BigDecimalFilter specified(Boolean specified) {
        super.setSpecified(specified);
        return this;
    }

    public BigDecimalFilter in(List<BigDecimal> in) {
        super.setIn(in);
        return this;
    }

    public BigDecimalFilter notIn(List<BigDecimal> notIn) {
        super.setNotIn(notIn);
        return this;
    }

    public BigDecimalFilter greaterThan(BigDecimal greaterThan) {
        super.setGreaterThan(greaterThan);
        return this;
    }

    public BigDecimalFilter lessThan(BigDecimal lessThan) {
        super.setLessThan(lessThan);
        return this;
    }

    public BigDecimalFilter greaterThanOrEqual(BigDecimal greaterThanOrEqual) {
        super.setGreaterThanOrEqual(greaterThanOrEqual);
        return this;
    }

    public BigDecimalFilter lessThanOrEqual(BigDecimal lessThanOrEqual) {
        super.setLessThanOrEqual(lessThanOrEqual);
        return this;
    }
}
