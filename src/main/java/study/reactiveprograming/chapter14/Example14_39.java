package study.reactiveprograming.chapter14;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Slf4j
public class Example14_39 {

  public static void main(String[] args) throws InterruptedException {
    restartApplicationServer()
        .and(restartDBServer())
        .subscribe(
            data -> log.info("# onNext: {}", data),
            error -> log.info("# onError: {}", error),
            () -> log.info(
                "# sent an email to Administrator: All Servers are restarted successfully")
        );

    Thread.sleep(6000);
  }

  private static Mono<String> restartApplicationServer() {
    return Mono
        .just("Application Server was restarted successfully")
        .delayElement(Duration.ofSeconds(2))
        .doOnNext(log::info);
  }

  private static Publisher<String> restartDBServer() {
    return Mono
        .just("DB Server was restarted successfully")
        .delayElement(Duration.ofSeconds(4))
        .doOnNext(log::info);
  }

}

