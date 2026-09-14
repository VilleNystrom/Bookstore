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
    public CommandLineRunner demo(BookRepository repository) {
        return (args) -> {

            repository.save(new Book(
                    "Harry Potter and the Philosopher's Stone",
                    "J.K. Rowling",
                    1997,
                    "9780747532743",
                    19.90
            ));

            repository.save(new Book(
                    "The Hobbit",
                    "J.R.R. Tolkien",
                    1937,
                    "9780261102217",
                    15.90
            ));

            repository.save(new Book(
                    "Clean Code",
                    "Robert C. Martin",
                    2008,
                    "9780132350884",
                    39.90
            ));

            repository.save(new Book(
                    "The Lord of the Rings",
                    "J.R.R. Tolkien",
                    1954,
                    "9780261102385",
                    29.90
            ));

            repository.save(new Book(
                    "Dune",
                    "Frank Herbert",
                    1965,
                    "9780441013593",
                    14.90
            ));

            repository.save(new Book(
                    "1984",
                    "George Orwell",
                    1949,
                    "9780451524935",
                    12.90
            ));

            System.out.println("Books saved to database!");
            System.out.println("Book: Harry Potter and the Philosopher's Stone, J.K. Rowling, 1997, 9780747532743, 19.90");
            System.out.println("Book: The Hobbit, J.R.R. Tolkien, 1937, 9780261102217, 15.90");
            System.out.println("Book: Clean Code, Robert C. Martin, 2008, 9780132350884, 39.90");
            System.out.println("Book: The Lord of the Rings, J.R.R. Tolkien, 1954, 9780261102385, 29.90");
            System.out.println("Book: Dune, Frank Herbert, 1965, 9780441013593, 14.90");
            System.out.println("Book: 1984, George Orwell, 1949, 9780451524935, 12.90");
        };
    }
}