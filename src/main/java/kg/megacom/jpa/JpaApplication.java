package kg.megacom.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class JpaApplication {

	private static final Logger log = LoggerFactory.getLogger(JpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Martin", "Killian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));
			repository.save(new Customer("David", "Mitchell"));
			repository.save(new Customer("Michelle", "Lonely"));
			repository.save(new Customer("Michelle", "Carti"));
			repository.save(new Customer("Michelle", "Lefty"));
			repository.save(new Customer("Michelle", "Fighter"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findByLastNameAndIdLessThanEqual("Bauer", 2);
			log.info("Customer found with findByLastNameAndIdLessThanEqual('Bauer', 2):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch an individual customer by first name and by ID
			Customer customer1 = repository.findByFirstNameAndIdGreaterThanEqual("David", 6);
			log.info("Customer found with findByFirstNameAndIdGreaterThanEqual('David', 6) (Expected is David Mitchell):");
			log.info("--------------------------------");
			log.info(customer1.toString());
			log.info("");

			// fetch an individual customer by first name and lastName
			Customer customer2 = repository.findByFirstNameAndLastNameEndingWith("David", "er");
			log.info("Customer found with findByFirstNameAndLastNameEndingWith('David', 'er') (Expected is David Palmer):");
			log.info("--------------------------------");
			log.info(customer2.toString());
			log.info("");

			// fetch an individual customer by first nameLike and lastName
			Customer customer3 = repository.findByFirstNameLikeAndLastNameEndingWith("%David%", "ell");
			log.info("Customer found with findByFirstNameLikeAndLastNameEndingWith('David', 'ell') (Expected is David Mitchell):");
			log.info("--------------------------------");
			log.info(customer3.toString());
			log.info("");

			// fetch an individual customer by first nameLike and lastName
			Customer customer4 = repository.findByFirstNameStartingWithAndLastNameContainingIgnoreCase("Mi", "ne");
			log.info("Customer found with findByFirstNameStartingWithAndLastNameContainingIgnoreCase('Mi', 'ne') (Expected is Michelle Lonely):");
			log.info("--------------------------------");
			log.info(customer4.toString());
			log.info("");

			// fetch an individual customer by first name and order in descending order
			log.info("Customers found with findByFirstNameOrderByLastNameDesc(Michelle) in descending order :");
			log.info("--------------------------------");
			repository.findByFirstNameOrderByLastNameDesc("Michelle").forEach(x -> log.info(x.toString()));
			log.info("");


			// fetch customers by their first names
			List<String> firstNameRange = new ArrayList<>();
			firstNameRange.add("Martin");
			firstNameRange.add("Kim");
			firstNameRange.add("David");

			log.info("Customer found with findByFirstNameIn([Martin, Kim, David]):");
			log.info("--------------------------------");
			repository.findByFirstNameIn(firstNameRange).forEach(x -> log.info(x.toString()));
			log.info("");

			// fetch an individual customer by first name and last name ending with
			log.info("Customers found with findByFirstNameLikeOrLastNameEndingWith('%Michelle%', 'er') :");
			log.info("--------------------------------");
			repository.findByFirstNameLikeOrLastNameEndingWith("%Michelle%", "er").forEach(x -> log.info(x.toString()));
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");
		};
	}
}
