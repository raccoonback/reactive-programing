package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_59 {

  public static void main(String[] args) throws InterruptedException {
    Flux.fromIterable(SampleData.books)
        .groupBy(book -> book.getAuthorName())
        .flatMap(
            groupedFlux -> Mono.just(groupedFlux.key())
                .zipWith(
                    groupedFlux
                        .map(book -> (int) (book.getPrice() * book.getStockQuantity() * 0.1))
                        .reduce((y1, y2) -> y1 + y2),
                    (authorName, sumRoyalty) -> authorName + "'s royalty: " + sumRoyalty
                )
        )
        .subscribe(log::info);
  }

}

