package ru.kata.spring.boot_security.demo.dao;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {

    //private final LocalContainerEntityManagerFactoryBean entityManager;

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUser() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Override
    public void deleteUser(Long id) {

        entityManager.remove(findById(id));
    }

    @Override
    public void addUser(User user) {

        entityManager.persist(user);
    }


    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }
    @Override
    public User findById(Long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public User findByLogin(String login) {

        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login")
                .setParameter("login",login).getSingleResult();
    }

}

