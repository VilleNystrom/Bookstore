package bookstore.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository kirjaVarasto;

    @Test
    void kirjanLuontiTallentaaKirjan() {
        Book kirja = new Book("Testivetoinen kehitys", "Kent Beck", 2002,
                "9780321146533", 34.50, null);

        Book tallennettuKirja = kirjaVarasto.save(kirja);

        assertThat(tallennettuKirja.getId()).isNotNull();
        assertThat(kirjaVarasto.findById(tallennettuKirja.getId())).isPresent();
    }

    @Test
    void kirjanPoistoPoistaaKirjan() {
        Book kirja = kirjaVarasto.save(new Book(
                "Poistettava kirja", "Testaaja", 2024, "1111111111111", 10.00, null));

        kirjaVarasto.deleteById(kirja.getId());

        assertThat(kirjaVarasto.findById(kirja.getId())).isEmpty();
    }

    @Test
    void hakuLoytaaOtsikonOsanKirjainkoostaRiippumatta() {
        kirjaVarasto.save(new Book("Clean Code", "Robert C. Martin", 2008,
                "9780132350884", 39.90, null));
        kirjaVarasto.save(new Book("The Hobbit", "J.R.R. Tolkien", 1937,
                "9780261102217", 15.90, null));

        List<Book> loydetytKirjat =
                kirjaVarasto.findByTitleContainingIgnoreCase("clean");

        assertThat(loydetytKirjat).hasSize(1);
        assertThat(loydetytKirjat.get(0).getTitle()).isEqualTo("Clean Code");
    }
}
