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
		System.out.println("XML Utils done.");
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
