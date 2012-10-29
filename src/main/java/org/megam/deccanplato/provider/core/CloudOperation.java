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

public interface CloudOperation {

	/** This is a conditional exectution interface which decides the fitness of an operation to run 
	 *
	 * @return {@link Boolean}
	 */
	public boolean isFitToRun();	
	
	
	public void preOperation() throws CloudOperationException;
	
	
	public void postOperation();


	public boolean canProceed();


	public <T extends Object> CloudOperationOutput<T> handle() 	throws CloudOperationException;
}
