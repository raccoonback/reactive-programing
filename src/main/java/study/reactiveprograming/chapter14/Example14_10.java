package study.reactiveprograming.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

@Slf4j
public class Example14_10 {

  public static void main(String[] args) throws InterruptedException {
    final int dan = 3;
    Flux
        .generate(() -> Tuples.of(dan, 1), (state, sink) -> {
          sink.next(state.getT1() +  " * " + state.getT2() + " = " + state.getT1() * state.getT2());
          if(state.getT2() == 9) {
            sink.complete();
          }

          return Tuples.of(state.getT1(), state.getT2() + 1);
        }, state -> log.info("# 구구단 {}단 {}번 종료!", state.getT1(), state.getT2()))
        .subscribe(data -> log.info("# onNext: {}", data));
  }

}
