package study.reactiveprograming.chapter12;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Example12_2 {

  public static void main(String[] args) throws InterruptedException {
    Flux.just(2, 4, 6, 8)
        .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
        .map(num -> num + 2)
        .checkpoint()
        .subscribe(
            data -> log.info("# onNext: {}", data),
            error -> log.error("# onError:", error)
        );
  }
}
