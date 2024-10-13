package study.reactiveprograming.chapter14;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import study.reactiveprograming.Book;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_47 {

  public static void main(String[] args) throws InterruptedException {
    final String keyword = "DDD";
    getBooksFromCache(keyword)
        .onErrorResume(error -> getBooksFromDatabase(keyword))
        .subscribe(
            data -> log.info("# onNext: {}", data.getBookName()),
            error -> log.error("# onError: ", error)
        );
  }

  private static Flux<Book> getBooksFromCache(String keyword) {
    return Flux
        .fromIterable(SampleData.books)
        .filter(book -> book.getBookName().contains(keyword))
        .switchIfEmpty(
            Flux.error(
                new NoSuchBookException("No such Book")
            )
        );
  }

  private static Flux<Book> getBooksFromDatabase(String keyword) {
    ArrayList<Book> books = new ArrayList<>(SampleData.books);
    books.add(
        new Book("DDD: Domain Driven Design", "Joy", "ddd-man", 35000, 200)
    );

    return Flux.fromIterable(books)
        .filter(book -> book.getBookName().contains(keyword))
        .switchIfEmpty(Flux.error(new NoSuchBookException("No such Book")));
  }

  private static class NoSuchBookException extends RuntimeException {

    public NoSuchBookException(String noSuchBook) {
    }
  }

}

