package ro.ionutmarin.dao;

import org.springframework.stereotype.Repository;
import ro.ionutmarin.entity.FormularEntity;
import ro.ionutmarin.entity.GreetingsEntity;

/**
 * Created by ionut on 10/22/2017.
 */
@Repository("formularDao")
public class FormularDaoImpl extends AbstractDao implements FormularDao {
    @Override
    public void save(FormularEntity formularEntity) {
        persist(formularEntity);
    }
}
