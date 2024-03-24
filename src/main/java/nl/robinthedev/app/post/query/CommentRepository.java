package nl.robinthedev.app.post.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface CommentRepository extends JpaRepository<JpaComment, Long> {

  Optional<JpaComment> findByPublicId(UUID uuid);
}
