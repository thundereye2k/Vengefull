///////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2008, Robert D. Eden All Rights Reserved.
// Copyright (c) 2009, Jeff Randall All Rights Reserved.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
///////////////////////////////////////////////////////////////////////////////

package gnu.trove.impl.unmodifiable;

//////////////////////////////////////////////////
// THIS IS A GENERATED CLASS. DO NOT HAND EDIT! //
//////////////////////////////////////////////////

////////////////////////////////////////////////////////////
// THIS IS AN IMPLEMENTATION CLASS. DO NOT USE DIRECTLY!  //
// Access to these methods should be through TCollections //
////////////////////////////////////////////////////////////

import gnu.trove.iterator.*;
import gnu.trove.procedure.*;
import gnu.trove.set.*;
import gnu.trove.function.*;
import gnu.trove.map.*;
import gnu.trove.*;

import java.util.*;
import java.io.Serializable;

public class TUnmodifiableShortObjectMap<V> implements TShortObjectMap<V>, Serializable {
	private static final long serialVersionUID = -1034234728574286014L;

	private final TShortObjectMap<V> m;

	public TUnmodifiableShortObjectMap(TShortObjectMap<V> m) {
		if (m == null)
			throw new NullPointerException();
		this.m = m;
	}

	@Override
	public int size() {
		return m.size();
	}

	@Override
	public boolean isEmpty() {
		return m.isEmpty();
	}

	@Override
	public boolean containsKey(short key) {
		return m.containsKey(key);
	}

	@Override
	public boolean containsValue(Object val) {
		return m.containsValue(val);
	}

	@Override
	public V get(short key) {
		return m.get(key);
	}

	@Override
	public V put(short key, V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public V remove(short key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(TShortObjectMap<? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(Map<? extends Short, ? extends V> map) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	private transient TShortSet keySet = null;
	private transient Collection<V> values = null;

	@Override
	public TShortSet keySet() {
		if (keySet == null)
			keySet = TCollections.unmodifiableSet(m.keySet());
		return keySet;
	}

	@Override
	public short[] keys() {
		return m.keys();
	}

	@Override
	public short[] keys(short[] array) {
		return m.keys(array);
	}

	@Override
	public Collection<V> valueCollection() {
		if (values == null)
			values = Collections.unmodifiableCollection(m.valueCollection());
		return values;
	}

	@Override
	public Object[] values() {
		return m.values();
	}

	@Override
	public V[] values(V[] array) {
		return m.values(array);
	}

	@Override
	public boolean equals(Object o) {
		return o == this || m.equals(o);
	}

	@Override
	public int hashCode() {
		return m.hashCode();
	}

	@Override
	public String toString() {
		return m.toString();
	}

	@Override
	public short getNoEntryKey() {
		return m.getNoEntryKey();
	}

	@Override
	public boolean forEachKey(TShortProcedure procedure) {
		return m.forEachKey(procedure);
	}

	@Override
	public boolean forEachValue(TObjectProcedure<? super V> procedure) {
		return m.forEachValue(procedure);
	}

	@Override
	public boolean forEachEntry(TShortObjectProcedure<? super V> procedure) {
		return m.forEachEntry(procedure);
	}

	@Override
	public TShortObjectIterator<V> iterator() {
		return new TShortObjectIterator<V>() {
			TShortObjectIterator<V> iter = m.iterator();

			@Override
			public short key() {
				return iter.key();
			}

			@Override
			public V value() {
				return iter.value();
			}

			@Override
			public void advance() {
				iter.advance();
			}

			@Override
			public boolean hasNext() {
				return iter.hasNext();
			}

			@Override
			public V setValue(V val) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public V putIfAbsent(short key, V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void transformValues(TObjectFunction<V, V> function) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainEntries(TShortObjectProcedure<? super V> procedure) {
		throw new UnsupportedOperationException();
	}
}
