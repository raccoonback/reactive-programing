package study.reactiveprograming.chapter14;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import study.reactiveprograming.SampleData;
import study.reactiveprograming.SampleData.CovidVaccine;

@Slf4j
public class Example14_32 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .concat(
            Flux.fromIterable(getViralVector()),
            Flux.fromIterable(getMRNA()),
            Flux.fromIterable(getSubunit())
        )
        .subscribe(data -> log.info("# onNext: {}", data));
  }

  private static List<Tuple2<CovidVaccine, Integer>> getViralVector() {
    return SampleData.viralVectorVaccines;
  }

  private static List<Tuple2<CovidVaccine, Integer>> getMRNA() {
    return SampleData.mRNAVaccines;
  }

  private static List<Tuple2<CovidVaccine, Integer>> getSubunit() {
    return SampleData.subunitVaccines;
  }

}

