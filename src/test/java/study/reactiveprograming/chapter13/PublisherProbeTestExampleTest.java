package study.reactiveprograming.chapter13;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

class PublisherProbeTestExampleTest {

  @Test
  void publisherProbeTest() {
    PublisherProbe<String> probe =
        PublisherProbe.of(PublisherProbeTestExample.supplyStandbyPower());

    StepVerifier
        .create(
            PublisherProbeTestExample.processTask(
                PublisherProbeTestExample.supplyMainPower(),
                probe.mono()
            )
        )
        .expectNextCount(1)
        .verifyComplete();

    probe.assertWasSubscribed();
    probe.assertWasRequested();
    probe.assertWasNotCancelled();
  }
}