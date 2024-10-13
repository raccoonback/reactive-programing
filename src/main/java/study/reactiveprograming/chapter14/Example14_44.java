package study.reactiveprograming.chapter14;

import java.util.zip.DataFormatException;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class Example14_44 {

  public static void main(String[] args) throws InterruptedException {
    Flux
        .just('a', 'b', 'c', '3', 'd')
        .flatMap(letter -> {
          try {
            return convert(letter);
          } catch (DataFormatException e) {
            return Flux.error(e);
          }
        })
        .subscribe(
            data -> log.info("# onNext: {}", data),
            error -> log.info("# onError: ", error)
        );
  }

  private static Mono<String> convert(char ch) throws DataFormatException {
    if(!Character.isAlphabetic(ch)) {
      throw new DataFormatException("Not Alphabetic");
    }

    return Mono.just("Converted to " + Character.toUpperCase(ch));
  }
}

