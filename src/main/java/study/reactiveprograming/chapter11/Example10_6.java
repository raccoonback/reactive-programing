package study.reactiveprograming.chapter11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Example10_6 {

  public static void main(String[] args) throws InterruptedException {
    final String key1 = "company";
    final String key2 = "name";

    Mono.deferContextual(ctx ->
            Mono.just(ctx.get(key1))
        )
        .publishOn(Schedulers.parallel())
        .contextWrite(context -> context.put(key2, "Bill"))
        .transformDeferredContextual(
            (mono, ctx) ->
                mono.map(data -> data + ", " + ctx.getOrDefault(key2, "Steve"))
        )
        .contextWrite(context -> context.put(key1, "Apple"))
        .subscribe(data -> log.info("# onNext: {}", data));

    Thread.sleep(500L);
  }
}
