package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_57 {

  public static void main(String[] args) throws InterruptedException {
    Flux.fromIterable(SampleData.books)
        .groupBy(book -> book.getAuthorName())
        .flatMap(
            groupedFlux -> groupedFlux
                .map(book -> book.getBookName() + "(" + book.getAuthorName() + ")")
                .collectList()
        )
        .subscribe(bookByAuthor -> log.info("# book by author: {}", bookByAuthor));
  }

}

