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
package org.megam.deccanplato.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * A global constants which relate to the request and response json.
 * @author ram
 *
 */
public class Constants {

	private static final String VERSION = "0.1 beta";
	
	/**
	 * The key that are passed over in the security section of the request Json.
	 */
	
	
	/**
	 * The key that are passed over in the provider section of the request Json.
	 */
	public static final String CREATE="create";
	public static final String LIST="list";
	public static final String UPDATE="update";
	public static final String DELETE="delete";
	public static final String MARK="mark";
	public static final String SEND="send";
	public static final String PDF="pdf";
	public static final String CONVERT="convert";
	public static final String REMIND="remind";
	public static final String VIEW="view";
	public static final String MIGRATE="migrate";
	
	
	/**
	 * The key that are passed over in the output section of the request Json. 
	 */
	public static final String OUTPUT="output";
	public static final String CREATE_STRING="Created successfully";
	public static final String UPDATE_STRING="Updated successfully";
	public static final String DELETE_STRING="Deleted successfully";
	public static final String MIGRATE_STRING="Email migrated successfully";
}
