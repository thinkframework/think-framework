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
 * Class for filtering attributes with {@link Boolean} type. It can be added to a criteria class as a member, to support
 * the following query parameters:
 * <pre>
 *      fieldName.equals=true
 *      fieldName.notEquals=true
 *      fieldName.specified=true
 *      fieldName.specified=false
 *      fieldName.in=true,false
 *      fieldName.notIn=true,false
 * </pre>
 */
public class BooleanFilter extends Filter<Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for BooleanFilter.</p>
     */
    public BooleanFilter() {
    }

    public BooleanFilter(Boolean equals) {
        setEquals(equals);
    }

    /**
     * <p>Constructor for BooleanFilter.</p>
     *
     * @param filter a {@link BooleanFilter} object.
     */
    public BooleanFilter(final BooleanFilter filter) {
        super(filter);
    }

    /** {@inheritDoc} */
    @Override
    public BooleanFilter copy() {
        return new BooleanFilter(this);
    }


    public BooleanFilter equals(Boolean equals) {
        super.setEquals(equals);
        return this;
    }

    public BooleanFilter notEquals(Boolean notEquals) {
        super.setNotEquals(notEquals);
        return this;
    }

    public BooleanFilter specified(Boolean specified) {
        super.setSpecified(specified);
        return this;
    }

    public BooleanFilter in(List<Boolean> in) {
        super.setIn(in);
        return this;
    }

    public BooleanFilter notIn(List<Boolean> notIn) {
        super.setNotIn(notIn);
        return this;
    }
}
