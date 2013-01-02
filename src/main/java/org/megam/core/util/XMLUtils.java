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
package org.megam.core.util;

import java.io.BufferedWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class XMLUtils {

	public static <T extends Object> void marshal(Class clz, T marshalObj)
			throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clz);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(marshalObj, System.out);
		
	}

	public static <T extends Object> String marshalAsString(Class clz,
			T marshalObj) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clz);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter writer = new StringWriter();
		marshaller.marshal(marshalObj, new BufferedWriter(writer));
		return writer.toString();

	}

	public static <T extends Object> T unmarshalFromString(Class clz,
			String input) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		T unobj = (T) unmarshaller.unmarshal(new StreamSource(new StringReader(
				input.toString())));
		return unobj;
	}

}
