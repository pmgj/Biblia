package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletUpload", urlPatterns = { "/upload" })
@MultipartConfig
public class ServletUpload extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("Biblia.xml");
        String text = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        System.out.println(text);
    }
}
