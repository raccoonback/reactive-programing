package study.reactiveprograming.chapter14;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Example14_1 {

  public static void main(String[] args) {
    Mono.justOrEmpty(null)
//    Mono.justOrEmpty(Optional.ofNullable(null))
        .subscribe(
            data -> log.info("# onNext: {}", data),
            error -> log.error("# onError", error),
            () -> log.info("# onComplete")
        );
  }

}
