package study.reactiveprograming.chapter14;

import java.util.IllegalFormatException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import study.reactiveprograming.Book;
import study.reactiveprograming.SampleData;

@Slf4j
public class Example14_46 {

  public static void main(String[] args) throws InterruptedException {
    getBooks()
        .map(book -> book.getPenName().toUpperCase())
        .onErrorReturn(NullPointerException.class, "No pen name")
        .onErrorReturn(IllegalFormatException.class, "Illegal pen name")
        .subscribe(log::info);
  }

  private static Flux<Book> getBooks() {
    return Flux.fromIterable(SampleData.books);
  }

}

