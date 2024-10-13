package study.reactiveprograming.chapter14;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.net.URI;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@Slf4j
public class Example14_53 {

  public static void main(String[] args) throws InterruptedException {
    Flux.range(1, 11)
        .window(3)
        .flatMap(
            flux -> {
              log.info("==================");
              return flux;
            }
        )
        .subscribe(
            new BaseSubscriber<>() {
              @Override
              protected void hookOnSubscribe(Subscription subscription) {
                subscription.request(2);
              }

              @Override
              protected void hookOnNext(Integer value) {
                log.info("# onNext: {}", value);
                request(2);
              }
            }
        );
  }

}

