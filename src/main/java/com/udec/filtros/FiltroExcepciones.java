package com.udec.filtros;
import java.sql.SQLException;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.udec.dto.ErrorDto;
import com.udec.exception.BussinesException;
import com.udec.exception.ModelNotFoundException;

@ControllerAdvice
@RestController
public class FiltroExcepciones extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ModelNotFoundException.class)
	public final ResponseEntity<ErrorDto> ModelNotFoundExceptionHandler(WebRequest request, ModelNotFoundException ex){
		ex.printStackTrace();
		HttpStatus status =  HttpStatus.NOT_FOUND;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<ErrorDto>(error, status);
	}
	
	@ExceptionHandler(BussinesException.class)
	public final ResponseEntity<ErrorDto> BussinesExceptionHanlder(WebRequest request, BussinesException ex){
		ex.printStackTrace();
		HttpStatus status =  HttpStatus.BAD_REQUEST;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<ErrorDto>(error, status);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ErrorDto> DataIntegrityViolationExceptionHandler(WebRequest request, DataIntegrityViolationException ex){
		ex.printStackTrace();
		HttpStatus status =  HttpStatus.BAD_REQUEST;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<ErrorDto>(error, status);
	} 

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ErrorDto> ConstraintViolationExceptionHandler(WebRequest request, ConstraintViolationException ex){
		ex.printStackTrace();
		HttpStatus status =  HttpStatus.BAD_REQUEST;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<ErrorDto>(error, status);
	} 
	
	@ExceptionHandler(SQLException.class)
	public final ResponseEntity<ErrorDto> SQLExceptionHandler(WebRequest request, SQLException ex){
		ex.printStackTrace();
		HttpStatus status =  HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<ErrorDto>(error, status);
	}
	
	@ExceptionHandler(ClassNotFoundException.class)
	public final ResponseEntity<ErrorDto> ClassNotFoundExceptionHandler(WebRequest request, ClassNotFoundException ex){
		ex.printStackTrace();
		HttpStatus status =  HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<ErrorDto>(error, status);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<ErrorDto> NullPointerExceptionHandler(WebRequest request, NullPointerException ex){
		ex.printStackTrace();
		HttpStatus status =  HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , ex.getMessage(), ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<ErrorDto>(error, status);
	}
		
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDto> ExceptionHandler(WebRequest request, Exception ex){
		ex.printStackTrace();
		HttpStatus status =  HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , "Ha ocurrido un error inesperado", ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<ErrorDto>(error, status);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ex.printStackTrace();
		status = HttpStatus.BAD_REQUEST;
		BindingResult resultado = ex.getBindingResult();
		List<FieldError> fieldErrors = resultado.getFieldErrors();
		StringBuilder errorMessage = new StringBuilder();
		fieldErrors.forEach(f -> errorMessage.append(f.getField() + "-> " + f.getDefaultMessage() + " "));

		ErrorDto error = new ErrorDto(status.toString(), status.value(), errorMessage.toString(),
				((ServletWebRequest) request).getRequest().getRequestURI().toString());
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		String causa = ex.getMessage();
		System.out.println("CAUSA " + causa);
		if(causa.contains("java.time.format.DateTimeParseException")) {
			status =  HttpStatus.BAD_REQUEST;
			ErrorDto error = new ErrorDto(status.toString(), status.value() , "El formato correcto es AÑO-MES-DIA", ((ServletWebRequest)request).getRequest().getRequestURI().toString());
			return new ResponseEntity<Object>(error, status);
		} else {
			status =  HttpStatus.BAD_REQUEST;
			ErrorDto error = new ErrorDto(status.toString(), status.value() , "El Json va mal armado", ((ServletWebRequest)request).getRequest().getRequestURI().toString());
			return new ResponseEntity<Object>(error, status);
		}
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		status =  HttpStatus.METHOD_NOT_ALLOWED;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , "Metodo no soportado", ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		status =  HttpStatus.UNSUPPORTED_MEDIA_TYPE;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , "el formato es inválido", ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		status =  HttpStatus.BAD_REQUEST;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , "Páramatro incorrecto", ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		status =  HttpStatus.NOT_FOUND;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , "Url no existe", ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<Object>(error, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		status =  HttpStatus.NOT_ACCEPTABLE;
		ErrorDto error = new ErrorDto(status.toString(), status.value() , "Encabezados incorrectos", ((ServletWebRequest)request).getRequest().getRequestURI().toString());
		return new ResponseEntity<Object>(error, status);
	}
}
