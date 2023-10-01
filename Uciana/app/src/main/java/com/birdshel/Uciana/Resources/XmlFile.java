package com.birdshel.Uciana.Resources;

import android.os.Environment;

import com.birdshel.Uciana.Utility.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class XmlFile {
    private Document document;
    private Element rootNode;
    private final File xmlFile;

    public XmlFile(String str, String str2) {
        File file = new File(Environment.getExternalStorageDirectory() + str);
        this.xmlFile = new File(file.getAbsolutePath() + File.separator + str2);
    }

    public void add(Element element, String str) {
        Element createElement = this.document.createElement(str);
        createElement.appendChild(this.document.createTextNode("\u200b"));
        element.appendChild(createElement);
    }

    public void create(String str) {
        try {
            Document newDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            this.document = newDocument;
            Element createElement = newDocument.createElement(str);
            this.rootNode = createElement;
            this.document.appendChild(createElement);
        } catch (Exception e2) {
            Log.message("MOD", "XmlFile Exception " + e2.getMessage());
        }
    }

    public boolean exists() {
        return this.xmlFile.exists();
    }

    public NodeList getItems(String str) {
        return this.document.getElementsByTagName(str);
    }

    public Element newItem(String str, String str2) {
        Element createElement = this.document.createElement(str);
        createElement.setAttribute("id", str2);
        this.rootNode.appendChild(createElement);
        return createElement;
    }

    public void open() {
        try {
            Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.xmlFile);
            this.document = parse;
            parse.getDocumentElement().normalize();
        } catch (Exception e2) {
            Log.message("MOD", "XmlFile Exception " + e2.getMessage());
        }
    }

    public void save() {
        try {
            DOMSource dOMSource = new DOMSource(this.document);
            StreamResult streamResult = new StreamResult(this.xmlFile);
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("method", "xml");
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            newTransformer.setOutputProperty("omit-xml-declaration", "yes");
            newTransformer.transform(dOMSource, streamResult);
        } catch (Exception e2) {
            Log.message("MOD", "XmlFile save Exception " + e2.getMessage());
        }
    }
}
