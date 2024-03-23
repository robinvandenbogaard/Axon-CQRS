package nl.robinthedev.app.post.query;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "post")
class JpaPost {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private UUID publicId;

  private String title;

  private String content;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private List<JpaComment> comments;

  protected JpaPost() {
    //required by Hibernate
  }

  public JpaPost(UUID publicId, String title, String content) {
    this.publicId = publicId;
    this.title = title;
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public List<JpaComment> getComments() {
    return comments;
  }

  public UUID getPublicId() {
    return publicId;
  }
}