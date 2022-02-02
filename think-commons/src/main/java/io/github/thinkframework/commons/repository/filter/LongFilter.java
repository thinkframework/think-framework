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
 * Filter class for {@link Long} type attributes.
 *
 * @see RangeFilter
 */
public class LongFilter extends RangeFilter<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for LongFilter.</p>
     */
    public LongFilter() {
    }

    public LongFilter(Long equals) {
        setEquals(equals);
    }

    /**
     * <p>Constructor for LongFilter.</p>
     *
     * @param filter a {@link LongFilter} object.
     */
    public LongFilter(final LongFilter filter) {
        super(filter);
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link LongFilter} object.
     */
    @Override
    public LongFilter copy() {
        return new LongFilter(this);
    }

    public LongFilter equals(Long equals) {
        super.setEquals(equals);
        return this;
    }

    public LongFilter notEquals(Long notEquals) {
        super.setNotEquals(notEquals);
        return this;
    }

    public LongFilter specified(Boolean specified) {
        super.setSpecified(specified);
        return this;
    }

    public LongFilter in(List<Long> in) {
        super.setIn(in);
        return this;
    }

    public LongFilter notIn(List<Long> notIn) {
        super.setNotIn(notIn);
        return this;
    }

    public LongFilter greaterThan(Long greaterThan) {
        super.setGreaterThan(greaterThan);
        return this;
    }

    public LongFilter lessThan(Long lessThan) {
        super.setLessThan(lessThan);
        return this;
    }

    public LongFilter greaterThanOrEqual(Long greaterThanOrEqual) {
        super.setGreaterThanOrEqual(greaterThanOrEqual);
        return this;
    }

    public LongFilter lessThanOrEqual(Long lessThanOrEqual) {
        super.setLessThanOrEqual(lessThanOrEqual);
        return this;
    }
}
