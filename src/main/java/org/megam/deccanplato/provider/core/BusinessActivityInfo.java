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

import java.util.Formatter;

/**
 * @author ram
 * 
 */
public class BusinessActivityInfo {

	private String name;
	private String activity;
	private BusinessActivityNameSpliter bans;
	
	public BusinessActivityInfo(String tempName, String tempActivity) {
		bans=new BusinessActivityNameSpliter(tempActivity);
		this.name = tempName;
		this.activity = tempActivity;
	}

	public String getName() {
		return name;
	}

	public String getActivity() {
		return activity;
	}
	public String getActivityName() {
		return bans.getActivityName();
	}
	public String getActivityFunction() {
		System.out.println("BUSINESS ACTIVITY FUNCTION"+bans.getActivityFunction());
		return bans.getActivityFunction();
	}

	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%10s = %s%n", getName(), getActivity());
		formatter.close();
		return strbd.toString();
	}
	
	private class BusinessActivityNameSpliter{
		private String name;
		private String function;
		String a[]=new String[2];
		
		BusinessActivityNameSpliter(String activityname){
			
			a=activityname.split("#");
			
		}
		private String getActivityName() {
			return name=a[0];
		}
		private String getActivityFunction() {
			return function=a[1];
		}
	}

}
