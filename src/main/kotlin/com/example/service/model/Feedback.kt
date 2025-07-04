package com.example.service.model

import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * 意见反馈数据模型
 * 这个类定义了意见反馈的数据结构，就像定义了一个反馈表单的字段
 */
@Entity
@Table(name = "feedback")
data class Feedback(
    
    /**
     * 主键ID - 每个反馈的唯一标识符
     * @GeneratedValue 表示这个ID会自动生成，不需要手动设置
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    /**
     * 反馈标题 - 用户给反馈起的标题
     */
    @Column(nullable = false, length = 200)
    val title: String,
    
    /**
     * 反馈内容 - 用户的具体反馈内容
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    val content: String,
    
    /**
     * 反馈类型 - 比如：功能建议、bug报告、用户体验等
     */
    @Column(nullable = false, length = 50)
    val type: String,
    
    /**
     * 用户邮箱 - 用于回复用户
     */
    @Column(length = 100)
    val email: String? = null,
    
    /**
     * 用户姓名 - 反馈者的姓名
     */
    @Column(length = 50)
    val userName: String? = null,
    
    /**
     * 反馈状态 - 待处理、处理中、已处理、已关闭
     */
    @Column(nullable = false, length = 20)
    val status: String = "PENDING",
    
    /**
     * 优先级 - 高、中、低
     */
    @Column(length = 10)
    val priority: String = "MEDIUM",
    
    /**
     * 创建时间 - 反馈提交的时间
     */
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    /**
     * 更新时间 - 反馈最后修改的时间
     */
    @Column(nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
) 