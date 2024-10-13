package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.math.MathFlux;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_54 {

  public static void main(String[] args) throws InterruptedException {
    Flux.fromIterable(SampleData.monthlyBookSales2021)
        .window(3)
        .flatMap(flux -> MathFlux.sumInt(flux))
        .subscribe(
            new BaseSubscriber<>() {
              @Override
              protected void hookOnSubscribe(Subscription subscription) {
                subscription.request(2);
              }

              @Override
              protected void hookOnNext(Integer value) {
                log.info("# onNext: {}", value);
                request(2);
              }
            }
        );
  }

}

