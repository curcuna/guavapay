package com.guavapay.authservice.repository;

import com.guavapay.authservice.model.entity.Person;
import com.guavapay.authservice.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);
    List<Person> findAllByRoles(Role role);

    @Query("SELECT p FROM Person p WHERE p.username = :username and p.password = :password")
    Person retrieveByUsernamePassword(@Param("username") String username, @Param("password") String password);

    Person save(Person person);
}