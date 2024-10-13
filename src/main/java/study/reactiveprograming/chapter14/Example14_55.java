package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.math.MathFlux;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_55 {

  public static void main(String[] args) throws InterruptedException {
    Flux.range(1, 95)
        .buffer(10)
        .subscribe(buffer -> log.info("# onNext: {}", buffer));
  }

}

