/* 
** Copyright [2012] [Megam Systems]
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
** http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/
package org.megam.deccanplato.provider.maluuba;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.maluuba.Constants.*;


import org.megam.deccanplato.provider.core.AdapterAccess;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;

/**
 * @author pandiyaraja
 *
 */
public class MaluubaAdapterAccess implements AdapterAccess{

	private boolean success = false;
	
	public MaluubaAdapterAccess() {
		super();
	}
	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.core.AdapterAccess#isSuccessful()
	 */
	@Override
	public boolean isSuccessful() {
		return success;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.core.AdapterAccess#authenticate(org.megam.deccanplato.provider.core.DataMap)
	 */
	@Override
	public <T> DataMap<T> authenticate(DataMap<T> accessMap)
			throws AdapterAccessException {
		success=true;
		accessMap.map().put(APIKEY, (T) KEY);
		return accessMap;
	}

}
