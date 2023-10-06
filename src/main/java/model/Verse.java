package model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "v")
public class Verse {
    private int n;
    private String description;

    @XmlAttribute
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @XmlValue
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
