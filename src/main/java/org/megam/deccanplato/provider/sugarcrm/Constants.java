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
package org.megam.deccanplato.provider.sugarcrm;

/**
 * @author pandiyaraja
 *
 */
public class Constants {
	/**
	 * SUGARCRM input constants
	 */
	public static final String METHOD="method";
	public static final String INPUT_TYPE="input_type";
	public static final String RESPONSE_TYPE="response_type";
	public static final String DATA="rest_data";
	public static final String TYPE="JSON";
	public static final String SET_METHOD="set_entry";
	public static final String GET_LIST="get_entry_list";
	
	/**
	 * The constants that are needed Common purpose.
	 * Json Input constants
	 * 
	 */
	public static final String SUGAR_URL="http://localhost/sugarcrm/service/v4/rest.php";
	public static final String APPLICATION="sugar";
	public static final String PASSWORD="password";
	public static final String VERSION="1";		
	public static final String ID="id";
	public static final String NAME="name";
	public static final String TITLE="title";
	/**
	 * The constants that are needed for User business function.
	 * Json Input constants
	 */	
	public static final String USER_NAME="user_name";
	public static final String FIRST_NAME="first_name";
	public static final String LAST_NAME="last_name";
	public static final String EMAIL="email";
	public static final String EMAILADD="emailAddress";
	public static final String SESSIONID="session_id";
	/**
	 * The constants that are needed for Account business function.
	 * Json Input constants
	 */
	
	/**
	 * The constants that are needed for Leads business function.
	 * Json Input constants
	 */

}
