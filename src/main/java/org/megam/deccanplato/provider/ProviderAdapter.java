/* “Copyright 2012 Megam Systems”
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.megam.deccanplato.provider;

import org.megam.deccanplato.provider.core.DataMap;

/**
 * This interface acts as an adapter for a provider. Any new provider shall implement this interface.
 * Every cloud adapter will have its own implementation. 
 * 
 * @author ram
 *
 * @param <T>
 */
public interface ProviderAdapter<T extends Object> {
	
	/*
	 * This method handles the provider request and returns an output as it perceives.
	 */
	public T handle();
	
	/*
	 * This method builds the required arguments, and any other as required for the adapter to handle the business function. 
	 * Not much is implemented now, but in future the validity of the arguments prior to the run shall be done.
	 */
	public boolean build();
	
	/*
	 * Explicity set the mutated data map after any operation. For instance the initial JSON map will not have any
	 * authentication ticket as required by the cloud application. A separate cloudoperation provides that, hence after
	 * the accesss stuff, a multidatamap with the ticket value, initial json input values will be set via this method.
	 */
	public void setDataMap(DataMap multiMap);
	

}
