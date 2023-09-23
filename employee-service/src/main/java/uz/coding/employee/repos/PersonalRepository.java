package uz.coding.employee.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.coding.employee.dto.Person;

import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByPinfl(String pinfl);


}
