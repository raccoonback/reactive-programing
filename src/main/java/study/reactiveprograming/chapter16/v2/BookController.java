package study.reactiveprograming.chapter16.v2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import study.reactiveprograming.chapter16.v1.Book;
import study.reactiveprograming.chapter16.v1.BookDto;
import study.reactiveprograming.chapter16.v1.BookMapper;
import study.reactiveprograming.chapter16.v1.BookService;

@RestController
@RequestMapping("/v1/books")
public class BookController {
  private final BookService bookService;
  private final BookMapper mapper;

  public BookController(BookService bookService, BookMapper mapper) {
    this.bookService = bookService;
    this.mapper = mapper;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono postBook(@RequestBody Mono<BookDto.Post> requestBody) {
    Mono<Book> result = bookService.createBook(requestBody);

    return result.flatMap(book -> Mono.just(mapper.bookToResponse(book)));
  }

  @PatchMapping("/{book-id}")
  public Mono patchBook(@PathVariable("book-id") long bookId, @RequestBody Mono<BookDto.Patch> requestBody) {
    Mono<Book> result = bookService.updateBook(bookId, requestBody);
    return result.flatMap(book -> Mono.just(mapper.bookToResponse(book)));
  }

  @GetMapping("{book-id}")
  public Mono getBook(@PathVariable("book-id") long bookId) {
    return bookService.findBook(bookId)
        .flatMap(book -> Mono.just(mapper.bookToResponse(book)));
  }
}
