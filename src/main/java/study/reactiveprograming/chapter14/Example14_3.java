package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_3 {

  public static void main(String[] args) {
    Flux.fromStream(() -> SampleData.coinNames.stream())
        .filter(
            coin -> coin.equals("BTC") || coin.equals("ETH")
        )
        .subscribe(
            coin -> log.info("coin 명: {}", coin),
            error -> log.error("# onError", error),
            () -> log.info("# onComplete")
        );
  }

}
