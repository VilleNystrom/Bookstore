package bookstore.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository kategoriaVarasto;

    @Test
    void kategorianLuontiTallentaaKategorian() {
        Category kategoria = kategoriaVarasto.save(new Category("Fantasia"));

        assertThat(kategoria.getId()).isNotNull();
        assertThat(kategoriaVarasto.findById(kategoria.getId())).isPresent();
    }

    @Test
    void kategorianPoistoPoistaaKategorian() {
        Category kategoria = kategoriaVarasto.save(new Category("Poistettava"));

        kategoriaVarasto.deleteById(kategoria.getId());

        assertThat(kategoriaVarasto.findById(kategoria.getId())).isEmpty();
    }

    @Test
    void hakuLoytaaKategorianNimenOsanKirjainkoostaRiippumatta() {
        kategoriaVarasto.save(new Category("Science Fiction"));
        kategoriaVarasto.save(new Category("Fantasy"));

        List<Category> loydetytKategoriat =
                kategoriaVarasto.findByNameContainingIgnoreCase("fiction");

        assertThat(loydetytKategoriat).hasSize(1);
        assertThat(loydetytKategoriat.get(0).getName()).isEqualTo("Science Fiction");
    }
}
