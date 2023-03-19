package sh.radical.testpost.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sh.radical.testpost.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {}
