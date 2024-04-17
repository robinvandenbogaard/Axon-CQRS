package nl.robinthedev.app.post.projection.database;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import nl.robinthedev.app.post.projection.core.model.Comment;
import nl.robinthedev.app.post.projection.core.model.Post;

@Entity
@Table(name = "post")
public class JpaPost implements Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private UUID publicId;

  private String title;

  @Column(length = 3000)
  private String content;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<JpaComment> comments = new ArrayList<>();

  protected JpaPost() {
    // required by Hibernate
  }

  public JpaPost(UUID publicId, String title, String content) {
    this.publicId = publicId;
    this.title = title;
    this.content = content;
  }

  public List<JpaComment> getComments() {
    return comments;
  }

  public void addComment(JpaComment comment) {
    this.comments.add(comment);
    comment.setPost(this);
  }

  @Override
  public UUID id() {
    return publicId;
  }

  @Override
  public String title() {
    return title;
  }

  @Override
  public String text() {
    return content;
  }

  @Override
  public List<? extends Comment> comments() {
    return this.comments;
  }
}
