package ro.ionutmarin.entity;

import ro.ionutmarin.model.TipVenit;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ionut on 10/21/2017.
 */
@Entity
@Table(name="formular")
@XmlRootElement
public class FormularEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    @Column(name = "nume")
    String nume;

    @Column(name = "prenume")
    String prenume;

    @Column(name = "initialaTata")
    String initialaTata;

    @Column(name = "cnp")
    String cnp;

    @Column(name = "adresa")
    String adresa;

    @Column(name = "judet")
    String judet;

    @Column(name = "localitate")
    String localitate;

    @Column(name = "telefon")
    String telefon;

    @Column(name = "email")
    String email;

    @Column(name = "tipVenit")
    @Enumerated(EnumType.STRING)
    TipVenit tipVenit;

    @Column(name = "beneficiar")
    String beneficiar;

    @Column(name = "codIdentificare")
    String codIdentificare;

    @Column(name = "cont")
    String cont;

    @Column(name = "salariuBrut")
    Double salariuBrut;

    @Column(name = "sumaTotala")
    Double sumaTotala;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public TipVenit getTipVenit() {
        return tipVenit;
    }

    public void setTipVenit(TipVenit tipVenit) {
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
