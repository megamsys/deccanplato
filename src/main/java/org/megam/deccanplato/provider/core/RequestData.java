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
package org.megam.deccanplato.provider.core;

import java.util.Formatter;

import com.google.gson.annotations.SerializedName;



public class RequestData {
	
	private AccessInfo system;
	private ProviderInfo provider;	
	private OutputInfo execution;

	
	public RequestData() {
	}
	
	public AccessInfo getAccess() {
		return system;
	}
	
	public ProviderInfo getGeneral() {
		return provider;
	}
	
	public OutputInfo getOutput() {
		return execution;
	}
	
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%n", getAccess().toString());
		formatter.format("%s%n", getGeneral().toString());
		formatter.format("%s%n", getOutput().toString());
		formatter.close();
		return strbd.toString();
	}
	
}
