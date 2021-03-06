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
import gnu.trove.*;

import java.util.Collection;
import java.io.Serializable;

public class TUnmodifiableFloatCollection implements TFloatCollection, Serializable {
	private static final long serialVersionUID = 1820017752578914078L;

	final TFloatCollection c;

	public TUnmodifiableFloatCollection(TFloatCollection c) {
		if (c == null)
			throw new NullPointerException();
		this.c = c;
	}

	@Override
	public int size() {
		return c.size();
	}

	@Override
	public boolean isEmpty() {
		return c.isEmpty();
	}

	@Override
	public boolean contains(float o) {
		return c.contains(o);
	}

	@Override
	public float[] toArray() {
		return c.toArray();
	}

	@Override
	public float[] toArray(float[] a) {
		return c.toArray(a);
	}

	@Override
	public String toString() {
		return c.toString();
	}

	@Override
	public float getNoEntryValue() {
		return c.getNoEntryValue();
	}

	@Override
	public boolean forEach(TFloatProcedure procedure) {
		return c.forEach(procedure);
	}

	@Override
	public TFloatIterator iterator() {
		return new TFloatIterator() {
			TFloatIterator i = c.iterator();

			@Override
			public boolean hasNext() {
				return i.hasNext();
			}

			@Override
			public float next() {
				return i.next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public boolean add(float e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(float o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> coll) {
		return c.containsAll(coll);
	}

	@Override
	public boolean containsAll(TFloatCollection coll) {
		return c.containsAll(coll);
	}

	@Override
	public boolean containsAll(float[] array) {
		return c.containsAll(array);
	}

	@Override
	public boolean addAll(TFloatCollection coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends Float> coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(float[] array) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(TFloatCollection coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(float[] array) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(TFloatCollection coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(float[] array) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}
}
