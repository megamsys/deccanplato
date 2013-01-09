/* 
** Copyright [2012] [Megam Systems]
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
** http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/
package org.megam.deccanplato.provider.xero.handler;

import static org.megam.deccanplato.provider.Constants.*;
import static org.megam.deccanplato.provider.xero.Constants.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.megam.deccanplato.provider.BusinessActivity;
import org.megam.deccanplato.provider.core.BusinessActivityInfo;

import com.rossjourdain.util.xero.ArrayOfEmployee;
import com.rossjourdain.util.xero.Employee;
import com.rossjourdain.util.xero.Hyperlink;
import com.rossjourdain.util.xero.XeroClientException;
import com.rossjourdain.util.xero.XeroClientUnexpectedException;
import com.rossjourdain.util.xero.XeroPublicClient;
import com.rossjourdain.util.xero.XeroXmlManager;

/**
 * @author pandiyaraja
 * This class deals with xero employees. This class can use to
 * create, update, list and view employee.
 */
public class EmployeesImpl implements BusinessActivity {
	private Map<String, String> args;
	private BusinessActivityInfo bizInfo;
	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#setArguments(org.megam.deccanplato.provider.core.BusinessActivityInfo, java.util.Map)
	 */
	@Override
	public void setArguments(BusinessActivityInfo tempBizInfo,
			Map<String, String> tempArgs) {
		this.args=tempArgs;
		this.bizInfo=tempBizInfo;
		
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#run()
	 */
	@Override
	public Map<String, String> run() {
		Map<String, String> outMap=null;
		switch(bizInfo.getActivityFunction()) {
		case LIST:
			outMap=listAll();
			break;
		case VIEW:
			outMap=list();
			break;
		case CREATE:
			outMap=create();
			break;
		case UPDATE:
			outMap=create();
			break;
		}
		return outMap;
	}

	/**
	 * This Method creates and updates an employee in xero,
	 * update can perform by using employee id.
	 * @return
	 */
	private Map<String, String> create() {
		Map<String, String> outMap=new HashMap<>();
		XeroPublicClient client=new XeroPublicClient(args);
		try {
			  ArrayOfEmployee arrayOfEmployee=new ArrayOfEmployee();
			  List<Employee> employees=arrayOfEmployee.getEmployee();
			  Employee employee=new Employee();
			  
			  if(args.get(EMPLOYEE_ID)!=null) {
				  employee.setEmployeeID(args.get(EMPLOYEE_ID));
			  }
			  employee.setName(args.get(NAME));
			  employee.setFirstName(args.get(FIRST_NAME));
			  employee.setLastName(args.get(LAST_NAME));
			  Hyperlink hyperlink=new Hyperlink();
			  hyperlink.setUrl(args.get(EXTERNAL_LINK));
			  employee.setExternalLink(hyperlink);			  
			  employees.add(employee);
            String response =client.post(XeroXmlManager.employeesToXml(arrayOfEmployee), 
            		new StringTokenizer(args.get(BIZ_FUNCTION), "#").nextToken());
            outMap.put(OUTPUT, response);
        } catch (XeroClientException ex) {
            ex.printDetails();
        } catch (XeroClientUnexpectedException ex) {
            ex.printStackTrace();
        }
		return outMap;
	}

	/**
	 * This method returns a particular employee details 
	 * by using employee id.
	 * @return
	 */
	private Map<String, String> list() {
        Map<String,String> outMap = new HashMap<String,String>();	
		
		try {
			XeroPublicClient client=new XeroPublicClient(args);
        	String responseString =client.list(args.get(ID), 
        			new StringTokenizer(args.get(BIZ_FUNCTION), "#").nextToken());
    		outMap.put(OUTPUT, responseString);
		} catch (XeroClientException e) {
			e.printStackTrace();
		} catch (XeroClientUnexpectedException e) {
			e.printStackTrace();
		} 
		return outMap; 
	}

	/**
	 * This method returns a list of employee details
	 * @return
	 */
	private Map<String, String> listAll() {
		Map<String,String> outMap = new HashMap<String,String>();	
		
		try {
			XeroPublicClient client=new XeroPublicClient(args);
        	String response =client.listAll(new StringTokenizer(args.get(BIZ_FUNCTION), "#").nextToken());
    		outMap.put(OUTPUT, response);
		} catch (XeroClientException e) {
			e.printStackTrace();
		} catch (XeroClientUnexpectedException e) {
			e.printStackTrace();
		} 
		return outMap; 
	}

	/* (non-Javadoc)
	 * @see org.megam.deccanplato.provider.BusinessActivity#name()
	 */
	@Override
	public String name() {
		return "employee";
	}

}
