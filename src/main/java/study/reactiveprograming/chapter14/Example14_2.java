package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_2 {

  public static void main(String[] args) {
    Flux.fromIterable(SampleData.coins)
        .subscribe(
            coin -> log.info("coin 명: {}, 현재가: {}", coin.getT1(), coin.getT2()),
            error -> log.error("# onError", error),
            () -> log.info("# onComplete")
        );
  }

}
