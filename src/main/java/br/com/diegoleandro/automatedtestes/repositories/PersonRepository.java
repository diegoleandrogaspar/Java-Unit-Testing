package br.com.diegoleandro.automatedtestes.repositories;

import br.com.diegoleandro.automatedtestes.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
