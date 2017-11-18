package ro.ionutmarin.model;

import java.util.List;

/**
 * Created by ionut on 11/18/2017.
 */
public class Report {
    String judet;
    Integer numarBarbatiJudet= 0;
    Integer numarFemeiJudet = 0;
    Double medieVenitJudet = 0.0;
    Integer numarVenitPensie = 0;
    Integer numarVenitSalariu= 0;
    List<Person> persons;

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public Integer getNumarBarbatiJudet() {
        return numarBarbatiJudet;
    }

    public void setNumarBarbatiJudet(Integer numarBarbatiJudet) {
        this.numarBarbatiJudet = numarBarbatiJudet;
    }

    public Integer getNumarFemeiJudet() {
        return numarFemeiJudet;
    }

    public void setNumarFemeiJudet(Integer numarFemeiJudet) {
        this.numarFemeiJudet = numarFemeiJudet;
    }

    public Double getMedieVenitJudet() {
        return medieVenitJudet;
    }

    public void setMedieVenitJudet(Double medieVenitJudet) {
        this.medieVenitJudet = medieVenitJudet;
    }

    public Integer getNumarVenitPensie() {
        return numarVenitPensie;
    }

    public void setNumarVenitPensie(Integer numarVenitPensie) {
        this.numarVenitPensie = numarVenitPensie;
    }

    public Integer getNumarVenitSalariu() {
        return numarVenitSalariu;
    }

    public void setNumarVenitSalariu(Integer numarVenitSalariu) {
        this.numarVenitSalariu = numarVenitSalariu;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}

