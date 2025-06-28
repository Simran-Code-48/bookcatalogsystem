package learning.bookcatalogsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import learning.bookcatalogsystem.model.Book;
import learning.bookcatalogsystem.service.BookService;

@RestController
@RequestMapping("api/books")
public class BookController {
	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService){
		this.bookService = bookService;
	}

	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book book){
		Book savedBook = bookService.saveBook(book);
		return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id){
		Book book = bookService.getBookById(id);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	@GetMapping("/year/{year}")
	public ResponseEntity<List<Book>> getBookByPublicationYear(@PathVariable int year){
		List<Book> books = bookService.getBooksByPublicationYear(year);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("/range")
	public ResponseEntity<List<Book>> getBookByRangePublicationYear(
			@RequestParam int start, 
			@RequestParam int end){
		List<Book> books = bookService.getBooksByRangePublication(start, end);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("/title")
	public ResponseEntity<Page<Book>> getBooksByTitle(
			@RequestParam String title,
			@RequestParam(defaultValue = "0")int page, 
			@RequestParam(defaultValue = "10")int size){
		Page<Book> books = bookService.getBooksByTitle(title, page, size);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("/author")
	public ResponseEntity<Page<Book>> getBooksByAuthor(
			@RequestParam String author,
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size){
		Page<Book> books = bookService.getBooksByAuthor(author, page, size);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("/genre")
	public ResponseEntity<Page<Book>> getBooksByGenre(
			@RequestParam String genre,
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "10") int size){
		Page<Book> books = bookService.getBooksByGenre(genre, page, size);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("genres")
	public ResponseEntity<Page<Book>> getBooksByGenres(
			@RequestParam List<String> genres,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
		Page<Book> books = bookService.getBooksByGenres(genres, page, size);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        
        Page<Book> books = bookService.getAllBooks(page, size, sortBy, direction);
        return ResponseEntity.ok(books);
    }
	@GetMapping("/search")
    public ResponseEntity<Page<Book>> searchBooks(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Page<Book> books = bookService.searchBooks(q, page, size);
        return ResponseEntity.ok(books);
    }
}
