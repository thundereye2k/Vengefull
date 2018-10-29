/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections4.functors;

import java.io.Serializable;

import org.apache.commons.collections4.Predicate;

/**
 * Predicate implementation that returns true if the input is null.
 *
 * @since 3.0
 * @version $Id: NullPredicate.java 1543950 2013-11-20 21:13:35Z tn $
 */
public final class NullPredicate<T> implements Predicate<T>, Serializable {

	/** Serial version UID */
	private static final long serialVersionUID = 7533784454832764388L;

	/** Singleton predicate instance */
	@SuppressWarnings("rawtypes")
	public static final Predicate INSTANCE = new NullPredicate<Object>();

	/**
	 * Factory returning the singleton instance.
	 *
	 * @param <T>
	 *            the type that the predicate queries
	 * @return the singleton instance
	 * @since 3.1
	 */
	@SuppressWarnings("unchecked")
	public static <T> Predicate<T> nullPredicate() {
		return INSTANCE;
	}

	/**
	 * Restricted constructor.
	 */
	private NullPredicate() {
		super();
	}

	/**
	 * Evaluates the predicate returning true if the input is null.
	 *
	 * @param object
	 *            the input object
	 * @return true if input is null
	 */
	@Override
	public boolean evaluate(final T object) {
		return object == null;
	}

	private Object readResolve() {
		return INSTANCE;
	}

}
