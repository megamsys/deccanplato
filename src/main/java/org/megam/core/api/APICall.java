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
package org.megam.core.api;

import java.util.Formatter;

public class APICall {

	private String apistr;
	private String desc;

	public APICall(String tempApi, String tempDesc) {
		this.apistr = tempApi;
		this.desc = tempDesc;
	}

	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("-10s%3s%s%n", apistr, " - ", desc);
		formatter.close();
		return strbd.toString();
	}

}
