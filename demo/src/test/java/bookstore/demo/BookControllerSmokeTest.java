package bookstore.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerSmokeTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository kirjaVarasto;

    @Test
    void etusivuAvautuu() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void kirjaluetteloAvautuu() throws Exception {
        mockMvc.perform(get("/booklist"))
                .andExpect(status().isOk())
                .andExpect(view().name("booklist"));
    }

    @Test
    void lisayslomakeAvautuu() throws Exception {
        mockMvc.perform(get("/addbook"))
                .andExpect(status().isOk())
                .andExpect(view().name("addbook"));
    }

    @Test
    void kirjanTallennusOhjaaKirjaluetteloon() throws Exception {
        mockMvc.perform(post("/savebook")
                        .param("title", "Savutesti kirja")
                        .param("author", "Testaaja")
                        .param("publicationYear", "2025")
                        .param("isbn", "9999999999999")
                        .param("price", "9.99"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/booklist"));
    }

    @Test
    void kirjanPoistoOhjaaKirjaluetteloon() throws Exception {
        Book kirja = kirjaVarasto.save(
                new Book("Poistettava savutestikirja", "Testaaja", 2025,
                        "8888888888888", 8.99, null));

        mockMvc.perform(get("/delete/{id}", kirja.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/booklist"));
    }
}
