package nl.robinthedev.app.post.query;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import nl.robinthedev.app.api.model.Comment;

import java.util.UUID;

@Entity
@Table(name = "comment")
class JpaComment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private UUID publicId;

  private String text;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private JpaPost post;

  public JpaComment() {
    //required by Hibernate
  }

  public JpaComment(Comment comment) {
    this.publicId = comment.publicId().commentId();
    this.text = comment.text();
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
}

