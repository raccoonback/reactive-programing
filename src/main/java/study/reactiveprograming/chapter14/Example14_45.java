package study.reactiveprograming.chapter14;

import java.util.zip.DataFormatException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.reactiveprograming.Book;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_45 {

  public static void main(String[] args) throws InterruptedException {
    getBooks()
        .map(book -> book.getPenName().toUpperCase())
        .onErrorReturn("No pen name")
        .subscribe(log::info);
  }

  private static Flux<Book> getBooks() {
    return Flux.fromIterable(SampleData.books);
  }

}

