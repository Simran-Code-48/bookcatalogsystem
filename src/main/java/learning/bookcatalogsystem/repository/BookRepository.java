package learning.bookcatalogsystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import learning.bookcatalogsystem.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	// This interface will automatically provide CRUD operations for the Book entity
	// Additional custom query methods can be defined here if needed
	List<Book> findByPublicationYear(int year);

	List<Book> findByPublicationYearBetween(int start, int end);

	Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
	
	Page<Book> findByGenreContainingIgnoreCase(String genre, Pageable pageable);

	Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

	@Query("SELECT b FROM Book b WHERE b.genre IN :genres")
	Page<Book> findByGenres(@Param("genres") List<String> genres, Pageable pageable);

	@Query("SELECT b FROM Book b WHERE " +
           "LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(b.author) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Book> searchBooks(@Param("query") String query, Pageable pageable);
}