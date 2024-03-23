package nl.robinthedev.app.post.query;

import org.springframework.data.jpa.repository.JpaRepository;

interface CommentRepository extends JpaRepository<JpaComment, Long> {
}
