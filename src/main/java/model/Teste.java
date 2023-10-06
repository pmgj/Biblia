package model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jakarta.json.bind.JsonbBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class Teste {

    public static void main(String[] args) {
        InputStream is = Teste.class.getClassLoader().getResourceAsStream("Biblia.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = String.format("/bible/book[@abbrev = '%s']/c[@n = %s]/v[@n >= %s and @n <= %s]", "gn",
                    "1", "3", "6");
            List<Verse> verses = new ArrayList<>();
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element v = (Element) nodeList.item(i);
                verses.add(new Verse(Integer.parseInt(v.getAttribute("n")), v.getTextContent()));
            }
            String x = JsonbBuilder.create().toJson(verses);
            System.out.println(x);
        } catch (ParserConfigurationException | SAXException | XPathExpressionException | IOException ex) {

        }        
    }

    public static void main2(String[] args) throws JAXBException {
        Verse v = new Verse(2, "Minha descrição");
        Verse v2 = new Verse(5, "Minha descrição sdsdafdsf");

        List<Verse> verses = new ArrayList<>();
        verses.add(v);
        verses.add(v2);

        Chapter c = new Chapter();
        c.setVerses(verses);
        c.setN(4);

        Verse v3 = new Verse(2, "Minha descrição");

        Verse v4 = new Verse(5, "Minha descrição sdsdafdsf");

        List<Verse> verses2 = new ArrayList<>();
        verses2.add(v3);
        verses2.add(v4);

        Chapter c2 = new Chapter();
        c2.setVerses(verses2);
        c2.setN(6);

        List<Chapter> chapters = new ArrayList<>();

        chapters.add(c);
        chapters.add(c2);

        Book b = new Book();
        b.setAbbrev("sdfa");
        b.setChapter(chapters);
        b.setName("sdjfhasd");
        b.setChapters(34);

        JAXBContext jaxbContext2 = JAXBContext.newInstance(Book.class);
        Marshaller m2 = jaxbContext2.createMarshaller();
        m2.marshal(b, System.out);
    }
}
