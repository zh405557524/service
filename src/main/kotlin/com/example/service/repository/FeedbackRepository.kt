package com.example.service.repository

import com.example.service.model.Feedback
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * 意见反馈数据访问层
 * 负责与数据库的交互，就像数据库的管家一样
 * @Repository 注解告诉Spring这是一个数据访问组件
 */
@Repository
interface FeedbackRepository : JpaRepository<Feedback, Long> {
    
    /**
     * 根据状态查询反馈列表
     * 这个方法会自动生成SQL查询语句
     * @param status 反馈状态
     * @param pageable 分页参数
     * @return 分页的反馈列表
     */
    fun findByStatus(status: String, pageable: Pageable): Page<Feedback>
    
    /**
     * 根据类型查询反馈列表
     * @param type 反馈类型
     * @param pageable 分页参数
     * @return 分页的反馈列表
     */
    fun findByType(type: String, pageable: Pageable): Page<Feedback>
    
    /**
     * 根据优先级查询反馈列表
     * @param priority 优先级
     * @param pageable 分页参数
     * @return 分页的反馈列表
     */
    fun findByPriority(priority: String, pageable: Pageable): Page<Feedback>
    
    /**
     * 根据用户邮箱查询反馈列表
     * @param email 用户邮箱
     * @param pageable 分页参数
     * @return 分页的反馈列表
     */
    fun findByEmail(email: String, pageable: Pageable): Page<Feedback>
    
    /**
     * 根据标题或内容模糊查询
     * 使用自定义SQL查询，支持模糊搜索
     * @param keyword 搜索关键词
     * @param pageable 分页参数
     * @return 分页的反馈列表
     */
    @Query("SELECT f FROM Feedback f WHERE f.title LIKE %:keyword% OR f.content LIKE %:keyword%")
    fun findByTitleOrContentContaining(@Param("keyword") keyword: String, pageable: Pageable): Page<Feedback>
    
    /**
     * 统计各状态的反馈数量
     * 用于生成统计报表
     * @return 状态和对应的数量
     */
    @Query("SELECT f.status, COUNT(f) FROM Feedback f GROUP BY f.status")
    fun countByStatus(): List<Array<Any>>
    
    /**
     * 统计各类型的反馈数量
     * @return 类型和对应的数量
     */
    @Query("SELECT f.type, COUNT(f) FROM Feedback f GROUP BY f.type")
    fun countByType(): List<Array<Any>>
} 