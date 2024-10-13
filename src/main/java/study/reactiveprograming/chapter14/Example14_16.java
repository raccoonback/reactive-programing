package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_16 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .fromIterable(SampleData.btcTopPricesPerYear)
        .filter(tuple -> tuple.getT2() > 20_000_000)
        .subscribe(data -> log.info(data.getT1() + ":" + data.getT2()));
  }

}

