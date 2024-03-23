package nl.robinthedev.app.qs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
class QueryController {

  @Autowired
  private PostService postService;

  @GetMapping("/posts")
  public ResponseEntity<List<Post>> getAllPosts() {
    List<Post> posts = postService.getAllPosts();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @GetMapping("/posts/{postId}")
  public ResponseEntity<Post> getPostById(@PathVariable("postId") Long postId) {
    Post post = postService.getPostById(postId);
    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  @GetMapping("/posts/{postId}/comments")
  public ResponseEntity<List<Comment>> getCommentsForPost(@PathVariable("postId") Long postId) {
    List<Comment> comments = postService.getCommentsForPost(postId);
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }
}

