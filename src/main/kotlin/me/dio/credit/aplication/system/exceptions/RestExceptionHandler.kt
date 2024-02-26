package me.dio.credit.aplication.system.exceptions

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidateException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach() {
            error: ObjectError ->
            val fieldName: String = (error as FieldError).field
            val messageError: String? = error.defaultMessage
            errors[fieldName] = messageError
        }
        return ResponseEntity(
            ExceptionDetails(
                title = "BAD REQUEST",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.toString(),
                details = errors
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(DataAccessException::class)
    fun handlerValidateException(ex: DataAccessException): ResponseEntity<ExceptionDetails> {
        return ResponseEntity(
            ExceptionDetails(
                title = "CONFLICT",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.CONFLICT.value(),
                exception = ex.javaClass.toString(),
                details = mutableMapOf(ex.cause.toString() to ex.message)
            ), HttpStatus.CONFLICT
        )
    }

    @ExceptionHandler(RuntimeException::class)
    fun handlerValidateException(ex: RuntimeException): ResponseEntity<ExceptionDetails> {
        return ResponseEntity(
            ExceptionDetails(
                title = "BAD REQUEST",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.toString(),
                details = mutableMapOf(ex.cause.toString() to ex.message)
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(IllegalAccessException::class)
    fun handlerValidateException(ex: IllegalAccessException): ResponseEntity<ExceptionDetails> {
        return ResponseEntity(
            ExceptionDetails(
                title = "BAD REQUEST",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = ex.javaClass.toString(),
                details = mutableMapOf(ex.cause.toString() to ex.message)
            ), HttpStatus.BAD_REQUEST
        )
    }
}
