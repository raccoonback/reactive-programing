package study.reactiveprograming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@EnableR2dbcAuditing
@SpringBootApplication
public class ReactiveProgramingApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReactiveProgramingApplication.class, args);
  }

}
