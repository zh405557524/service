package com.example.service

import com.example.service.model.Feedback
import com.example.service.repository.FeedbackRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime

/**
 * Spring Boot 应用启动类
 * 这是整个应用的入口点，就像公司的大门一样
 * @SpringBootApplication 注解表示这是一个Spring Boot应用
 */
@SpringBootApplication
class ServiceApplication

/**
 * 应用主函数
 * 启动Spring Boot应用
 */
fun main(args: Array<String>) {
    runApplication<ServiceApplication>(*args)
}

/**
 * 应用配置类
 * 包含一些初始化配置
 */
@org.springframework.context.annotation.Configuration
class ApplicationConfig {
    
    /**
     * 初始化数据
     * 在应用启动时自动创建一些测试数据
     * @param feedbackRepository 反馈数据访问对象
     * @return CommandLineRunner 启动时执行的命令
     */
    @Bean
    fun initData(feedbackRepository: FeedbackRepository): CommandLineRunner {

        return CommandLineRunner { args ->
            // 创建一些测试反馈数据
            val testFeedbacks = listOf(
                Feedback(
                    title = "界面优化建议",
                    content = "希望可以优化用户界面的布局，让操作更加直观",
                    type = "功能建议",
                    email = "user1@example.com",
                    userName = "张三",
                    status = "PENDING",
                    priority = "MEDIUM"
                ),
                Feedback(
                    title = "登录功能异常",
                    content = "在某些情况下登录按钮无响应，需要刷新页面才能正常使用",
                    type = "Bug报告",
                    email = "user2@example.com",
                    userName = "李四",
                    status = "IN_PROGRESS",
                    priority = "HIGH"
                ),
                Feedback(
                    title = "性能优化建议",
                    content = "页面加载速度较慢，建议优化数据库查询和缓存机制",
                    type = "性能问题",
                    email = "user3@example.com",
                    userName = "王五",
                    status = "RESOLVED",
                    priority = "LOW"
                )
            )
            
            // 保存测试数据到数据库
            feedbackRepository.saveAll(testFeedbacks)
            
            println("✅ 测试数据初始化完成！")
            println("📝 已创建 ${testFeedbacks.size} 条测试反馈")
            println("🌐 应用启动成功，访问地址：http://localhost:8080")
            println("📊 H2数据库控制台：http://localhost:8080/h2-console")
            println("📋 API文档：")
            println("   - 创建反馈：POST http://localhost:8080/api/feedback")
            println("   - 获取反馈列表：GET http://localhost:8080/api/feedback")
            println("   - 获取反馈详情：GET http://localhost:8080/api/feedback/{id}")
            println("   - 更新反馈：PUT http://localhost:8080/api/feedback/{id}")
            println("   - 删除反馈：DELETE http://localhost:8080/api/feedback/{id}")
        }
    }
}
