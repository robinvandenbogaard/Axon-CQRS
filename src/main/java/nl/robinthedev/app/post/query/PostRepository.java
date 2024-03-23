package nl.robinthedev.app.post.query;

import org.springframework.data.jpa.repository.JpaRepository;

interface PostRepository extends JpaRepository<Post, Long> {

}
