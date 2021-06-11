package kg.megacom.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findByFirstNameAndIdGreaterThanEqual(String firstName, long id);

    Customer findByFirstNameLikeAndLastNameEndingWith(String firstNameLike, String lastNameEndingWith);

    List<Customer> findByFirstNameLikeOrLastNameEndingWith(String firstNameLike, String lastNameEndingWith);

    Customer findByFirstNameAndLastNameEndingWith(String firstName, String lastNameEndingWith);

    Customer findByFirstNameStartingWithAndLastNameContainingIgnoreCase(String firstNameStart, String lastNameContain);

    List<Customer> findByFirstNameIn(List<String> firstNameRange);

    List<Customer> findByFirstNameOrderByLastNameDesc(String firstName);

    Customer findByLastNameAndIdLessThanEqual(String lastName, long id);
}