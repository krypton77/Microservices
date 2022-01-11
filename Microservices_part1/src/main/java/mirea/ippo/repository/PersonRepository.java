package mirea.ippo.repository;

import mirea.ippo.entity.Person_first;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person_first, Integer> {

    @Query(value = "select * from person_first", nativeQuery = true)
    List<Person_first> allPersons();

    @Query(value = "select * from person_first where id = :id", nativeQuery = true)
    Person_first getPersonById(@Param("id") Integer id);
}
