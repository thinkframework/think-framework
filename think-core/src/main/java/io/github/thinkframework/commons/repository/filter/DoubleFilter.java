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

import java.util.List;

/**
 * Filter class for {@link Double} type attributes.
 *
 * @see RangeFilter
 */
public class DoubleFilter extends RangeFilter<Double> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for DoubleFilter.</p>
     */
    public DoubleFilter() {
    }

    public DoubleFilter(Double equals) {
        setEquals(equals);
    }

    /**
     * <p>Constructor for DoubleFilter.</p>
     *
     * @param filter a {@link DoubleFilter} object.
     */
    public DoubleFilter(final DoubleFilter filter) {
        super(filter);
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link DoubleFilter} object.
     */
    @Override
    public DoubleFilter copy() {
        return new DoubleFilter(this);
    }

    public DoubleFilter equals(Double equals) {
        super.setEquals(equals);
        return this;
    }

    public DoubleFilter notEquals(Double notEquals) {
        super.setNotEquals(notEquals);
        return this;
    }

    public DoubleFilter specified(Boolean specified) {
        super.setSpecified(specified);
        return this;
    }

    public DoubleFilter in(List<Double> in) {
        super.setIn(in);
        return this;
    }

    public DoubleFilter notIn(List<Double> notIn) {
        super.setNotIn(notIn);
        return this;
    }

    public DoubleFilter greaterThan(Double greaterThan) {
        super.setGreaterThan(greaterThan);
        return this;
    }

    public DoubleFilter lessThan(Double lessThan) {
        super.setLessThan(lessThan);
        return this;
    }

    public DoubleFilter greaterThanOrEqual(Double greaterThanOrEqual) {
        super.setGreaterThanOrEqual(greaterThanOrEqual);
        return this;
    }

    public DoubleFilter lessThanOrEqual(Double lessThanOrEqual) {
        super.setLessThanOrEqual(lessThanOrEqual);
        return this;
    }
}
