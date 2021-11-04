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

import java.time.Duration;
import java.util.List;

/**
 * Filter class for {@link Duration} type attributes.
 *
 * @see Filter
 */
public class DurationFilter extends RangeFilter<Duration> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for DurationFilter.</p>
     */
    public DurationFilter() {
    }

    public DurationFilter(Duration equals) {
        setEquals(equals);
    }

    /**
     * <p>Constructor for DurationFilter.</p>
     *
     * @param filter a {@link DurationFilter} object.
     */
    public DurationFilter(final DurationFilter filter) {
        super(filter);
    }

    /**
     * <p>copy.</p>
     *
     * @return a {@link DurationFilter} object.
     */
    @Override
    public DurationFilter copy() {
        return new DurationFilter(this);
    }

    public DurationFilter equals(Duration equals) {
        super.setEquals(equals);
        return this;
    }

    public DurationFilter notEquals(Duration notEquals) {
        super.setNotEquals(notEquals);
        return this;
    }

    public DurationFilter specified(Boolean specified) {
        super.setSpecified(specified);
        return this;
    }

    public DurationFilter in(List<Duration> in) {
        super.setIn(in);
        return this;
    }

    public DurationFilter notIn(List<Duration> notIn) {
        super.setNotIn(notIn);
        return this;
    }

    public DurationFilter greaterThan(Duration greaterThan) {
        super.setGreaterThan(greaterThan);
        return this;
    }

    public DurationFilter lessThan(Duration lessThan) {
        super.setLessThan(lessThan);
        return this;
    }

    public DurationFilter greaterThanOrEqual(Duration greaterThanOrEqual) {
        super.setGreaterThanOrEqual(greaterThanOrEqual);
        return this;
    }

    public DurationFilter lessThanOrEqual(Duration lessThanOrEqual) {
        super.setLessThanOrEqual(lessThanOrEqual);
        return this;
    }
}
