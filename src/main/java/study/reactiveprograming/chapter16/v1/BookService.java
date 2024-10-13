package study.reactiveprograming.chapter16.v1;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BookService {
  public Mono<Book> createBook(Book book) {
    return Mono.just(book);
  }

  public Mono<Book> updateBook(Book book) {
    return Mono.just(book);
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
