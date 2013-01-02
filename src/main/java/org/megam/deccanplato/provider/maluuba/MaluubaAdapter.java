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

import org.megam.deccanplato.provider.AbstractProviderAdapter;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.ProviderRegistry;

/**
 * @author pandiyaraja
 *
 */
public class MaluubaAdapter extends AbstractProviderAdapter<Object>{

	private BusinessActivity activity;
	
	public MaluubaAdapter() {
		super();
	}
	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.ProviderAdapter#handle()
	 */
	@Override
	public Object handle() {
		return ((Object) activity.run());
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.ProviderAdapter#build()
	 */
	@Override
	public boolean build() {
		activity.setArguments(bizInfo, args);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.AbstractProviderAdapter#configure()
	 */
	@Override
	public void configure() {
		activity = ProviderRegistry.instance().getBusinessActivity(
				bizInfo.getName(), bizInfo.getActivityName());
	}

}
