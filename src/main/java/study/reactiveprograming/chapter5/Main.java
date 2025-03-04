package study.reactiveprograming.chapter5;

import reactor.core.publisher.Flux;

public class Main {

  public static void main(String[] args) {
    Flux<String> sequence = Flux.just("Hello", "World");
    sequence.map(it -> it.toUpperCase())
        .subscribe(System.out::println);
  }
}
