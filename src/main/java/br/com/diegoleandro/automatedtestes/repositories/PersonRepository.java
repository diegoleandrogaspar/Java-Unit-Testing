package br.com.diegoleandro.automatedtestes.repositories;

import br.com.diegoleandro.automatedtestes.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

    // Define custom query using JPQL with index parameters
    @Query("select p from Person p where p.firstName = ?1 and p.lastName = ?2")
    Person findbyJPQL(String firstName, String lastName);


    //custom query using JPQL with named parameters
    @Query("select p from Person p where p.firstName =:firstName and p.lastName =:lastName")
    Person findbyJPQLNamedParameters(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);


    //custom query using Native SQL with index parameters
    @Query(value = "select * from person p where p.first_name =?1 and p.last_name =?2", nativeQuery = true)
    Person findbyNativeSQL(String firstName, String lastName);


    //custom query using Native SQL with index named parameters
    @Query(value = "select * from person p where p.first_name =:firstName and p.last_name =:lastName", nativeQuery = true)
    Person findbyNativeSQLwithNamedParameters(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);


}
