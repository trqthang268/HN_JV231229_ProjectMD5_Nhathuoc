package ra.hn_jv231229_projectmd5_nhathuoc.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseWrapper<T> {
    HttpStatus httpStatus;
    T data;
    int statusCode;
}
