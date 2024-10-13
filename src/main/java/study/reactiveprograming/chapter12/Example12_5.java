package study.reactiveprograming.chapter12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example12_5 {

  public static void main(String[] args) throws InterruptedException {
    Flux.just(2, 4, 6, 8)
        .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
        .checkpoint("Example12_4.zipWith.checkpoint", true)
        .map(num -> num + 2)
        .checkpoint("Example12_4.map.checkpoint", true)
        .subscribe(
            data -> log.info("# onNext: {}", data),
            error -> log.error("# onError:", error)
        );
  }
}
