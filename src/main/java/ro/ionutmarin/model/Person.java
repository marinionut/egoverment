package ro.ionutmarin.model;

/**
 * Created by ionut on 11/18/2017.
 */
public class Person {
    String nume;
    String prenume;
    String initialaTata;
    String cnp;
    String telefon;
    String email;

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
}
