package study.reactiveprograming.chapter17.v2;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import study.reactiveprograming.chapter16.v1.BookDto;

@Component("bookValidatorV2")
public class BookValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return BookDto.Post.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    BookDto.Post post = (BookDto.Post) target;

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titleKorean", "field.required");

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titleEnglish", "field.required");
  }
}
