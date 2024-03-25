package nl.robinthedev.app.post.projection;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class PostQueryController {

  @Autowired private PostService postService;

  @GetMapping("/posts")
  public ResponseEntity<List<RPost>> getAllPosts() {
    List<RPost> posts = postService.getAllPosts();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }
}
