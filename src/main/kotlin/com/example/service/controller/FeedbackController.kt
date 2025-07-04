package com.example.service.controller

import com.example.service.dto.*
import com.example.service.service.FeedbackService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * 意见反馈控制器
 * 处理HTTP请求，就像公司的前台接待一样
 * @RestController 注解表示这是一个REST API控制器
 * @RequestMapping 定义API的基础路径
 */
@RestController
@RequestMapping("/api/feedback")
class FeedbackController(
    private val feedbackService: FeedbackService
) {
    
    /**
     * 创建新的意见反馈
     * POST /api/feedback
     * @param request 创建反馈的请求数据
     * @return 创建成功的反馈信息
     */
    @PostMapping
    fun createFeedback(@RequestBody request: CreateFeedbackRequest): ResponseEntity<FeedbackResponse> {
        try {
            val feedback = feedbackService.createFeedback(request)
            return ResponseEntity.status(HttpStatus.CREATED).body(feedback)
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }
    
    /**
     * 根据ID获取反馈详情
     * GET /api/feedback/{id}
     * @param id 反馈ID
     * @return 反馈详情
     */
    @GetMapping("/{id}")
    fun getFeedbackById(@PathVariable id: Long): ResponseEntity<FeedbackResponse> {
        return try {
            val feedback = feedbackService.getFeedbackById(id)
            ResponseEntity.ok(feedback)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }
    
    /**
     * 更新反馈信息
     * PUT /api/feedback/{id}
     * @param id 反馈ID
     * @param request 更新请求数据
     * @return 更新后的反馈信息
     */
    @PutMapping("/{id}")
    fun updateFeedback(
        @PathVariable id: Long,
        @RequestBody request: UpdateFeedbackRequest
    ): ResponseEntity<FeedbackResponse> {
        return try {
            val feedback = feedbackService.updateFeedback(id, request)
            ResponseEntity.ok(feedback)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }
    
    /**
     * 删除反馈
     * DELETE /api/feedback/{id}
     * @param id 反馈ID
     * @return 删除成功响应
     */
    @DeleteMapping("/{id}")
    fun deleteFeedback(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            feedbackService.deleteFeedback(id)
            ResponseEntity.noContent().build()
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }
    
    /**
     * 分页查询反馈列表
     * GET /api/feedback?page=0&size=10
     * @param page 页码（默认0）
     * @param size 每页大小（默认10）
     * @return 分页的反馈列表
     */
    @GetMapping
    fun getFeedbacks(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<FeedbackListResponse> {
        val feedbacks = feedbackService.getFeedbacks(page, size)
        return ResponseEntity.ok(feedbacks)
    }
    
    /**
     * 根据状态查询反馈列表
     * GET /api/feedback/status/{status}?page=0&size=10
     * @param status 反馈状态
     * @param page 页码
     * @param size 每页大小
     * @return 分页的反馈列表
     */
    @GetMapping("/status/{status}")
    fun getFeedbacksByStatus(
        @PathVariable status: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<FeedbackListResponse> {
        val feedbacks = feedbackService.getFeedbacksByStatus(status, page, size)
        return ResponseEntity.ok(feedbacks)
    }
    
    /**
     * 根据类型查询反馈列表
     * GET /api/feedback/type/{type}?page=0&size=10
     * @param type 反馈类型
     * @param page 页码
     * @param size 每页大小
     * @return 分页的反馈列表
     */
    @GetMapping("/type/{type}")
    fun getFeedbacksByType(
        @PathVariable type: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<FeedbackListResponse> {
        val feedbacks = feedbackService.getFeedbacksByType(type, page, size)
        return ResponseEntity.ok(feedbacks)
    }
    
    /**
     * 搜索反馈
     * GET /api/feedback/search?keyword=关键词&page=0&size=10
     * @param keyword 搜索关键词
     * @param page 页码
     * @param size 每页大小
     * @return 分页的反馈列表
     */
    @GetMapping("/search")
    fun searchFeedbacks(
        @RequestParam keyword: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<FeedbackListResponse> {
        val feedbacks = feedbackService.searchFeedbacks(keyword, page, size)
        return ResponseEntity.ok(feedbacks)
    }
    
    /**
     * 获取反馈统计信息
     * GET /api/feedback/stats
     * @return 统计信息
     */
    @GetMapping("/stats")
    fun getFeedbackStats(): ResponseEntity<Map<String, Any>> {
        val stats = feedbackService.getFeedbackStats()
        return ResponseEntity.ok(stats)
    }
    
    /**
     * 更新反馈状态
     * PATCH /api/feedback/{id}/status
     * @param id 反馈ID
     * @param status 新状态
     * @return 更新后的反馈信息
     */
    @PatchMapping("/{id}/status")
    fun updateFeedbackStatus(
        @PathVariable id: Long,
        @RequestParam status: String
    ): ResponseEntity<FeedbackResponse> {
        return try {
            val request = UpdateFeedbackRequest(status = status)
            val feedback = feedbackService.updateFeedback(id, request)
            ResponseEntity.ok(feedback)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }
    
    /**
     * 更新反馈优先级
     * PATCH /api/feedback/{id}/priority
     * @param id 反馈ID
     * @param priority 新优先级
     * @return 更新后的反馈信息
     */
    @PatchMapping("/{id}/priority")
    fun updateFeedbackPriority(
        @PathVariable id: Long,
        @RequestParam priority: String
    ): ResponseEntity<FeedbackResponse> {
        return try {
            val request = UpdateFeedbackRequest(priority = priority)
            val feedback = feedbackService.updateFeedback(id, request)
            ResponseEntity.ok(feedback)
        } catch (e: RuntimeException) {
            ResponseEntity.notFound().build()
        }
    }
} 