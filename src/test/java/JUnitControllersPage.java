import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.simbirsoft.Prictice.controllers.ControllersPage;
import ru.simbirsoft.Prictice.page.WebPage;
import ru.simbirsoft.Prictice.service.ServicePage;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ControllersPage.class)
@SpringBootTest
public class JUnitControllersPage {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ServicePage servicePage;

    WebPage webPage1 = new WebPage(1, "youtube.ru", "https://yandex.ru/", 124, 17);
    WebPage webPage2 = new WebPage(2, "vk.ru", "https://vk.ru/", 523, 153);
    WebPage webPage3 = new WebPage(3, "java.ru", "https://javarush.ru/", 755, 300);

    @Test
    public void getPageByIdTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/page")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
