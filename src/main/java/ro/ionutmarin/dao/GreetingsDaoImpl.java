package ro.ionutmarin.dao;

import org.springframework.stereotype.Repository;
import ro.ionutmarin.entity.GreetingsEntity;

/**
 * Created by ionut on 10/22/2017.
 */
@Repository("greetingsDao")
public class GreetingsDaoImpl extends AbstractDao implements GreetingsDao {
    @Override
    public void save(GreetingsEntity greetingsEntity) {
        persist(greetingsEntity);
    }
}
