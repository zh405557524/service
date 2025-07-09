package com.example.service.dto

import java.time.LocalDateTime

/**
 * 意见反馈数据传输对象
 * 用于前端和后端之间的数据交换，就像快递包装一样
 */

/**
 * 创建反馈请求DTO - 前端发送给后端的数据格式
 */
data class CreateFeedbackRequest(
    val title: String? = null,    // 反馈标题（可选）
    val content: String,          // 反馈内容（必选）
    val type: String? = null,     // 反馈类型（可选）
    val email: String? = null,    // 用户邮箱（可选）
    val userName: String? = null  // 用户姓名（可选）
)

/**
 * 更新反馈请求DTO - 更新反馈时使用的数据格式
 */
data class UpdateFeedbackRequest(
    val title: String? = null,     // 反馈标题
    val content: String? = null,   // 反馈内容
    val type: String? = null,      // 反馈类型
    val status: String? = null,    // 反馈状态
    val priority: String? = null   // 优先级
)

/**
 * 反馈响应DTO - 后端返回给前端的数据格式
 */
data class FeedbackResponse(
    val id: Long,                    // 反馈ID
    val title: String,               // 反馈标题
    val content: String,             // 反馈内容
    val type: String,                // 反馈类型
    val email: String?,              // 用户邮箱
    val userName: String?,           // 用户姓名
    val status: String,              // 反馈状态
    val priority: String,            // 优先级
    val createdAt: LocalDateTime,    // 创建时间
    val updatedAt: LocalDateTime     // 更新时间
)

/**
 * 反馈列表响应DTO - 分页查询时返回的数据格式
 */
data class FeedbackListResponse(
    val feedbacks: List<FeedbackResponse>,  // 反馈列表
    val total: Long,                        // 总数量
    val page: Int,                          // 当前页码
    val size: Int                           // 每页大小
) 