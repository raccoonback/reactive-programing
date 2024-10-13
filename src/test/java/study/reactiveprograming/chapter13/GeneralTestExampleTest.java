package study.reactiveprograming.chapter13;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.test.publisher.TestPublisher;
import reactor.test.publisher.TestPublisher.Violation;

class GeneralTestExampleTest {

  @Test
  void sayHelloTest() {
    StepVerifier
        .create(GeneralTestExample.sayHello())
        .expectSubscription()
        .as("# expect subscription")
        .expectNext("Hi")
        .as("# expect Hi")
        .expectNext("Reactor")
        .as("# expect Reactor")
        .verifyComplete();
  }

  @Test
  void divideByTwoTest() {
    Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);
    StepVerifier
        .create(GeneralTestExample.divideByTwo(source))
        .expectSubscription()
        .expectNext(1)
        .expectNext(2)
        .expectNext(3)
        .expectNext(4)
//        .expectNext(1, 2, 3, 4)
        .expectError()
        .verify();
  }

  @Test
  void divideByTwoTestUsedTestPublisher() {
    TestPublisher<Integer> source = TestPublisher.create();

    StepVerifier
        .create(GeneralTestExample.divideByTwo(source.flux()))
        .expectSubscription()
        .then(() -> source.emit(2, 4, 6, 8, 10))
        .expectNext(1, 2, 3, 4)
        .expectError()
        .verify();
  }

  @Test
  void divideByTwoErrorUsedTestPublisher() {
//    TestPublisher<Integer> source = TestPublisher.create();
      TestPublisher<Integer> source = TestPublisher.createNoncompliant(Violation.ALLOW_NULL);

    StepVerifier
        .create(GeneralTestExample.divideByTwo(source.flux()))
        .expectSubscription()
        .then(() -> {
          getDataSource().stream()
              .forEach(data -> source.next(data));
          source.complete();
        })
        .expectNext(1, 2, 3, 4, 5)
        .expectComplete()
        .verify();
  }

  private static List<Integer> getDataSource() {
    return Arrays.asList(2, 4, 6, 8, null);
  }

  @Test
  void takeNumberTest() {
    Flux<Integer> source = Flux.range(0, 1000);
    StepVerifier
        .create(GeneralTestExample.takeNumber(source, 500),
            StepVerifierOptions.create().scenarioName("Verify from 0 to 499"))
        .expectSubscription()
        .expectNext(0)
        .expectNextCount(498)
        .expectNext(500)
        .expectComplete()
        .verify();
  }
}