package uz.coding.employee.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.coding.employee.dto.JobTitle;

public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {
}
