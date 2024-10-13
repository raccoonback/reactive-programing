package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_48 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .just(1, 2, 4, 0, 6, 12)
        .map(num -> 12 / num)
        .onErrorContinue((error, num) -> {
          log.error("error: {}, num: {}", error.getMessage(), num);
        })
        .subscribe(
            data -> log.info("# onNext: {}", data),
            error -> log.error("# onError: ", error)
        );
  }

}

