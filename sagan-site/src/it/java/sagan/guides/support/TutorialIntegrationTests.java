package sagan.guides.support;

import sagan.support.Fixtures;
import saganx.AbstractIntegrationTests;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TutorialIntegrationTests extends AbstractIntegrationTests {

    @Test
    public void getTutorialRootPage() throws Exception {
        stubRestClient.putResponse("/repos/spring-guides/tut-my-tutorial/contents/README.md", "html");

        mockMvc.perform(get("/guides/tutorials/my-tutorial"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/html"));

    }

    @Test
    public void getTutorialPage1() throws Exception {
        stubRestClient.putResponse("/repos/spring-guides/tut-my-tutorial/contents/1/README.md", "html");

        mockMvc.perform(get("/guides/tutorials/my-tutorial/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/html"));

    }

    @Test
    public void getTutorialPage100() throws Exception {
        stubRestClient.putResponse("/repos/spring-guides/tut-my-tutorial/contents/100/README.md", "html");

        mockMvc.perform(get("/guides/tutorials/my-tutorial/100"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/html"));

    }

    @Test
    public void getInvalidTutorialPages() throws Exception {
        mockMvc.perform(get("/guides/tutorials/my-tutorial/0"))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/guides/tutorials/my-tutorial/NaN"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getImage() throws Exception {
        String imageJson = Fixtures.load("/fixtures/github/imageResponse.json");

        stubRestClient.putResponse("/repos/spring-guides/tut-my-tutorial/contents/images/image.png", imageJson);

        mockMvc.perform(get("/guides/tutorials/my-tutorial/images/image.png")).andExpect(status().isOk());

    }

}
