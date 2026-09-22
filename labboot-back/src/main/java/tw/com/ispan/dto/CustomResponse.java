package tw.com.ispan.dto;

import java.util.List;

public record CustomResponse(
    Boolean success,
    String message,
    Long count,
    List<?> list) {
    
}
