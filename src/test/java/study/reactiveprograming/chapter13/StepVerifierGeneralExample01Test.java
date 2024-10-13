package study.reactiveprograming.chapter13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class StepVerifierGeneralExample01Test {

  @Test
  void sayHelloReactorTest() {
    StepVerifier
        .create(Mono.just("Hello Reactor"))
        .expectNext("Hello Reactor")
        .expectComplete()
        .verify();
  }
}