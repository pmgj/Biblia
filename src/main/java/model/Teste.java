package model;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class Teste {
    public static void main(String[] args) throws JAXBException {
        Verse v = new Verse();
        v.setDescription("Minha descrição");
        v.setN(2);

        Verse v2 = new Verse();
        v2.setDescription("Minha descrição sdsdafdsf");
        v2.setN(5);

        List<Verse> verses = new ArrayList<>();
        verses.add(v);
        verses.add(v2);

        Chapter c = new Chapter();
        c.setVerses(verses);
        c.setN(4);

        Verse v3 = new Verse();
        v3.setDescription("Minha descrição");
        v3.setN(2);

        Verse v4 = new Verse();
        v4.setDescription("Minha descrição sdsdafdsf");
        v4.setN(5);

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
