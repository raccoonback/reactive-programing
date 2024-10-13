package study.reactiveprograming.chapter14;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.net.URI;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@Slf4j
public class Example14_52 {

  public static void main(String[] args) throws InterruptedException {
    URI worldTimeUri = UriComponentsBuilder.newInstance()
        .scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/Asia/Seoul")
        .build()
        .encode()
        .toUri();

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    Mono.defer(
            () -> Mono.just(
                restTemplate.exchange(
                    worldTimeUri,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
                )
            )
        )
        .repeat(4)
        .elapsed()
        .map(response -> {
          DocumentContext jsonContext = JsonPath.parse(response.getT2().getBody());
          String dateTime = jsonContext.read("$.datetime");
          return Tuples.of(dateTime, response.getT1());
        })
        .subscribe(
            data -> log.info("now: {}, elapsed: {}", data.getT1(), data.getT2()),
            error -> log.error("# onError: ", error),
            () -> log.info("# onComplete")
        );
  }

}

