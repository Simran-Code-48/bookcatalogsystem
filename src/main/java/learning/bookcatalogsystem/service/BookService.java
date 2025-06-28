package learning.bookcatalogsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import learning.bookcatalogsystem.model.Book;
import learning.bookcatalogsystem.repository.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository){
		this.bookRepository = bookRepository;
	}

	// get all books without pagination
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}

	public Book saveBook(Book book){
		return bookRepository.save(book);
	}
	public Book getBookById(Long id){
		return bookRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Book not found"));
	}
	//get by publication year
	public List<Book> getBooksByPublicationYear(int year){
		return bookRepository.findByPublicationYear(year);
	}
	public List<Book> getBooksByRangePublication(int start, int end){
		return bookRepository.findByPublicationYearBetween(start, end);
	}
	
	public Page<Book> getBooksByTitle(String title, int page, int size){
		Pageable pageable = Pageable.ofSize(size).withPage(page);
		return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
	}

	public Page<Book> getBooksByAuthor(String author, int page, int size) {
        Pageable pageable = PageRequest.of(page, size) ;
        return bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
    }

	public Page<Book> getBooksByGenre(String genre, int page, int size) {
		Pageable pageable = Pageable.ofSize(size).withPage(page);
		return bookRepository.findByGenreContainingIgnoreCase(genre, pageable);
	}

	public Page<Book> getBooksByGenres(List<String> genres, int page, int size) {
		Pageable pageable = Pageable.ofSize(size).withPage(page);
		return bookRepository.findByGenres(genres, pageable);
	}

	public Page<Book> getAllBooks(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageable);
    }

	public Page<Book> searchBooks(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.searchBooks(query, pageable);
    }

}
