package study.reactiveprograming.chapter16.v1;

import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;
import study.reactiveprograming.chapter16.v1.BookDto.Response;

@Mapper(componentModel = "spring")
public interface BookMapper {

  Book bookPostToBook(BookDto.Post requestBody);

  Book bookPatchToBook(BookDto.Patch requestBody);

  BookDto.Response bookToResponse(Book book);

  default Mono<Response> bookToBookResponse(Mono<Book> mono) {
    return mono.flatMap(book -> Mono.just(bookToResponse(book)));
  }
}
