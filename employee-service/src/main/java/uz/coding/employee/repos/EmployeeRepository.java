package uz.coding.employee.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.coding.employee.dto.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByPersonId(Long personalId);

    Optional<Employee> findByEmpCode(String empCode);
}
