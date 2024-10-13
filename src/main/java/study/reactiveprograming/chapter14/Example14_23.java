package study.reactiveprograming.chapter14;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_23 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .fromIterable(SampleData.btcTopPricesPerYear)
        .takeLast(2)
        .subscribe(tuple -> log.info("# onNext: {}, {}", tuple.getT1(), tuple.getT2()));

    Thread.sleep(3000);
  }

}

