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

import static org.megam.deccanplato.provider.core.ProviderInfo.BIZ_FUNCTION;
import static org.megam.deccanplato.provider.core.ProviderInfo.PROVIDER;

import java.util.Formatter;
import java.util.Map;

import org.megam.deccanplato.provider.core.BusinessActivityInfo;
import org.megam.deccanplato.provider.core.DataMap;

/**
 * An abstract implementation of the Provider adapter. Contains more methods to
 * configure any adapter during implementation. The provider name (or) cloud
 * application name eg: salesforce, zohocrm etc. and the business_function the
 * adapter is trying to get configured is required during configuration.
 * 
 * @author ram
 * 
 * @param <T>
 */
public abstract class AbstractProviderAdapter<T extends Object> implements
		ProviderAdapter<T> {

	/**
	 * An arguments map which contains the the key value pairs required to
	 * perform a business function
	 */
	protected Map<String, String> args;

	/**
	 * A string representing the provider name, eg: salesforce, zohocrm etc. A
	 * string representing the business function, eg: user A string representing
	 * the business activity eg: create.
	 **/
	protected BusinessActivityInfo bizInfo;

	/**
	 * A null constructor as required by our spring framework to load the beans.
	 * The down-side of this approach is that, the arguments map will be null. A
	 * constructor with the arguments map is the right way to run the adapter.
	 * Apparently when spring loads this bean, we wouldn't be having an
	 * arguments map. This is because the arguments map is passed as a request
	 * of parsing the input Json. Failing to set the arguments prior to any
	 * business activity run will result in the adapter to fail.
	 */
	public AbstractProviderAdapter() {
	}

	/**
	 * An abstract method which allows the implementing adapter classes to
	 * configure its implementation as it desires. This is called by setDataMap.
	 * 
	 * @see org.megam.deccanplato.provider.ProviderAdapter#setDataMap(org.megam.
	 *      deccanplato.provider.core.MultiDataMap)
	 */
	public abstract void configure();

	/**
	 * An explicit setter to set the configuration of this bean (or) provider.
	 * This is not a good way, but helpful for setting up the configuration of
	 * the provider after spring loads any adapter
	 * 
	 * @param tempArgs
	 */
	public void setConfiguration(Map<String, String> tempArgs) {
		this.args = tempArgs;
		bizInfo = new BusinessActivityInfo(args.get(PROVIDER),
				args.get(BIZ_FUNCTION));
		configure();
	}

	/*
	 * Explicity set the mutated data map after any operation. For instance the
	 * initial JSON map will not have any authentication ticket as required by
	 * the cloud application. A separate cloudoperation provides that, hence
	 * after the accesss stuff, a multidatamap with the ticket value, initial
	 * json input values will be set via this method.
	 * 
	 * @see org.megam.deccanplato.provider.ProviderAdapter#setDataMap(org.megam.
	 * deccanplato.provider.core.MultiDataMap)
	 */
	@Override
	public void setDataMap(DataMap multiMap) {
		setConfiguration(multiMap.map());
		build();
	}

	/**
	 * Returns the string representation of <K,V> inside the map.
	 */
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		for (Map.Entry<String, String> oneArg : args.entrySet()) {
			formatter.format("%10s = %s%n", oneArg.getKey(), oneArg.getValue());
		}
		formatter.close();
		return strbd.toString();
	}

}
