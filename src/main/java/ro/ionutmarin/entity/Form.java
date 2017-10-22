package ro.ionutmarin.entity;

import javax.persistence.*;

/**
 * Created by ionut on 10/21/2017.
 */
@Entity
@Table(name="formular")
public class Form {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
