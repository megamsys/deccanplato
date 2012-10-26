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

import java.util.Formatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractProviderAdapter<T extends Object> implements ProviderAdapter<T> {
		
		
	protected Map<String,String> args;

	protected String cloud_app;

	protected String business_function;
	
	public AbstractProviderAdapter(){
		
	}
	
	public AbstractProviderAdapter(Map<String,String> tempArgs) {
		this.args = tempArgs;	
		configure();
	}
	
	public abstract void configure();
	
	
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
