package study.reactiveprograming.chapter14;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_9 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .generate(() -> 0, (state, sink) -> {
          sink.next(state);
          if(state == 10) {
            sink.complete();
          }

          return ++state;
        })
        .subscribe(data -> log.info("# onNext: {}", data));
  }

}
