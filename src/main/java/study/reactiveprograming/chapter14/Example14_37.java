package study.reactiveprograming.chapter14;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_37 {

  public static void main(String[] args) throws InterruptedException {
    getInfectedPersonPerHour(10, 21)
        .subscribe(tuples -> {
          Tuple3<Tuple2, Tuple2, Tuple2> tuples3 = (Tuple3) tuples;
          int sum = (int) tuples3.getT1().getT2() + (int) tuples3.getT2().getT2() + (int) tuples3.getT3().getT2();
          log.info("# onNext: {}, {}", tuples3.getT1().getT1(), sum);
        });
  }

  private static Flux getInfectedPersonPerHour(int start, int end) {
    return Flux.zip(
        Flux.fromIterable(SampleData.seoulInfected)
            .filter(t2 -> t2.getT1() >= start && t2.getT2() <= end),
        Flux.fromIterable(SampleData.incheonInfected)
            .filter(t2 -> t2.getT1() >= start && t2.getT1() <= end),
        Flux.fromIterable(SampleData.suwonInfected)
            .filter(t2 -> t2.getT1() >= start && t2.getT1() <= end)
    );
  }

}

