package study.reactiveprograming.chapter13;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class ContextTestExampleTest {

  @Test
  void getSecretMessageTest() {
    Mono<String> source = Mono.just("hello");

    StepVerifier
        .create(
            ContextTestExample.getSecretMessage(source)
                .contextWrite(context -> context.put("secretMessage", "Hello, Reactor"))
                .contextWrite(context -> context.put("secretKey", "aGVsbG8"))
        )
        .expectSubscription()
        .expectAccessibleContext()
        .hasKey("secretKey")
        .hasKey("secretMessage")
        .hasKey("secretMessage")
        .then()
        .expectNext("Hello, Reactor")
        .expectComplete()
        .verify();
  }
}