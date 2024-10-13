package study.reactiveprograming.chapter13;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class RecordTestExampleTest {

  @Test
  void getCityTest() {
    StepVerifier
        .create(
            RecordTestExample.getCapitalizedCountry(
                Flux.just("korea", "england", "canada", "india")
            )
        )
        .expectSubscription()
        .recordWith(ArrayList::new)
        .thenConsumeWhile(country -> !country.isEmpty())
        .consumeRecordedWith(
            countries -> {
              assertThat(
                  countries.stream()
                      .allMatch(country -> Character.isUpperCase(country.charAt(0)))
              ).isTrue();
            }
        )
        .expectComplete()
        .verify();
  }

  @Test
  void getCountryTest() {
    StepVerifier
        .create(
            RecordTestExample.getCapitalizedCountry(
                Flux.just("korea", "england", "canada", "india")
            )
        )
        .expectSubscription()
        .recordWith(ArrayList::new)
        .thenConsumeWhile(country -> !country.isEmpty())
        .expectRecordedMatches(
            countries ->
                countries.stream()
                    .allMatch(
                        country -> Character.isUpperCase(country.charAt(0))
                    )
        )
        .expectComplete()
        .verify();
  }
}