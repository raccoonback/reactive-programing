package study.reactiveprograming.chapter13;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

class TimeBasedTestExampleTest {

  @Test
  void getCOVID19CountTest() {
    StepVerifier
        .withVirtualTime(() ->
            TimeBasedTestExample.getCOVID19Count(
                Flux.interval(Duration.ofHours(1)).take(1)
            )
        )
        .expectSubscription()
        .then(() ->
            VirtualTimeScheduler
                .get()
                .advanceTimeBy(Duration.ofHours(1))
        )
        .expectNextCount(11)
        .expectComplete()
        .verify();
  }

  @Test
  void getCOVID19CountTimeoutTestT() {
    StepVerifier
        .create(
            TimeBasedTestExample.getCOVID19Count(
                Flux.interval(Duration.ofMinutes(1)).take(1)
            )
        )
        .expectSubscription()
        .expectNextCount(11)
        .expectComplete()
        .verify(Duration.ofSeconds(3));
  }

  @Test
  void getVoteCountTest() {
    StepVerifier
        .withVirtualTime(
            () -> TimeBasedTestExample.getVoteCount(
                Flux.interval(Duration.ofMinutes(1))
            )
        )
        .expectSubscription()
        .expectNoEvent(Duration.ofMinutes(1))
        .expectNoEvent(Duration.ofMinutes(1))
        .expectNoEvent(Duration.ofMinutes(1))
        .expectNoEvent(Duration.ofMinutes(1))
        .expectNoEvent(Duration.ofMinutes(1))
        .expectNextCount(5)
        .expectComplete()
        .verify();
  }
}