package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_4 {

  public static void main(String[] args) {
    Flux
        .range(5, 10)
        .subscribe(data -> log.info("{}", data));
  }

}
