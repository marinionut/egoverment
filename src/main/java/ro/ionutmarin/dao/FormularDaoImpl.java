package ro.ionutmarin.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import ro.ionutmarin.entity.FormularEntity;
import ro.ionutmarin.entity.GreetingsEntity;

import java.util.List;

/**
 * Created by ionut on 10/22/2017.
 */
@Repository("formularDao")
public class FormularDaoImpl extends AbstractDao implements FormularDao {
    @Override
    public void save(FormularEntity formularEntity) {
        persist(formularEntity);
    }

    @Override
    public List<FormularEntity> findAllFormular() {
        Criteria criteria = getSession().createCriteria(FormularEntity.class);
        return (List<FormularEntity>) criteria.list();
    }
}
