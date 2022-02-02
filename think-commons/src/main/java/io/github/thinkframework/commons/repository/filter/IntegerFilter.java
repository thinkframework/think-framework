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
 * Filter class for {@link Integer} type attributes.
 *
 * @see RangeFilter
 */
public class IntegerFilter extends RangeFilter<Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for IntegerFilter.</p>
     */
    public IntegerFilter() {
    }

    public IntegerFilter(Integer equals) {
        setEquals(equals);
    }

    /**
     * <p>Constructor for IntegerFilter.</p>
     *
     * @param filter a {@link IntegerFilter} object.
     */
    public IntegerFilter(final IntegerFilter filter) {
        super(filter);
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link IntegerFilter} object.
     */
    @Override
    public IntegerFilter copy() {
        return new IntegerFilter(this);
    }

    public IntegerFilter equals(Integer equals) {
        super.setEquals(equals);
        return this;
    }

    public IntegerFilter notEquals(Integer notEquals) {
        super.setNotEquals(notEquals);
        return this;
    }

    public IntegerFilter specified(Boolean specified) {
        super.setSpecified(specified);
        return this;
    }

    public IntegerFilter in(List<Integer> in) {
        super.setIn(in);
        return this;
    }

    public IntegerFilter notIn(List<Integer> notIn) {
        super.setNotIn(notIn);
        return this;
    }

    public IntegerFilter greaterThan(Integer greaterThan) {
        super.setGreaterThan(greaterThan);
        return this;
    }

    public IntegerFilter lessThan(Integer lessThan) {
        super.setLessThan(lessThan);
        return this;
    }

    public IntegerFilter greaterThanOrEqual(Integer greaterThanOrEqual) {
        super.setGreaterThanOrEqual(greaterThanOrEqual);
        return this;
    }

    public IntegerFilter lessThanOrEqual(Integer lessThanOrEqual) {
        super.setLessThanOrEqual(lessThanOrEqual);
        return this;
    }
}
