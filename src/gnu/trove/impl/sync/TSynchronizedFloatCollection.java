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

import gnu.trove.iterator.*;
import gnu.trove.procedure.*;
import gnu.trove.*;

import java.util.Collection;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class TSynchronizedFloatCollection implements TFloatCollection, Serializable {
	private static final long serialVersionUID = 3053995032091335093L;

	final TFloatCollection c; // Backing Collection
	final Object mutex; // Object on which to synchronize

	public TSynchronizedFloatCollection(TFloatCollection c) {
		if (c == null)
			throw new NullPointerException();
		this.c = c;
		mutex = this;
	}

	public TSynchronizedFloatCollection(TFloatCollection c, Object mutex) {
		this.c = c;
		this.mutex = mutex;
	}

	@Override
	public int size() {
		synchronized (mutex) {
			return c.size();
		}
	}

	@Override
	public boolean isEmpty() {
		synchronized (mutex) {
			return c.isEmpty();
		}
	}

	@Override
	public boolean contains(float o) {
		synchronized (mutex) {
			return c.contains(o);
		}
	}

	@Override
	public float[] toArray() {
		synchronized (mutex) {
			return c.toArray();
		}
	}

	@Override
	public float[] toArray(float[] a) {
		synchronized (mutex) {
			return c.toArray(a);
		}
	}

	@Override
	public TFloatIterator iterator() {
		return c.iterator(); // Must be manually synched by user!
	}

	@Override
	public boolean add(float e) {
		synchronized (mutex) {
			return c.add(e);
		}
	}

	@Override
	public boolean remove(float o) {
		synchronized (mutex) {
			return c.remove(o);
		}
	}

	@Override
	public boolean containsAll(Collection<?> coll) {
		synchronized (mutex) {
			return c.containsAll(coll);
		}
	}

	@Override
	public boolean containsAll(TFloatCollection coll) {
		synchronized (mutex) {
			return c.containsAll(coll);
		}
	}

	@Override
	public boolean containsAll(float[] array) {
		synchronized (mutex) {
			return c.containsAll(array);
		}
	}

	@Override
	public boolean addAll(Collection<? extends Float> coll) {
		synchronized (mutex) {
			return c.addAll(coll);
		}
	}

	@Override
	public boolean addAll(TFloatCollection coll) {
		synchronized (mutex) {
			return c.addAll(coll);
		}
	}

	@Override
	public boolean addAll(float[] array) {
		synchronized (mutex) {
			return c.addAll(array);
		}
	}

	@Override
	public boolean removeAll(Collection<?> coll) {
		synchronized (mutex) {
			return c.removeAll(coll);
		}
	}

	@Override
	public boolean removeAll(TFloatCollection coll) {
		synchronized (mutex) {
			return c.removeAll(coll);
		}
	}

	@Override
	public boolean removeAll(float[] array) {
		synchronized (mutex) {
			return c.removeAll(array);
		}
	}

	@Override
	public boolean retainAll(Collection<?> coll) {
		synchronized (mutex) {
			return c.retainAll(coll);
		}
	}

	@Override
	public boolean retainAll(TFloatCollection coll) {
		synchronized (mutex) {
			return c.retainAll(coll);
		}
	}

	@Override
	public boolean retainAll(float[] array) {
		synchronized (mutex) {
			return c.retainAll(array);
		}
	}

	@Override
	public float getNoEntryValue() {
		return c.getNoEntryValue();
	}

	@Override
	public boolean forEach(TFloatProcedure procedure) {
		synchronized (mutex) {
			return c.forEach(procedure);
		}
	}

	@Override
	public void clear() {
		synchronized (mutex) {
			c.clear();
		}
	}

	@Override
	public String toString() {
		synchronized (mutex) {
			return c.toString();
		}
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		synchronized (mutex) {
			s.defaultWriteObject();
		}
	}
}
