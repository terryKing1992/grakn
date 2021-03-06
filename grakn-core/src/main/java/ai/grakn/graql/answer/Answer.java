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

import ai.grakn.graql.Query;
import ai.grakn.graql.admin.Explanation;
import com.google.common.collect.Sets;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import java.util.Set;

/**
 * An object that contains the answer of every Graql {@link Query}
 * @param <T> the data structure in which the specific type of Answer is contained in.
 */
public interface Answer<T> {

    /**
     * @return a specific type of Answer, down-casted from this Answer class
     */
    @CheckReturnValue
    T get();

    /**
     * @return an explanation object indicating how this answer was obtained
     */
    @Nullable
    @CheckReturnValue
    default Explanation explanation() {
        return null;
    }

    /**
     * @return all explanations taking part in the derivation of this answer
     */
    @CheckReturnValue
    default Set<Explanation> explanations() {
        if (this.explanation() == null) return null;
        Set<Explanation> explanations = Sets.newHashSet(this.explanation());
        this.explanation().getAnswers().forEach(ans -> ans.explanations().forEach(explanations::add));
        return explanations;
    }
}
