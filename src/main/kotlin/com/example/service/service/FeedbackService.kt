package com.example.service.service

import com.example.service.dto.*
import com.example.service.model.Feedback
import com.example.service.repository.FeedbackRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * 意见反馈业务逻辑层
 * 处理业务规则，就像公司的业务部门一样
 * @Service 注解告诉Spring这是一个业务逻辑组件
 */
@Service
class FeedbackService(
    private val feedbackRepository: FeedbackRepository
) {
    
    /**
     * 创建新的意见反馈
     * @param request 创建反馈的请求数据
     * @return 创建成功的反馈信息
     */
    fun createFeedback(request: CreateFeedbackRequest): FeedbackResponse {
        // 创建新的反馈对象
        val feedback = Feedback(
            title = request.title,
            content = request.content,
            type = request.type,
            email = request.email,
            userName = request.userName
        )
        
        // 保存到数据库
        val savedFeedback = feedbackRepository.save(feedback)
        
        // 转换为响应格式并返回
        return convertToResponse(savedFeedback)
    }
    
    /**
     * 根据ID获取反馈详情
     * @param id 反馈ID
     * @return 反馈详情
     * @throws RuntimeException 如果反馈不存在
     */
    fun getFeedbackById(id: Long): FeedbackResponse {
        val feedback = feedbackRepository.findById(id)
            .orElseThrow { RuntimeException("反馈不存在，ID: $id") }
        
        return convertToResponse(feedback)
    }
    
    /**
     * 更新反馈信息
     * @param id 反馈ID
     * @param request 更新请求数据
     * @return 更新后的反馈信息
     */
    fun updateFeedback(id: Long, request: UpdateFeedbackRequest): FeedbackResponse {
        // 先查找现有的反馈
        val existingFeedback = feedbackRepository.findById(id)
            .orElseThrow { RuntimeException("反馈不存在，ID: $id") }
        
        // 创建更新后的反馈对象
        val updatedFeedback = existingFeedback.copy(
            title = request.title ?: existingFeedback.title,
            content = request.content ?: existingFeedback.content,
            type = request.type ?: existingFeedback.type,
            status = request.status ?: existingFeedback.status,
            priority = request.priority ?: existingFeedback.priority,
            updatedAt = LocalDateTime.now()
        )
        
        // 保存更新
        val savedFeedback = feedbackRepository.save(updatedFeedback)
        
        return convertToResponse(savedFeedback)
    }
    
    /**
     * 删除反馈
     * @param id 反馈ID
     */
    fun deleteFeedback(id: Long) {
        if (!feedbackRepository.existsById(id)) {
            throw RuntimeException("反馈不存在，ID: $id")
        }
        feedbackRepository.deleteById(id)
    }
    
    /**
     * 分页查询反馈列表
     * @param page 页码（从0开始）
     * @param size 每页大小
     * @return 分页的反馈列表
     */
    fun getFeedbacks(page: Int, size: Int): FeedbackListResponse {
        val pageable: Pageable = PageRequest.of(page, size)
        val feedbackPage: Page<Feedback> = feedbackRepository.findAll(pageable)
        
        val feedbacks = feedbackPage.content.map { convertToResponse(it) }
        
        return FeedbackListResponse(
            feedbacks = feedbacks,
            total = feedbackPage.totalElements,
            page = page,
            size = size
        )
    }
    
    /**
     * 根据状态查询反馈列表
     * @param status 反馈状态
     * @param page 页码
     * @param size 每页大小
     * @return 分页的反馈列表
     */
    fun getFeedbacksByStatus(status: String, page: Int, size: Int): FeedbackListResponse {
        val pageable: Pageable = PageRequest.of(page, size)
        val feedbackPage: Page<Feedback> = feedbackRepository.findByStatus(status, pageable)
        
        val feedbacks = feedbackPage.content.map { convertToResponse(it) }
        
        return FeedbackListResponse(
            feedbacks = feedbacks,
            total = feedbackPage.totalElements,
            page = page,
            size = size
        )
    }
    
    /**
     * 根据类型查询反馈列表
     * @param type 反馈类型
     * @param page 页码
     * @param size 每页大小
     * @return 分页的反馈列表
     */
    fun getFeedbacksByType(type: String, page: Int, size: Int): FeedbackListResponse {
        val pageable: Pageable = PageRequest.of(page, size)
        val feedbackPage: Page<Feedback> = feedbackRepository.findByType(type, pageable)
        
        val feedbacks = feedbackPage.content.map { convertToResponse(it) }
        
        return FeedbackListResponse(
            feedbacks = feedbacks,
            total = feedbackPage.totalElements,
            page = page,
            size = size
        )
    }
    
    /**
     * 搜索反馈
     * @param keyword 搜索关键词
     * @param page 页码
     * @param size 每页大小
     * @return 分页的反馈列表
     */
    fun searchFeedbacks(keyword: String, page: Int, size: Int): FeedbackListResponse {
        val pageable: Pageable = PageRequest.of(page, size)
        val feedbackPage: Page<Feedback> = feedbackRepository.findByTitleOrContentContaining(keyword, pageable)
        
        val feedbacks = feedbackPage.content.map { convertToResponse(it) }
        
        return FeedbackListResponse(
            feedbacks = feedbacks,
            total = feedbackPage.totalElements,
            page = page,
            size = size
        )
    }
    
    /**
     * 获取反馈统计信息
     * @return 统计信息
     */
    fun getFeedbackStats(): Map<String, Any> {
        val statusStats = feedbackRepository.countByStatus()
        val typeStats = feedbackRepository.countByType()
        
        return mapOf(
            "statusStats" to statusStats,
            "typeStats" to typeStats,
            "totalCount" to feedbackRepository.count()
        )
    }
    
    /**
     * 将数据库实体转换为响应DTO
     * @param feedback 数据库实体
     * @return 响应DTO
     */
    private fun convertToResponse(feedback: Feedback): FeedbackResponse {
        return FeedbackResponse(
            id = feedback.id!!,
            title = feedback.title,
            content = feedback.content,
            type = feedback.type,
            email = feedback.email,
            userName = feedback.userName,
            status = feedback.status,
            priority = feedback.priority,
            createdAt = feedback.createdAt,
            updatedAt = feedback.updatedAt
        )
    }
} 