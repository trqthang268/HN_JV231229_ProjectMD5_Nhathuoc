package ra.hn_jv231229_projectmd5_nhathuoc.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataExistException extends Exception{
    private String field;

    public DataExistException(String field, String message) {
        super(message);
        this.field = field;
    }
}
