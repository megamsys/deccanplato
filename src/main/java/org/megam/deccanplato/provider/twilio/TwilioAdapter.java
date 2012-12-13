/**
 * “Copyright 2012 Megam Systems”
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
package org.megam.deccanplato.provider.twilio;

import java.util.Map;

import org.megam.deccanplato.provider.AbstractProviderAdapter;
import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.sugarcrm.handler.BusinessActivityImpl;

/**
 * @author pandiyaraja
 *
 */
public class TwilioAdapter extends AbstractProviderAdapter{

	private BusinessActivity activity;
	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.ProviderAdapter#handle()
	 */
	public TwilioAdapter() {
		super();
	}
	@Override
	public Map<String, String> handle() {
		return activity.run();
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.ProviderAdapter#build()
	 */
	@Override
	public boolean build() {
		/** get the handle responsible for the call and stick stuff into it **/
		activity.setArguments(bizInfo,args);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.AbstractProviderAdapter#configure()
	 */
	@Override
	public void configure() {
		
		activity = ProviderRegistry.instance().getBusinessActivity(bizInfo.getName(),
				bizInfo.getActivityName());
		
		((BusinessActivityImpl)activity).setName(bizInfo.getActivityName());
		
	}

}
