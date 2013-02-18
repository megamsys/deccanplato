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
	public static final String VIEW="view";
	/**
	 * ZOHO INVOICE
	 */
	public static final String MARK="mark";
	public static final String SEND="send";
	public static final String PDF="pdf";
	public static final String CONVERT="convert";
	public static final String REMIND="remind";	
	public static final String MIGRATE="migrate";
	/**
	 * SALESFORCE CHATTER
	 */
	public static final String FEED="feed";
	public static final String COMMENT="comment";
	public static final String LIKE="like";
	public static final String MEMBERSHIP="membership";
	public static final String FILE="file";
	public static final String MEMBER="member";
	public static final String CONVERSATION="conversation";
	public static final String CONVERSATIONVIEW="conversationview";
	public static final String MESSAGE="message";
	public static final String MESSAGEVIEW="messageview";
	public static final String POSTCOMMENT="postcomment";
	/**
	 * TWILIO
	 */
	public static final String SUSPEND="suspend";
	public static final String ACTIVE="active";
	public static final String CLOSE="close";
	public static final String LISTACTIVE="listactive";
	public static final String LOCALLIST="locallist";
	public static final String STARTSWITH="startswith";
	public static final String STORM="storm";
	public static final String INREGION="region";
	public static final String WITHINDISTANCE="withindistance";
	public static final String TOLLFREELIST="tollfreelist";
	public static final String TOLLFREESTORM="tollfreestorm";
	public static final String TOLLFREEPATTERN="tollfreepattern";
	public static final String RECORDINGLIST="recordinglist";
	public static final String NOTIFICATIONS="notifications";
	public static final String MAKECALL="makecall";
	public static final String MODIFYLIVECALL="modifylivecall";
	public static final String TRANSCRIPTIONS="transcriptions";
	/**
	 * BOX
	 */
	public static final String UPLOAD="upload";
	public static final String DOWNLOAD="download";
	public static final String SHARE="share";
	public static final String RETRIVE="retrive";
	/**
	 * The key that are passed over in the output section of the request Json. 
	 */
	public static final String OUTPUT="output";
	public static final String CREATE_STRING="Created successfully";
	public static final String UPDATE_STRING="Updated successfully";
	public static final String DELETE_STRING="Deleted successfully";
	public static final String MIGRATE_STRING="Email migrated successfully";
}
