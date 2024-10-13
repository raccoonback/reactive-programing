package study.reactiveprograming.chapter16.v2;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import study.reactiveprograming.chapter16.v1.Book;
import study.reactiveprograming.chapter16.v1.BookDto;
import study.reactiveprograming.chapter16.v1.BookMapper;

@Service("bookServiceV2")
public class BookService {

  private final BookMapper mapper;

  public BookService(BookMapper mapper) {
    this.mapper = mapper;
  }

  public Mono<Book> createBook(Mono<BookDto.Post> book) {
    return book.flatMap(post -> Mono.just(mapper.bookPostToBook(post)));
  }

  public Mono<Book> updateBook(long bookId, Mono<BookDto.Patch> book) {
    return book.flatMap(patch -> {
      patch.setBookId(bookId);
      return Mono.just(mapper.bookPatchToBook(patch));
    });
  }

  public Mono<Book> findBook(long bookId) {
    return Mono.just(
      new Book(
          bookId,
          "Java 고급",
          "Advanced Java",
          "Kevin",
          "111-111-11111-111-1",
          "Jav 중급 프로그래밍 마스터",
          "2022003-22",
          LocalDateTime.now(),
          LocalDateTime.now()
      )
    );
  }
}
