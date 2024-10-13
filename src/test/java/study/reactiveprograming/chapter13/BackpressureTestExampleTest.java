package study.reactiveprograming.chapter13;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class BackpressureTestExampleTest {

  @Test
  void generateNumberErrorTest() {
    StepVerifier
        .create(BackpressureTestExample.generateNumber(), 1L)
        .thenConsumeWhile(num -> num >= 1)
        .verifyComplete();
  }

  @Test
  void generateNumberTest() {
    StepVerifier
        .create(BackpressureTestExample.generateNumber(), 1L)
        .thenConsumeWhile(num -> num >= 1)
        .expectError()
        .verifyThenAssertThat()
        .hasDroppedElements();
  }


}