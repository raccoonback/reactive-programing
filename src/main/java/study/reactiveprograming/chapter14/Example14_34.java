package study.reactiveprograming.chapter14;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_34 {

  public static void main(String[] args) throws InterruptedException {
    String[] useStates = {
        "Ohio", "Michigan", "New Jersey", "Illinois", "New Hampshire",
        "Virginia", "Vermont", "North Carolina", "Ontario", "Georgia"
    };

    Flux
        .merge(getMeltDownRecoveryMessage(useStates))
        .subscribe(log::info);

    Thread.sleep(2000);
  }

  private static List<Mono<String>> getMeltDownRecoveryMessage(String[] useStates) {
    List<Mono<String>> messages = new ArrayList<>();
    for (String state : useStates) {
      messages.add(SampleData.nppMap.get(state));
    }

    return messages;
  }
}

