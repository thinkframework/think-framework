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
 * Filter class for {@link Float} type attributes.
 *
 * @see RangeFilter
 */
public class FloatFilter extends RangeFilter<Float> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for FloatFilter.</p>
     */
    public FloatFilter() {
    }

    public FloatFilter(Float equals) {
        setEquals(equals);
    }

    /**
     * <p>Constructor for FloatFilter.</p>
     *
     * @param filter a {@link FloatFilter} object.
     */
    public FloatFilter(final FloatFilter filter) {
        super(filter);
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link FloatFilter} object.
     */
    @Override
    public FloatFilter copy() {
        return new FloatFilter(this);
    }

    public FloatFilter equals(Float equals) {
        super.setEquals(equals);
        return this;
    }

    public FloatFilter notEquals(Float notEquals) {
        super.setNotEquals(notEquals);
        return this;
    }

    public FloatFilter specified(Boolean specified) {
        super.setSpecified(specified);
        return this;
    }

    public FloatFilter in(List<Float> in) {
        super.setIn(in);
        return this;
    }

    public FloatFilter notIn(List<Float> notIn) {
        super.setNotIn(notIn);
        return this;
    }

    public FloatFilter greaterThan(Float greaterThan) {
        super.setGreaterThan(greaterThan);
        return this;
    }

    public FloatFilter lessThan(Float lessThan) {
        super.setLessThan(lessThan);
        return this;
    }

    public FloatFilter greaterThanOrEqual(Float greaterThanOrEqual) {
        super.setGreaterThanOrEqual(greaterThanOrEqual);
        return this;
    }

    public FloatFilter lessThanOrEqual(Float lessThanOrEqual) {
        super.setLessThanOrEqual(lessThanOrEqual);
        return this;
    }
}
