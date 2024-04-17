package nl.robinthedev.app.post.projection.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import nl.robinthedev.app.post.projection.core.model.Comment;

@Entity
@Table(name = "comment")
public class JpaComment implements Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private UUID publicId;

  @Column(length = 3000)
  private String text;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private JpaPost post;

  public JpaComment() {
    // required by Hibernate
  }

  public JpaComment(UUID publicId, String text) {
    this.publicId = publicId;
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setPost(JpaPost post) {
    this.post = post;
  }

  public UUID getPublicId() {
    return publicId;
  }

  @Override
  public UUID id() {
    return publicId;
  }

  @Override
  public String text() {
    return text;
  }
}
