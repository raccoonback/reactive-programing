package study.reactiveprograming.chapter10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Example10_11 {

  public static void main(String[] args) throws InterruptedException {
    doTask("task1")
        .subscribe(data -> log.info("# task1 onNext: {}", data));

    doTask("task2")
        .subscribe(data -> log.info("# task2 onNext: {}", data));

    Thread.sleep(100L);
  }

  private static Flux<Integer> doTask(String taskName) {
    return Flux.fromArray(new Integer[]{1, 3, 5, 7})
        .publishOn(Schedulers.newSingle("new-single", true))
        .filter(data -> data > 3)
        .doOnNext(data -> log.info("# {} doOnNext filter: {}", taskName, data))
        .map(data -> data * 10)
        .doOnNext(data -> log.info("# {} doOnNext map: {}", taskName, data));
  }
}
