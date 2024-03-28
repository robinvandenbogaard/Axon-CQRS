package nl.robinthedev.app.post.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import nl.robinthedev.app.api.messaging.post.command.AddComment;
import nl.robinthedev.app.api.messaging.post.command.CreatePost;
import nl.robinthedev.app.api.messaging.post.command.UpdateComment;
import nl.robinthedev.app.api.messaging.post.model.CommentId;
import nl.robinthedev.app.api.messaging.post.model.PostId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PostCommandController.class)
class PostCommandControllerTest {

  private static final UUID POST_UUID = UUID.randomUUID();
  private static final PostId POST_ID = new PostId(POST_UUID);
  private static final UUID COMMENT_UUID = UUID.randomUUID();
  private static final CommentId COMMENT_ID = new CommentId(COMMENT_UUID);

  @Autowired private MockMvc mockMvc;

  @MockBean private UuidGenerator idGenerator;

  @MockBean private CommandGateway gateway;

  @BeforeEach
  void setUp() {
    when(idGenerator.generateId()).thenReturn(POST_UUID);
  }

  @Test
  void creatPost() throws Exception {
    mockMvc
        .perform(
            post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                      "title": "My Title",
                      "content": "My message."
                    }"""))
        .andExpect(status().isCreated());

    verify(gateway).send(new CreatePost(POST_ID, "My Title", "My message."));
  }

  @Test
  void addComment() throws Exception {
    when(idGenerator.generateId()).thenReturn(COMMENT_UUID);
    mockMvc
        .perform(
            post("/api/posts/{postId}/addcomment", POST_UUID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                      "text": "My remark."
                    }"""))
        .andExpect(status().isCreated());

    verify(gateway).send(new AddComment(POST_ID, COMMENT_ID, "My remark."));
  }

  @Test
  void updateComment() throws Exception {
    mockMvc
        .perform(
            put("/api/posts/{postId}/comments/{commentId}/update", POST_UUID, COMMENT_UUID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                      "text": "My new remark."
                    }"""))
        .andExpect(status().isOk());

    verify(gateway).send(new UpdateComment(POST_ID, COMMENT_ID, "My new remark."));
  }
}
