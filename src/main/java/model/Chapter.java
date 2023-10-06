package model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "c")
public class Chapter {
    private List<Verse> verses;
    private int n;

    @XmlElement(name = "v", type = Verse.class)
    public List<Verse> getVerses() {
        return verses;
    }

    public void setVerses(List<Verse> verses) {
        this.verses = verses;
    }

    @XmlAttribute
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
