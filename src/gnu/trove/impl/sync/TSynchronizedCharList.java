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

package gnu.trove.impl.sync;

//////////////////////////////////////////////////
// THIS IS A GENERATED CLASS. DO NOT HAND EDIT! //
//////////////////////////////////////////////////

////////////////////////////////////////////////////////////
// THIS IS AN IMPLEMENTATION CLASS. DO NOT USE DIRECTLY!  //
// Access to these methods should be through TCollections //
////////////////////////////////////////////////////////////

import gnu.trove.procedure.*;
import gnu.trove.list.*;
import gnu.trove.function.*;
import java.util.RandomAccess;
import java.util.Random;

public class TSynchronizedCharList extends TSynchronizedCharCollection implements TCharList {

	static final long serialVersionUID = -7754090372962971524L;

	final TCharList list;

	public TSynchronizedCharList(TCharList list) {
		super(list);
		this.list = list;
	}

	public TSynchronizedCharList(TCharList list, Object mutex) {
		super(list, mutex);
		this.list = list;
	}

	@Override
	public boolean equals(Object o) {
		synchronized (mutex) {
			return list.equals(o);
		}
	}

	@Override
	public int hashCode() {
		synchronized (mutex) {
			return list.hashCode();
		}
	}

	@Override
	public char get(int index) {
		synchronized (mutex) {
			return list.get(index);
		}
	}

	@Override
	public char set(int index, char element) {
		synchronized (mutex) {
			return list.set(index, element);
		}
	}

	@Override
	public void set(int offset, char[] values) {
		synchronized (mutex) {
			list.set(offset, values);
		}
	}

	@Override
	public void set(int offset, char[] values, int valOffset, int length) {
		synchronized (mutex) {
			list.set(offset, values, valOffset, length);
		}
	}

	@Override
	public char replace(int offset, char val) {
		synchronized (mutex) {
			return list.replace(offset, val);
		}
	}

	@Override
	public void remove(int offset, int length) {
		synchronized (mutex) {
			list.remove(offset, length);
		}
	}

	@Override
	public char removeAt(int offset) {
		synchronized (mutex) {
			return list.removeAt(offset);
		}
	}

	@Override
	public void add(char[] vals) {
		synchronized (mutex) {
			list.add(vals);
		}
	}

	@Override
	public void add(char[] vals, int offset, int length) {
		synchronized (mutex) {
			list.add(vals, offset, length);
		}
	}

	@Override
	public void insert(int offset, char value) {
		synchronized (mutex) {
			list.insert(offset, value);
		}
	}

	@Override
	public void insert(int offset, char[] values) {
		synchronized (mutex) {
			list.insert(offset, values);
		}
	}

	@Override
	public void insert(int offset, char[] values, int valOffset, int len) {
		synchronized (mutex) {
			list.insert(offset, values, valOffset, len);
		}
	}

	@Override
	public int indexOf(char o) {
		synchronized (mutex) {
			return list.indexOf(o);
		}
	}

	@Override
	public int lastIndexOf(char o) {
		synchronized (mutex) {
			return list.lastIndexOf(o);
		}
	}

	// public TListCharIterator listIterator() {
	// return list.listIterator(); // Must be manually synched by user
	// }

	// public TListCharIterator listIterator( int index ) {
	// return list.listIterator( index ); // Must be manually synched by user
	// }

	@Override
	public TCharList subList(int fromIndex, int toIndex) {
		synchronized (mutex) {
			return new TSynchronizedCharList(list.subList(fromIndex, toIndex), mutex);
		}
	}

	@Override
	public char[] toArray(int offset, int len) {
		synchronized (mutex) {
			return list.toArray(offset, len);
		}
	}

	@Override
	public char[] toArray(char[] dest, int offset, int len) {
		synchronized (mutex) {
			return list.toArray(dest, offset, len);
		}
	}

	@Override
	public char[] toArray(char[] dest, int source_pos, int dest_pos, int len) {
		synchronized (mutex) {
			return list.toArray(dest, source_pos, dest_pos, len);
		}
	}

	@Override
	public int indexOf(int offset, char value) {
		synchronized (mutex) {
			return list.indexOf(offset, value);
		}
	}

	@Override
	public int lastIndexOf(int offset, char value) {
		synchronized (mutex) {
			return list.lastIndexOf(offset, value);
		}
	}

	@Override
	public void fill(char val) {
		synchronized (mutex) {
			list.fill(val);
		}
	}

	@Override
	public void fill(int fromIndex, int toIndex, char val) {
		synchronized (mutex) {
			list.fill(fromIndex, toIndex, val);
		}
	}

	@Override
	public void reverse() {
		synchronized (mutex) {
			list.reverse();
		}
	}

	@Override
	public void reverse(int from, int to) {
		synchronized (mutex) {
			list.reverse(from, to);
		}
	}

	@Override
	public void shuffle(Random rand) {
		synchronized (mutex) {
			list.shuffle(rand);
		}
	}

	@Override
	public void sort() {
		synchronized (mutex) {
			list.sort();
		}
	}

	@Override
	public void sort(int fromIndex, int toIndex) {
		synchronized (mutex) {
			list.sort(fromIndex, toIndex);
		}
	}

	@Override
	public int binarySearch(char value) {
		synchronized (mutex) {
			return list.binarySearch(value);
		}
	}

	@Override
	public int binarySearch(char value, int fromIndex, int toIndex) {
		synchronized (mutex) {
			return list.binarySearch(value, fromIndex, toIndex);
		}
	}

	@Override
	public TCharList grep(TCharProcedure condition) {
		synchronized (mutex) {
			return list.grep(condition);
		}
	}

	@Override
	public TCharList inverseGrep(TCharProcedure condition) {
		synchronized (mutex) {
			return list.inverseGrep(condition);
		}
	}

	@Override
	public char max() {
		synchronized (mutex) {
			return list.max();
		}
	}

	@Override
	public char min() {
		synchronized (mutex) {
			return list.min();
		}
	}

	@Override
	public char sum() {
		synchronized (mutex) {
			return list.sum();
		}
	}

	@Override
	public boolean forEachDescending(TCharProcedure procedure) {
		synchronized (mutex) {
			return list.forEachDescending(procedure);
		}
	}

	@Override
	public void transformValues(TCharFunction function) {
		synchronized (mutex) {
			list.transformValues(function);
		}
	}

	/**
	 * SynchronizedRandomAccessList instances are serialized as SynchronizedList
	 * instances to allow them to be deserialized in pre-1.4 JREs (which do not have
	 * SynchronizedRandomAccessList). This method inverts the transformation. As a
	 * beneficial side-effect, it also grafts the RandomAccess marker onto
	 * SynchronizedList instances that were serialized in pre-1.4 JREs.
	 *
	 * Note: Unfortunately, SynchronizedRandomAccessList instances serialized in
	 * 1.4.1 and deserialized in 1.4 will become SynchronizedList instances, as this
	 * method was missing in 1.4.
	 */
	private Object readResolve() {
		return (list instanceof RandomAccess ? new TSynchronizedRandomAccessCharList(list) : this);
	}
}