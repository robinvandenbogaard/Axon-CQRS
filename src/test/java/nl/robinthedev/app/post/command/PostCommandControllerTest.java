package nl.robinthedev.app.post.command;

import nl.robinthedev.app.api.messaging.command.AddComment;
import nl.robinthedev.app.api.messaging.command.CreatePost;
import nl.robinthedev.app.api.messaging.command.UpdateComment;
import nl.robinthedev.app.api.model.CommentId;
import nl.robinthedev.app.api.model.PostId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostCommandController.class)
class PostCommandControllerTest {

  public static final UUID ID = UUID.randomUUID();
  private static final PostId POST_ID = new PostId(ID);

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UuidGenerator idGenerator;

  @MockBean
  private CommandGateway gateway;

  @BeforeEach
  void setUp() {
    when(idGenerator.generateId()).thenReturn(ID);
  }

  @Test
  void creatPost() throws Exception {
    mockMvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON).content("""
            {
              "title": "My Title",
              "content": "My message."
            }""")).andExpect(status().isCreated());

    verify(gateway).send(new CreatePost(POST_ID, "My Title", "My message."));
  }

  @Test
  void addComment() throws Exception {
    mockMvc.perform(post("/api/posts/68eefe7a-6ef3-4003-98ea-87a6eab49756").contentType(MediaType.APPLICATION_JSON)
                                                                           .content("""
                                                                                   {
                                                                                     "text": "My remark."
                                                                                   }"""))
           .andExpect(status().isCreated());

    verify(gateway).send(new AddComment(new PostId(UUID.fromString("68eefe7a-6ef3-4003-98ea-87a6eab49756")), new CommentId(ID), "My remark."));
  }

  @Test
  void updateComment() throws Exception {
    String commentId = "68eefe7a-6ef3-4003-98ea-87a6eab49756";
    mockMvc.perform(put("/api/comments/"+commentId).contentType(MediaType.APPLICATION_JSON)
                                                                           .content("""
                                                                                   {
                                                                                     "text": "My new remark."
                                                                                   }"""))
           .andExpect(status().isOk());

    verify(gateway).send(new UpdateComment(CommentId.fromString(commentId), "My new remark."));
  }
}