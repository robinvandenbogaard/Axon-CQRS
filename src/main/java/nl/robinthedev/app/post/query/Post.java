package nl.robinthedev.app.post.query;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String content;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private List<Comment> comments;

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public List<Comment> getComments() {
    return comments;
  }
}
