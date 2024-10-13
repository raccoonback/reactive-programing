package study.reactiveprograming.chapter14;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_8 {

  public static void main(String[] args) throws InterruptedException {
    Path path = Paths.get("src/main/java/study/reactiveprograming/chapter14/Example14_8.java");

    Flux
        .using(() -> Files.lines(path), Flux::fromStream, Stream::close)
        .subscribe(log::info);
  }

}
