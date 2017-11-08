package ro.ionutmarin.dao;

import ro.ionutmarin.entity.FormularEntity;
import ro.ionutmarin.entity.GreetingsEntity;

import java.util.List;

/**
 * Created by ionut on 10/22/2017.
 */
public interface FormularDao {
    public void save(FormularEntity formularEntity);

    public List<FormularEntity> findAllFormular();
}
