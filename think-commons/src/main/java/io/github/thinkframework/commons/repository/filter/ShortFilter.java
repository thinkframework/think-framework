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
 * Filter class for {@link Short} type attributes.
 *
 * @see RangeFilter
 */
public class ShortFilter extends RangeFilter<Short> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for ShortFilter.</p>
     */
    public ShortFilter() {
    }

    public ShortFilter(Short equals) {
        setEquals(equals);
    }

    /**
     * <p>Constructor for ShortFilter.</p>
     *
     * @param filter a {@link ShortFilter} object.
     */
    public ShortFilter(final ShortFilter filter) {
        super(filter);
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link ShortFilter} object.
     */
    @Override
    public ShortFilter copy() {
        return new ShortFilter(this);
    }

    public ShortFilter equals(Short equals) {
        super.setEquals(equals);
        return this;
    }

    public ShortFilter notEquals(Short notEquals) {
        super.setNotEquals(notEquals);
        return this;
    }

    public ShortFilter specified(Boolean specified) {
        super.setSpecified(specified);
        return this;
    }

    public ShortFilter in(List<Short> in) {
        super.setIn(in);
        return this;
    }

    public ShortFilter notIn(List<Short> notIn) {
        super.setNotIn(notIn);
        return this;
    }

    public ShortFilter greaterThan(Short greaterThan) {
        super.setGreaterThan(greaterThan);
        return this;
    }

    public ShortFilter lessThan(Short lessThan) {
        super.setLessThan(lessThan);
        return this;
    }

    public ShortFilter greaterThanOrEqual(Short greaterThanOrEqual) {
        super.setGreaterThanOrEqual(greaterThanOrEqual);
        return this;
    }

    public ShortFilter lessThanOrEqual(Short lessThanOrEqual) {
        super.setLessThanOrEqual(lessThanOrEqual);
        return this;
    }
}
