package nl.robinthedev.app.post.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface PostRepository extends JpaRepository<JpaPost, Long> {

  Optional<JpaPost> findByPublicId(UUID uuid);
}
