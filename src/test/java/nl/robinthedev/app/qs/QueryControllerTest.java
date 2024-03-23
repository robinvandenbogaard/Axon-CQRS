package nl.robinthedev.app.qs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class QueryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void listAllPosts() throws Exception {
    mockMvc.perform(get("/api/posts")).andExpect(status().isOk());
  }

  @Test
  void getSinglePost() throws Exception {
    mockMvc.perform(get("/api/posts/{postId}", 1)).andExpect(status().isOk());
  }
}