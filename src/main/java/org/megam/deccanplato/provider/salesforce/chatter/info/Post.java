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
package org.megam.deccanplato.provider.salesforce.chatter.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * @author pandiyaraja
 *
 */
public class Post {
    Map<String, List<Messagesegment>> body=new HashMap<>();
	public Post(String type, String text) {
	 
	 body.put("messageSegments", set(type, text));
	}
	
	public List<Messagesegment> set(String type, String text) {
		Messagesegment msg; msg=new Messagesegment(type, text);
		List<Messagesegment> msglist=new ArrayList<>();
		msglist.add(msg);
		return msglist;
	}
   private class Messagesegment{
	   String type;
	   String text;
	   public Messagesegment(String type, String text) {
		   this.type=type;
		   this.text=text;
	   }
   }
}
