package nl.robinthedev.app.post.query;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@Sql("posts.sql")
@Transactional
@AutoConfigureMockMvc
class PostQueryControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void listAllPosts() throws Exception {
    mockMvc.perform(get("/api/posts"))
           .andExpect(status().isOk())
           .andExpect(content().json("""
            [{
              "title": "Story of my life",
              "text": "Let me tell you what happened.",
              comments: [{
                "text": "Unbelievable!"
              }]
            }]
            """));
  }

  @Test
  void getSinglePost() throws Exception {
    mockMvc.perform(get("/api/posts/{postId}", 1))
           .andExpect(status().isOk())
           .andExpect(content().json("""
            {
              "title": "Story of my life",
              "text": "Let me tell you what happened.",
              comments: [{
                "text": "Unbelievable!"
              }]
            }
            """));
  }
}