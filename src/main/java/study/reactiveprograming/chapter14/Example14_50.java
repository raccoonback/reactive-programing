package study.reactiveprograming.chapter14;

import java.time.Duration;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import study.reactiveprograming.Book;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_50 {

  public static void main(String[] args) throws InterruptedException {
    getBooks()
        .collect(Collectors.toSet())
        .subscribe(
            bookSet -> bookSet.stream()
                .forEach(book -> log.info("book name: {}, price: {}", book.getBookName(), book.getPrice()))
        );

    Thread.sleep(12000);
  }

  private static Flux<Book> getBooks() {
    final int[] count = {0};
    return Flux
        .fromIterable(SampleData.books)
        .delayElements(Duration.ofMillis(500))
        .map(book -> {
          try {
            count[0]++;
            if (count[0] == 3) {
              Thread.sleep(2000);
            }
          } catch (InterruptedException e) {
          }

          return book;
        })
        .timeout(Duration.ofSeconds(2))
        .retry(1)
        .doOnNext(book -> log.info("# getBooks > doOnNext: {}, price: {}", book.getBookName(),
            book.getPrice()));
  }

}

