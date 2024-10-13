package study.reactiveprograming.chapter17.v1;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration("bookRouterV1")
public class BookRouter {

  @Bean
  public RouterFunction<?> routeBook(BookHandler handler) {
    return route()
        .POST("/v1/books", handler::createBook)
        .PATCH("/v1/books/{book-id}", handler::patchBook)
        .GET("/v1/books", handler::getBooks)
        .GET("/v1/books/{book-id}", handler::getBook)
        .build();
  }

}
