package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_58 {

  public static void main(String[] args) throws InterruptedException {
    Flux.fromIterable(SampleData.books)
        .groupBy(
            book -> book.getAuthorName(),
            book -> book.getBookName() + "(" + book.getAuthorName() + ")"
        )
        .flatMap(groupedFlux -> groupedFlux.collectList())
        .subscribe(bookByAuthor -> log.info("# book by author: {}", bookByAuthor));
  }

}

