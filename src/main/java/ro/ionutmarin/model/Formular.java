package ro.ionutmarin.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ionut on 10/24/2017.
 */
@XmlRootElement
public class Formular {
    String nume;
    String prenume;
    String initialaTata;
    String cnp;
    String adresa;
    String judet;
    String localitate;
    String telefon;
    String email;
    Integer tipVenit;
    String beneficiar;
    String codIdentificare;
    String cont;
    Double salariuBrut;
    Double sumaTotala;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getInitialaTata() {
        return initialaTata;
    }

    public void setInitialaTata(String initialaTata) {
        this.initialaTata = initialaTata;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getLocalitate() {
        return localitate;
    }

    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTipVenit() {
        return tipVenit;
    }

    public void setTipVenit(Integer tipVenit) {
        this.tipVenit = tipVenit;
    }

    public String getBeneficiar() {
        return beneficiar;
    }

    public void setBeneficiar(String beneficiar) {
        this.beneficiar = beneficiar;
    }

    public String getCodIdentificare() {
        return codIdentificare;
    }

    public void setCodIdentificare(String codIdentificare) {
        this.codIdentificare = codIdentificare;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public Double getSalariuBrut() {
        return salariuBrut;
    }

    public void setSalariuBrut(Double salariuBrut) {
        this.salariuBrut = salariuBrut;
    }

    public Double getSumaTotala() {
        return sumaTotala;
    }

    public void setSumaTotala(Double sumaTotala) {
        this.sumaTotala = sumaTotala;
    }
}
