package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Example14_31 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .concat(Flux.just(1, 2, 3), Flux.just(4, 5))
        .subscribe(data -> log.info("# onNext: {}", data));
  }

}

