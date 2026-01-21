package ecommerce.core.user.dto;

import java.util.Map;

public record ErrorResponse(int status,String error,String message,String path,Map<String, Object> details) {
	public static ErrorResponse of(int status, String error, String message, String path) {
        return new ErrorResponse(status, error, message, path, null);
    }
	
	public static ErrorResponse of(int status, String error, String message, String path, Map<String, Object> details) {
        return new ErrorResponse(status, error, message, path, details);
    }
	
}
