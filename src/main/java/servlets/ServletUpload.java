package servlets;

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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Verse;

@WebServlet(name = "ServletUpload", urlPatterns = { "/execute" })
@MultipartConfig
public class ServletUpload extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("Biblia.xml");

        String book = req.getParameter("book");
        String chapter = req.getParameter("chapter");
        String verse1 = req.getParameter("verseFrom");
        String verse2 = req.getParameter("verseTo");

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = String.format("/bible/book[@abbrev = '%s']/c[@n = %s]/v[@n >= %s and @n <= %s]", book,
                    chapter, verse1, verse2);
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
            List<Verse> verses = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element v = (Element) nodeList.item(i);
                verses.add(new Verse(Integer.parseInt(v.getAttribute("n")), v.getTextContent()));
            }
            String x = JsonbBuilder.create().toJson(verses);
            resp.setContentType("application/json");
            resp.getWriter().print(x);
        } catch (ParserConfigurationException | SAXException | XPathExpressionException ex) {

        }
    }
}
