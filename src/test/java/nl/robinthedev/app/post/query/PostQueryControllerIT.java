package nl.robinthedev.app.post.query;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Sql("posts.sql")
@Transactional
@AutoConfigureMockMvc
class PostQueryControllerIT {

  @Autowired private MockMvc mockMvc;

  @Test
  void listAllPosts() throws Exception {
    mockMvc
        .perform(get("/api/posts"))
        .andExpect(status().isOk())
        .andExpect(
            content()
                .json(
                    """
                    [{
                      "id": "52b9d5d0-4959-4c3d-a390-b415ec9dfaaa",
                      "title": "Story of my life",
                      "text": "Let me tell you what happened.",
                      comments: [{
                        "id": "ce6ef61b-292a-4b30-a172-d5355a6c6e19",
                        "text": "Unbelievable!"
                      }]
                    }]"""));
  }
}
