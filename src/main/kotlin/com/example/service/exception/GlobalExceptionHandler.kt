package com.example.service.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

/**
 * 全局异常处理器
 * 统一处理API中的异常，就像公司的客服部门一样
 * @ControllerAdvice 注解表示这是一个全局异常处理器
 */
@ControllerAdvice
class GlobalExceptionHandler {
    
    /**
     * 处理运行时异常
     * @param ex 运行时异常
     * @return 错误响应
     */
    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.NOT_FOUND.value(),
            error = "Not Found",
            message = ex.message ?: "资源未找到",
            path = "/api/feedback"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }
    
    /**
     * 处理一般异常
     * @param ex 一般异常
     * @return 错误响应
     */
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = "Internal Server Error",
            message = ex.message ?: "服务器内部错误",
            path = "/api/feedback"
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
    
    /**
     * 处理参数验证异常
     * @param ex 参数验证异常
     * @return 错误响应
     */
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            timestamp = LocalDateTime.now(),
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Bad Request",
            message = ex.message ?: "请求参数错误",
            path = "/api/feedback"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}

/**
 * 错误响应数据类
 * 定义错误响应的格式
 */
data class ErrorResponse(
    val timestamp: LocalDateTime,  // 错误发生时间
    val status: Int,               // HTTP状态码
    val error: String,             // 错误类型
    val message: String,           // 错误消息
    val path: String               // 请求路径
) 