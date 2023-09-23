package uz.coding.employee.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.coding.employee.dto.Department;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByCode(String code);
}
