///////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2002, Eric D. Friedman All Rights Reserved.
// Copyright (c) 2009, Robert D. Eden All Rights Reserved.
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

package gnu.trove.decorator;

import gnu.trove.map.TLongLongMap;
import gnu.trove.iterator.TLongLongIterator;

import java.io.*;
import java.util.*;

//////////////////////////////////////////////////
// THIS IS A GENERATED CLASS. DO NOT HAND EDIT! //
//////////////////////////////////////////////////

/**
 * Wrapper class to make a TLongLongMap conform to the <tt>java.util.Map</tt>
 * API. This class simply decorates an underlying TLongLongMap and translates
 * the Object-based APIs into their Trove primitive analogs.
 * <p/>
 * Note that wrapping and unwrapping primitive values is extremely inefficient.
 * If possible, users of this class should override the appropriate methods in
 * this class and use a table of canonical values.
 * <p/>
 * Created: Mon Sep 23 22:07:40 PDT 2002
 *
 * @author Eric D. Friedman
 * @author Robert D. Eden
 * @author Jeff Randall
 */
public class TLongLongMapDecorator extends AbstractMap<Long, Long>
		implements Map<Long, Long>, Externalizable, Cloneable {

	static final long serialVersionUID = 1L;

	/** the wrapped primitive map */
	protected TLongLongMap _map;

	/**
	 * FOR EXTERNALIZATION ONLY!!
	 */
	public TLongLongMapDecorator() {
	}

	/**
	 * Creates a wrapper that decorates the specified primitive map.
	 *
	 * @param map
	 *            the <tt>TLongLongMap</tt> to wrap.
	 */
	public TLongLongMapDecorator(TLongLongMap map) {
		super();
		this._map = map;
	}

	/**
	 * Returns a reference to the map wrapped by this decorator.
	 *
	 * @return the wrapped <tt>TLongLongMap</tt> instance.
	 */
	public TLongLongMap getMap() {
		return _map;
	}

	/**
	 * Inserts a key/value pair into the map.
	 *
	 * @param key
	 *            an <code>Object</code> value
	 * @param value
	 *            an <code>Object</code> value
	 * @return the previous value associated with <tt>key</tt>, or Long(0) if none
	 *         was found.
	 */
	@Override
	public Long put(Long key, Long value) {
		long k;
		long v;
		if (key == null) {
			k = _map.getNoEntryKey();
		} else {
			k = unwrapKey(key);
		}
		if (value == null) {
			v = _map.getNoEntryValue();
		} else {
			v = unwrapValue(value);
		}
		long retval = _map.put(k, v);
		if (retval == _map.getNoEntryValue()) {
			return null;
		}
		return wrapValue(retval);
	}

	/**
	 * Retrieves the value for <tt>key</tt>
	 *
	 * @param key
	 *            an <code>Object</code> value
	 * @return the value of <tt>key</tt> or null if no such mapping exists.
	 */
	@Override
	public Long get(Object key) {
		long k;
		if (key != null) {
			if (key instanceof Long) {
				k = unwrapKey(key);
			} else {
				return null;
			}
		} else {
			k = _map.getNoEntryKey();
		}
		long v = _map.get(k);
		// There may be a false positive since primitive maps
		// cannot return null, so we have to do an extra
		// check here.
		if (v == _map.getNoEntryValue()) {
			return null;
		} else {
			return wrapValue(v);
		}
	}

	/**
	 * Empties the map.
	 */
	@Override
	public void clear() {
		this._map.clear();
	}

	/**
	 * Deletes a key/value pair from the map.
	 *
	 * @param key
	 *            an <code>Object</code> value
	 * @return the removed value, or null if it was not found in the map
	 */
	@Override
	public Long remove(Object key) {
		long k;
		if (key != null) {
			if (key instanceof Long) {
				k = unwrapKey(key);
			} else {
				return null;
			}
		} else {
			k = _map.getNoEntryKey();
		}
		long v = _map.remove(k);
		// There may be a false positive since primitive maps
		// cannot return null, so we have to do an extra
		// check here.
		if (v == _map.getNoEntryValue()) {
			return null;
		} else {
			return wrapValue(v);
		}
	}

	/**
	 * Returns a Set view on the entries of the map.
	 *
	 * @return a <code>Set</code> value
	 */
	@Override
	public Set<Map.Entry<Long, Long>> entrySet() {
		return new AbstractSet<Map.Entry<Long, Long>>() {
			@Override
			public int size() {
				return _map.size();
			}

			@Override
			public boolean isEmpty() {
				return TLongLongMapDecorator.this.isEmpty();
			}

			@Override
			public boolean contains(Object o) {
				if (o instanceof Map.Entry) {
					Object k = ((Map.Entry) o).getKey();
					Object v = ((Map.Entry) o).getValue();
					return TLongLongMapDecorator.this.containsKey(k) && TLongLongMapDecorator.this.get(k).equals(v);
				} else {
					return false;
				}
			}

			@Override
			public Iterator<Map.Entry<Long, Long>> iterator() {
				return new Iterator<Map.Entry<Long, Long>>() {
					private final TLongLongIterator it = _map.iterator();

					@Override
					public Map.Entry<Long, Long> next() {
						it.advance();
						long ik = it.key();
						final Long key = (ik == _map.getNoEntryKey()) ? null : wrapKey(ik);
						long iv = it.value();
						final Long v = (iv == _map.getNoEntryValue()) ? null : wrapValue(iv);
						return new Map.Entry<Long, Long>() {
							private Long val = v;

							@Override
							public boolean equals(Object o) {
								return o instanceof Map.Entry && ((Map.Entry) o).getKey().equals(key)
										&& ((Map.Entry) o).getValue().equals(val);
							}

							@Override
							public Long getKey() {
								return key;
							}

							@Override
							public Long getValue() {
								return val;
							}

							@Override
							public int hashCode() {
								return key.hashCode() + val.hashCode();
							}

							@Override
							public Long setValue(Long value) {
								val = value;
								return put(key, value);
							}
						};
					}

					@Override
					public boolean hasNext() {
						return it.hasNext();
					}

					@Override
					public void remove() {
						it.remove();
					}
				};
			}

			@Override
			public boolean add(Map.Entry<Long, Long> o) {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean remove(Object o) {
				boolean modified = false;
				if (contains(o)) {
					// noinspection unchecked
					Long key = ((Map.Entry<Long, Long>) o).getKey();
					_map.remove(unwrapKey(key));
					modified = true;
				}
				return modified;
			}

			@Override
			public boolean addAll(Collection<? extends Map.Entry<Long, Long>> c) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void clear() {
				TLongLongMapDecorator.this.clear();
			}
		};
	}

	/**
	 * Checks for the presence of <tt>val</tt> in the values of the map.
	 *
	 * @param val
	 *            an <code>Object</code> value
	 * @return a <code>boolean</code> value
	 */
	@Override
	public boolean containsValue(Object val) {
		return val instanceof Long && _map.containsValue(unwrapValue(val));
	}

	/**
	 * Checks for the present of <tt>key</tt> in the keys of the map.
	 *
	 * @param key
	 *            an <code>Object</code> value
	 * @return a <code>boolean</code> value
	 */
	@Override
	public boolean containsKey(Object key) {
		if (key == null)
			return _map.containsKey(_map.getNoEntryKey());
		return key instanceof Long && _map.containsKey(unwrapKey(key));
	}

	/**
	 * Returns the number of entries in the map.
	 *
	 * @return the map's size.
	 */
	@Override
	public int size() {
		return this._map.size();
	}

	/**
	 * Indicates whether map has any entries.
	 *
	 * @return true if the map is empty
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Copies the key/value mappings in <tt>map</tt> into this map. Note that this
	 * will be a <b>deep</b> copy, as storage is by primitive value.
	 *
	 * @param map
	 *            a <code>Map</code> value
	 */
	@Override
	public void putAll(Map<? extends Long, ? extends Long> map) {
		Iterator<? extends Entry<? extends Long, ? extends Long>> it = map.entrySet().iterator();
		for (int i = map.size(); i-- > 0;) {
			Entry<? extends Long, ? extends Long> e = it.next();
			this.put(e.getKey(), e.getValue());
		}
	}

	/**
	 * Wraps a key
	 *
	 * @param k
	 *            key in the underlying map
	 * @return an Object representation of the key
	 */
	protected Long wrapKey(long k) {
		return Long.valueOf(k);
	}

	/**
	 * Unwraps a key
	 *
	 * @param key
	 *            wrapped key
	 * @return an unwrapped representation of the key
	 */
	protected long unwrapKey(Object key) {
		return ((Long) key).longValue();
	}

	/**
	 * Wraps a value
	 *
	 * @param k
	 *            value in the underlying map
	 * @return an Object representation of the value
	 */
	protected Long wrapValue(long k) {
		return Long.valueOf(k);
	}

	/**
	 * Unwraps a value
	 *
	 * @param value
	 *            wrapped value
	 * @return an unwrapped representation of the value
	 */
	protected long unwrapValue(Object value) {
		return ((Long) value).longValue();
	}

	// Implements Externalizable
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

		// VERSION
		in.readByte();

		// MAP
		_map = (TLongLongMap) in.readObject();
	}

	// Implements Externalizable
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// VERSION
		out.writeByte(0);

		// MAP
		out.writeObject(_map);
	}

} // TLongLongHashMapDecorator