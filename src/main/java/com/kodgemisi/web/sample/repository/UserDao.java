package com.kodgemisi.web.sample.repository;

import com.kodgemisi.web.sample.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public class UserDao extends GenericDao<User> {

    public User findByEmail(String email) {
        Criteria c = this.createCriteria();

        c.add(Restrictions.eq("email", email));
        return (User) c.uniqueResult();
    }

    public HashSet<User> getAllWithItems() {
        Criteria c = this.createCriteria();
        c.createAlias("items","items",JoinType.LEFT_OUTER_JOIN);
        return new HashSet<>(c.list());
    }
}
