package ro.ionutmarin.service;

import ro.ionutmarin.entity.FormularEntity;
import ro.ionutmarin.model.Formular;

import java.util.List;

/**
 * Created by ionut on 10/21/2017.
 */
public interface FormularService {
    public String save(Formular formular);
    public List<FormularEntity> findAllFormular();
}
