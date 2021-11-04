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
import java.util.Objects;

/**
 * Class for filtering attributes with {@link String} type.
 * It can be added to a criteria class as a member, to support the following query parameters:
 * <code>
 * fieldName.equals='something'
 * fieldName.notEquals='something'
 * fieldName.specified=true
 * fieldName.specified=false
 * fieldName.in='something','other'
 * fieldName.notIn='something','other'
 * fieldName.contains='thing'
 * fieldName.notContain='thing'
 * </code>
 */
public class StringFilter extends Filter<String> {

    private static final long serialVersionUID = 1L;

    private String contains;
    private String notContain;

    /**
     * <p>Constructor for StringFilter.</p>
     */
    public StringFilter() {
    }

    public StringFilter(String equals) {
        setEquals(equals);
    }

    /**
     * <p>Constructor for StringFilter.</p>
     *
     * @param filter a {@link StringFilter} object.
     */
    public StringFilter(final StringFilter filter) {
        super(filter);
        this.contains = filter.contains;
        this.notContain = filter.notContain;
    }

    /** {@inheritDoc} */
    @Override
    public StringFilter copy() {
        return new StringFilter(this);
    }

    /**
     * <p>Getter for the field <code>contains</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getContains() {
        return contains;
    }

    /**
     * <p>Setter for the field <code>contains</code>.</p>
     *
     * @param contains a {@link String} object.
     * @return a {@link StringFilter} object.
     */
    public StringFilter setContains(String contains) {
        this.contains = contains;
        return this;
    }

    /**
     * <p>Getter for the field <code>notContain</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getNotContain() {
        return notContain;
    }

    /**
     * <p>Setter for the field <code>notContain</code>.</p>
     *
     * @param notContain a {@link String} object.
     * @return a {@link StringFilter} object.
     */
    public StringFilter setNotContain(String notContain) {
        this.notContain = notContain;
        return this;
    }

    public StringFilter equals(String equals) {
        super.setEquals(equals);
        return this;
    }

    public StringFilter notEquals(String notEquals) {
        super.setNotEquals(notEquals);
        return this;
    }

    public StringFilter specified(Boolean specified) {
        super.setSpecified(specified);
        return this;
    }

    public StringFilter in(List<String> in) {
        super.setIn(in);
        return this;
    }

    public StringFilter notIn(List<String> notIn) {
        super.setNotIn(notIn);
        return this;
    }

    public StringFilter contains(String contains) {
        this.setContains(contains);
        return this;
    }

    public StringFilter notContains(String notContain) {
        this.setNotContain(notContain);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final StringFilter that = (StringFilter) o;
        return Objects.equals(contains, that.contains) &&
            Objects.equals(notContain, that.notContain);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contains, notContain);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getFilterName() + " ["
            + (getEquals() != null ? "equals=" + getEquals() + ", " : "")
            + (getNotEquals() != null ? "notEquals=" + getNotEquals() + ", " : "")
            + (getSpecified() != null ? "specified=" + getSpecified() + ", " : "")
            + (getIn() != null ? "in=" + getIn() + ", " : "")
            + (getNotIn() != null ? "notIn=" + getNotIn() + ", " : "")
            + (getContains() != null ? "contains=" + getContains() + ", " : "")
            + (getNotContain() != null ? "notContain=" + getNotContain() : "")
            + "]";
    }

}
