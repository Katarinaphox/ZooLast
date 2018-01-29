package io;

import animals.Animal;

import main.ExtensibleCage;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.*;
import java.util.*;
import org.jdom2.*;

public class XmlSaver {

	private static final String TAG_NAME_ZOO = "ZOO";
	private static final String TAG_NAME_CAGE = "CAGE";
	private static final String TAG_NAME_ANIMAL = "ANIMAL";
	private static final String ATTRIBUTE_NAME_SIZE = "size";
	private static final String ATTRIBUTE_NAME_FILL = "fill";
	private static final String ATTRIBUTE_NAME_TYPE = "type";

	public static void save(Map<String, ExtensibleCage<? extends Animal>> cages) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElement(TAG_NAME_ZOO);
			doc.appendChild(rootElement);
			for (String key : cages.keySet()) {
				ExtensibleCage<? extends Animal> extensibleCage = cages.get(key);
				Element cageElement = doc.createElement(TAG_NAME_CAGE);
				Attr attrType = doc.createAttribute(ATTRIBUTE_NAME_TYPE);
				attrType.setValue(key);
				cageElement.setAttributeNode(attrType);
				rootElement.appendChild(cageElement);
				for (Animal animal : extensibleCage.getCage()) {
					Element animalElement = doc.createElement(TAG_NAME_ANIMAL);
					Attr attrSize = doc.createAttribute(ATTRIBUTE_NAME_SIZE);
					Attr attrFill = doc.createAttribute(ATTRIBUTE_NAME_FILL);
					attrFill.setValue(String.valueOf(animal.getFill()));
					attrSize.setValue(String.valueOf(animal.getSize()));
					animalElement.setAttributeNode(attrFill);
					animalElement.setAttributeNode(attrSize);
					animalElement.appendChild(doc.createTextNode(animal.getNickName()));
					cageElement.appendChild(animalElement);
				}
			}

			// write file

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(".autosave"));
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			System.out.println("Error");

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();

		} catch (TransformerException e) {
			e.printStackTrace();

		}
		
	}
}
