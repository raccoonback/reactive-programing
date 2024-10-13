package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_29 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .just("Good", "Bad")
        .flatMap(feeling -> Flux
            .just("Morning", "Afternoon", "Evening")
            .map(time -> feeling + " " + time))
        .subscribe(log::info);

  }

}

