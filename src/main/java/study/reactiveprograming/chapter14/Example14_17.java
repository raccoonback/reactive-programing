package study.reactiveprograming.chapter14;

import static study.reactiveprograming.SampleData.getCovidVaccines;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;
import study.reactiveprograming.SampleData;
import study.reactiveprograming.SampleData.CovidVaccine;

@Slf4j
public class Example14_17 {

  public static void main(String[] args) throws InterruptedException {
    Map<CovidVaccine, Tuple2<CovidVaccine, Integer>> vaccineMap = getCovidVaccines();

    Flux
        .fromIterable(SampleData.coronaVaccineNames)
        .filterWhen(
            vaccine -> Mono.just(vaccineMap.get(vaccine).getT2() >= 3_000_000)
                .publishOn(Schedulers.parallel())
        )
        .subscribe(data -> log.info("# onNext: {}", data));

    Thread.sleep(1000);
  }

}

