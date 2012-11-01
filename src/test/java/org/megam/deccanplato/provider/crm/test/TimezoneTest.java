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
package org.megam.deccanplato.provider.crm.test;

import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.junit.Test;

/**
 * @author pandiyaraja
 *
 */
public class TimezoneTest {
    @Test
	public void timeTest() {
		Locale locale=new Locale("en","US");
		String time=locale.toString();
		System.out.println("Time"+time);
		System.out.println("TimeZONE"+SimpleTimeZone.getTimeZone(time));
	}
}
