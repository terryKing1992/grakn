/*
 * Grakn - A Distributed Semantic Database
 * Copyright (C) 2016-2018 Grakn Labs Limited
 *
 * Grakn is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Grakn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Grakn. If not, see <http://www.gnu.org/licenses/agpl.txt>.
 */

package ai.grakn.graql.answer;

import ai.grakn.concept.ConceptId;
import ai.grakn.graql.admin.Explanation;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * A type of {@link Answer} object that contains a {@link List}.
 */
public class ConceptList implements Answer<ConceptList>{

    private final List<ConceptId> list;
    private final Explanation explanation;

    public ConceptList(List<ConceptId> list) {
        this(list, null);
    }

    public ConceptList(List<ConceptId> list, Explanation explanation) {
        this.list = ImmutableList.copyOf(list);
        this.explanation = explanation;
    }

    @Override
    public ConceptList get() {
        return this;
    }

    @Override
    public Explanation explanation() {
        return explanation;
    }

    public List<ConceptId> list() {
        return list;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ConceptList a2 = (ConceptList) obj;
        return this.list.equals(a2.list);
    }

    @Override
    public int hashCode(){
        return list.hashCode();
    }
}
