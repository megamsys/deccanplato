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

public class CloudOperationOutput<T extends Object> {
	
	private T out;
	private String name;
	
	public CloudOperationOutput(String tempName) {
		this.name = tempName;
	}
	
	public void set(T tempOut) {
		this.out = tempOut;
	}
	
	public T get() {
		return out;
	}

	public String name() {
		return name;
	}
	public String toString() {
		return name+","+get().toString();
	}

}
