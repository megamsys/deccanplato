/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package org.megam.deccanplato.provider.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ram
 * 
 */
public class MultiDataMap<T extends Object> implements DataMap<T> {

	private Map<String, DataMap<T>> multiMap = new HashMap<String, DataMap<T>>();

	public MultiDataMap() {
	}

	public void addDataMap(DataMap singleDataMap) {
		multiMap.put(singleDataMap.name(), singleDataMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.deccanplato.provider.core.DataMap#map()
	 */
	@Override
	public Map<String, T> map() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.core.DataMap#name()
	 */
	@Override
	public String name() {
		return "multi-datamap";
	}
	


}
