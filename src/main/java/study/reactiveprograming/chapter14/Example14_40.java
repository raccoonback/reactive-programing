package study.reactiveprograming.chapter14;

import java.time.Duration;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_40 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .just("...", "---", "...")
        .map(code -> transformMorseCode(code))
        .collectList()
        .subscribe(
            list -> log.info(
                list.stream().collect(Collectors.joining())
            )
        );

  }

  private static String transformMorseCode(String morseCode) {
    return SampleData.morseCodeMap.get(morseCode);
  }

}

