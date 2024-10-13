package study.reactiveprograming.chapter20;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class BookDto {
    @AllArgsConstructor
    @Getter
    public static class Post {
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

    @AllArgsConstructor
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
