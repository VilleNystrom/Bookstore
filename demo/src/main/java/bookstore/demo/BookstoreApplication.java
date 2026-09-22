package bookstore.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(
            BookRepository bookRepository,
            CategoryRepository categoryRepository) {

        return (args) -> {

            Category fiction = new Category("Fiction");
            Category fantasy = new Category("Fantasy");
            Category programming = new Category("Programming");
            Category scienceFiction = new Category("Science Fiction");

            categoryRepository.save(fiction);
            categoryRepository.save(fantasy);
            categoryRepository.save(programming);
            categoryRepository.save(scienceFiction);

            bookRepository.save(new Book(
                    "Harry Potter and the Philosopher's Stone",
                    "J.K. Rowling",
                    1997,
                    "9780747532743",
                    19.90,
                    fantasy
            ));

            bookRepository.save(new Book(
                    "The Hobbit",
                    "J.R.R. Tolkien",
                    1937,
                    "9780261102217",
                    15.90,
                    fantasy
            ));

            bookRepository.save(new Book(
                    "Clean Code",
                    "Robert C. Martin",
                    2008,
                    "9780132350884",
                    39.90,
                    programming
            ));

            bookRepository.save(new Book(
                    "The Lord of the Rings",
                    "J.R.R. Tolkien",
                    1954,
                    "9780261102385",
                    29.90,
                    fantasy
            ));

            bookRepository.save(new Book(
                    "Dune",
                    "Frank Herbert",
                    1965,
                    "9780441013593",
                    14.90,
                    scienceFiction
            ));

            bookRepository.save(new Book(
                    "1984",
                    "George Orwell",
                    1949,
                    "9780451524935",
                    12.90,
                    fiction
            ));

            System.out.println("Categories and books saved!");
        };
    }
}