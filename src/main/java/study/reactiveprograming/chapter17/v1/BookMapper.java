package study.reactiveprograming.chapter17.v1;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        implementationName = "bookMapperV2")
public interface BookMapper {
    Book bookPostToBook(BookDto.Post requestBody);
    Book bookPatchToBook(BookDto.Patch requestBody);
    BookDto.Response bookToResponse(Book book);
    List<BookDto.Response> booksToResponse(List<Book> books);
}
