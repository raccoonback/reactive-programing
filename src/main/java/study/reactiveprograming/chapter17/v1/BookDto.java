package study.reactiveprograming.chapter17.v1;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class BookDto {
    @Getter
    public static class Post {
        @NotBlank
        private String titleKorean;
        private String titleEnglish;
        private String description;
        private String author;
        private String isbn;
        private String publishDate;
    }

    @Getter
    public static class Patch {
        @Setter
        private long bookId;
        private String titleKorean;
        private String titleEnglish;
        private String description;
        private String author;
        private String isbn;
        private String publishDate;
    }

    @Builder
    @Getter
    public static class Response {
        private long bookId;
        private String titleKorean;
        private String titleEnglish;
        private String description;
        private String author;
        private String isbn;
        private String publishDate;
    }
}
