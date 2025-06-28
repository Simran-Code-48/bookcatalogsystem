package learning.bookcatalogsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;
	
	private String genre;

	@Column(nullable = false)
	private String author;
	
	@Column(name="publication_date")
	private int publicationYear;
	@Column(name="page_count")
	private Integer pageCount;
	@Column(name="created_at", updatable = false)
	private LocalDate createdAt = LocalDate.now();

	public Book(){
	}

	public Book(String title, String genre, String author, int publicationYear, Integer pageCount){
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.publicationYear = publicationYear;
		this.pageCount = pageCount;
	}
	
	public Long getId(){ return id; }
	public void setId(Long id){ this.id = id; }

	public String getTitle(){ return title; }
	public void setTitle(String title){ this.title = title; }

	public String getGenre(){ return genre; }
	public void setGenre(String genre){ this.genre = genre; }

	public String getAuthor(){ return author; }
	public void setAuthor(String author){ this.author = author; }

	public int getPublicationYear(){ return publicationYear; }
	public void setPublicationYear(int publicationYear){ this.publicationYear = publicationYear; }
	
	public Integer getPageCount() { return pageCount; }
    public void setPageCount(Integer pageCount) { this.pageCount = pageCount; }
    
    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

	@Override
	public String toString(){
		return "Book{" + 
				"id=" + id +
				", title='" + title + '\'' +
				", genre='" + genre + '\'' +
				", author='" + author + '\'' +
				", publicationDate=" + publicationYear +
				", pageCount=" + pageCount +
				", createdAt=" + createdAt +
				'}';
	}
}
