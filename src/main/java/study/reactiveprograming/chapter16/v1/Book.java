package study.reactiveprograming.chapter16.v1;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {
    private long bookId;
    private String titleKorean;
    private String titleEnglish;
    private String description;
    private String author;
    private String isbn;
    private String publishDate;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
