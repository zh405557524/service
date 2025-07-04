# 📝 意见反馈系统

一个基于 Spring Boot + Kotlin 开发的完整意见反馈系统，提供了完整的 CRUD 操作和高级查询功能。

## 🚀 项目特色

- **完整的CRUD操作** - 创建、读取、更新、删除反馈
- **高级查询功能** - 分页、搜索、按状态/类型筛选
- **统计功能** - 反馈状态和类型统计
- **RESTful API** - 标准的REST API设计
- **详细注释** - 每个文件都有详细的中文注释，适合新手学习
- **测试页面** - 内置Web测试界面，方便API测试

## 🏗️ 项目结构

```
src/main/kotlin/com/example/service/
├── ServiceApplication.kt          # 🚪 启动类 - 应用入口
├── controller/
│   └── FeedbackController.kt      # 🎮 控制器层 - 处理HTTP请求
├── service/
│   └── FeedbackService.kt         # 💼 业务逻辑层 - 处理业务规则
├── repository/
│   └── FeedbackRepository.kt      # 🗄️ 数据访问层 - 数据库操作
├── model/
│   └── Feedback.kt                # 📊 数据模型 - 数据库表结构
├── dto/
│   └── FeedbackDto.kt             # 📦 数据传输对象 - 前后端数据交换
└── exception/
    └── GlobalExceptionHandler.kt  # 🛡️ 全局异常处理 - 统一错误处理
```

## 🛠️ 技术栈

- **框架**: Spring Boot 3.x
- **语言**: Kotlin
- **数据库**: H2 (内存数据库)
- **ORM**: Spring Data JPA
- **构建工具**: Gradle
- **API文档**: 内置详细文档

## 📋 功能特性

### 基础功能
- ✅ 创建意见反馈
- ✅ 查看反馈列表（分页）
- ✅ 查看反馈详情
- ✅ 更新反馈信息
- ✅ 删除反馈

### 高级功能
- ✅ 按状态筛选反馈
- ✅ 按类型筛选反馈
- ✅ 关键词搜索反馈
- ✅ 反馈统计信息
- ✅ 更新反馈状态
- ✅ 更新反馈优先级

### 数据模型
- **反馈状态**: 待处理、处理中、已处理、已关闭
- **反馈类型**: 功能建议、Bug报告、性能问题、用户体验、其他
- **优先级**: 高、中、低

## 🚀 快速开始

### 1. 环境要求
- Java 17+
- Gradle 7+

### 2. 克隆项目
```bash
git clone <项目地址>
cd service
```

### 3. 运行项目
```bash
# 方式1: 使用Gradle运行
./gradlew bootRun

# 方式2: 构建后运行
./gradlew build
java -jar build/libs/service-0.0.1-SNAPSHOT.jar
```

### 4. 访问应用
- **应用首页**: http://localhost:8080
- **API测试页面**: http://localhost:8080/index.html
- **H2数据库控制台**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:feedbackdb`
  - 用户名: `sa`
  - 密码: (空)

## 📚 API文档

### 基础URL
```
http://localhost:8080/api/feedback
```

### 主要接口

| 方法 | 路径 | 描述 |
|------|------|------|
| POST | `/api/feedback` | 创建反馈 |
| GET | `/api/feedback` | 获取反馈列表 |
| GET | `/api/feedback/{id}` | 获取反馈详情 |
| PUT | `/api/feedback/{id}` | 更新反馈 |
| DELETE | `/api/feedback/{id}` | 删除反馈 |
| GET | `/api/feedback/search` | 搜索反馈 |
| GET | `/api/feedback/stats` | 获取统计信息 |

详细API文档请查看 [API_DOCUMENTATION.md](API_DOCUMENTATION.md)

## 🧪 测试示例

### 1. 创建反馈
```bash
curl -X POST http://localhost:8080/api/feedback \
  -H "Content-Type: application/json" \
  -d '{
    "title": "界面优化建议",
    "content": "希望可以优化用户界面的布局",
    "type": "功能建议",
    "email": "user@example.com",
    "userName": "测试用户"
  }'
```

### 2. 获取反馈列表
```bash
curl http://localhost:8080/api/feedback?page=0&size=10
```

### 3. 搜索反馈
```bash
curl "http://localhost:8080/api/feedback/search?keyword=界面"
```

## 🎯 学习指南

### 服务端开发流程

1. **数据模型层 (Model)** - 定义数据库表结构
   - 使用JPA注解映射数据库表
   - 定义字段类型和约束

2. **数据传输层 (DTO)** - 定义前后端数据交换格式
   - 请求DTO：前端发送给后端的数据格式
   - 响应DTO：后端返回给前端的数据格式

3. **数据访问层 (Repository)** - 数据库操作接口
   - 继承JpaRepository获得基础CRUD功能
   - 定义自定义查询方法

4. **业务逻辑层 (Service)** - 处理业务规则
   - 数据验证和转换
   - 业务逻辑处理
   - 调用Repository进行数据操作

5. **控制器层 (Controller)** - 处理HTTP请求
   - 接收前端请求
   - 调用Service处理业务
   - 返回响应结果

6. **异常处理层 (Exception)** - 统一错误处理
   - 捕获和处理各种异常
   - 返回统一的错误格式

### 关键概念

- **分层架构**: 每层职责明确，便于维护和扩展
- **依赖注入**: Spring自动管理组件依赖关系
- **RESTful API**: 遵循REST设计原则的API
- **JPA/Hibernate**: 对象关系映射，简化数据库操作

## 🔧 配置说明

主要配置文件：`src/main/resources/application.yml`

- **服务器端口**: 8080
- **数据库**: H2内存数据库
- **JPA配置**: 自动创建表结构
- **日志级别**: DEBUG模式

## 📝 开发说明

### 添加新功能
1. 在Model层定义数据模型
2. 在DTO层定义数据传输对象
3. 在Repository层定义数据访问方法
4. 在Service层实现业务逻辑
5. 在Controller层暴露API接口

### 代码规范
- 使用Kotlin语言特性
- 添加详细的中文注释
- 遵循Spring Boot最佳实践
- 使用统一的异常处理

## 🤝 贡献指南

1. Fork 项目
2. 创建功能分支
3. 提交更改
4. 推送到分支
5. 创建 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 🆘 常见问题

### Q: 如何修改数据库配置？
A: 在 `application.yml` 中修改 `spring.datasource` 配置

### Q: 如何添加新的查询功能？
A: 在 `FeedbackRepository` 中添加查询方法，在 `FeedbackService` 中实现业务逻辑

### Q: 如何部署到生产环境？
A: 修改数据库配置为生产数据库，使用 `./gradlew build` 构建jar包

---

**🎉 恭喜！你已经成功创建了一个完整的意见反馈系统！**

这个项目展示了Spring Boot + Kotlin的完整开发流程，包含了现代Web应用开发的核心概念。通过这个项目，你可以学习到：

- Spring Boot框架的使用
- Kotlin语言特性
- 分层架构设计
- RESTful API设计
- 数据库操作
- 异常处理
- 前后端交互

祝你学习愉快！🚀