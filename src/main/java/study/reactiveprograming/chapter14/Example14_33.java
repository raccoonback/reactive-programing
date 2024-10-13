package study.reactiveprograming.chapter14;

import java.time.Duration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import study.reactiveprograming.SampleData;
import study.reactiveprograming.SampleData.CovidVaccine;

@Slf4j
public class Example14_33 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .merge(
            Flux.just(1, 2, 3, 4).delayElements(Duration.ofMillis(300)),
            Flux.just(5, 6, 7).delayElements(Duration.ofMillis(500))
        )
        .subscribe(data -> log.info("# onNext: {}", data));

    Thread.sleep(2000);
  }
}

