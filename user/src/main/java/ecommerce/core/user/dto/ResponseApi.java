package ecommerce.core.user.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class ResponseApi <T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	Map<String, Object> map = new LinkedHashMap<String, Object>();
	private T result;
	private HttpStatus httpStatus = HttpStatus.OK;
	
	public void setStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		map.put("status", httpStatus.value());
	}
	
	public void setMessage(String message) {
		this.map.put("message", message);
	}
	
	public void setResult(Object result) {
		map.put("result", result);
	}
	
	
	public Object build() {
		return ResponseEntity.status(httpStatus).body(map);
	}
	
	
}
