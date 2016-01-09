package tools;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.persistence.internal.sessions.factories.model.platform.NetWeaver_7_1_PlatformConfig;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import xml_classes.*;
public class DataManager {
	
	public DataListType unmarshall(InputStream is) throws JAXBException
	{
		JAXBContext jc = JAXBContext.newInstance("xml_classes");
		Unmarshaller u = jc.createUnmarshaller();
		DataListType result = (DataListType) u.unmarshal(is);
		return result;
	}
	public InputStream marshall(DataListType dlt) throws JAXBException
	{
		JAXBContext jc = JAXBContext.newInstance("xml_classes");
		Marshaller m = jc.createMarshaller();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		m.marshal(dlt, os);
		return new ByteArrayInputStream(os.toByteArray());
		
	}

	public Document loadXmlFromFile(String filename) throws ParserConfigurationException, SAXException, IOException
	{
	
		File file = new File(filename);
		InputStream stream = new FileInputStream(file);
		return loadXmlFromStream(stream);
	}
	public Document loadXmlFromStream(InputStream stream) throws ParserConfigurationException, SAXException, IOException
	{
		Document document;
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(stream);
		return document;
	}
	
	public void saveDataToXml(DataListType dlt, String filename) throws JAXBException, IOException
	{
		InputStream istream = marshall(dlt);
		Files.copy(istream, new File(filename).toPath());
	}
	public DataListType loadDataFromXml(String filename) throws FileNotFoundException, JAXBException
	{
		return unmarshall(new FileInputStream(filename));
	}
		
}
