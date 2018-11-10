package com.example.smile.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.transform.TransformerException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xpath.XPathAPI;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class NekoHtmlParser {
	protected static Log logger = LogFactory.getLog(NekoHtmlParser.class);
	public Document document;

	public NodeList selectNodes(String path) {
		try {
			return XPathAPI.selectNodeList(this.document, path);
		} catch (TransformerException e) {
			logger.error(e);
		}
		return null;
	}

	public Node selectSingleNode(String path) {
		try {
			return XPathAPI.selectSingleNode(this.document, path);
		} catch (TransformerException e) {
			logger.error(e);
		}
		return null;
	}

	public static NodeList selectNodes(String path, Node node) {
		try {
			return XPathAPI.selectNodeList(node, path);
		} catch (TransformerException e) {
			logger.error(e);
		}
		return null;
	}

	public static Node selectSingleNode(String path, Node node) {
		try {
			return XPathAPI.selectSingleNode(node, path);
		} catch (TransformerException e) {
			logger.error(e);
		}
		return null;
	}

	public ArrayList<String> selectXPath(String path) {
		NodeList list = selectNodes(path);
		if ((list == null) || (list.getLength() == 0))
			return null;
		ArrayList values = new ArrayList();
		for (int i = 0; i < list.getLength(); ++i) {
			String value = list.item(i).getTextContent();
			if (value == null)
				continue;
			if (value.length() == 0)
				continue;
			values.add(value.trim().replaceAll("\r", "").replaceAll("\n", ""));
		}
		return values;
	}

	public boolean load(String content, String charSet) {
		DOMParser parser = new DOMParser();
		XMLInputSource source = new XMLInputSource(null, "xpath-wrapper", null, new StringReader(content), charSet);
		try {
			parser.setFeature("http://xml.org/sax/features/namespaces", false);
			parser.setProperty("http://cyberneko.org/html/properties/default-encoding", charSet);
			parser.parse(source);
			this.document = parser.getDocument();
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	public boolean load(byte[] content, String charSet) {
		DOMParser parser = new DOMParser();
		try {
			InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(content), charSet);
			InputSource source = new InputSource(reader);
			parser.setFeature("http://xml.org/sax/features/namespaces", false);
			parser.setProperty("http://cyberneko.org/html/properties/default-encoding", charSet);
			parser.parse(source);
			this.document = parser.getDocument();
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	public boolean load(File file, String charSet) {
		DOMParser parser = new DOMParser();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file), charSet);
			InputSource source = new InputSource(reader);
			parser.setFeature("http://xml.org/sax/features/namespaces", false);
			parser.parse(source);
			this.document = parser.getDocument();
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	public String getNodeText(String xpath) {
		Node node = selectSingleNode(xpath);
		if (node == null)
			return "";
		return node.getTextContent();
	}

	public String getNodeText(String xpath, Node sourceNode) {
		Node node = selectSingleNode(xpath, sourceNode);
		if (node == null)
			return "";
		return node.getTextContent();
	}
}