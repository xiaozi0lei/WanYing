package cn.yingnote.wanying.main

import cn.yingnote.wanying.filter.configFilter
import cn.yingnote.wanying.router.configRouter
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import org.slf4j.LoggerFactory

/**
 * Created by guolei on 2017/5/25.
 * 主函数入口
 */
fun main(args: Array<String>) {
    val logger = LoggerFactory.getLogger("main")

    // 初始化服务器全局配置
    val vertx: Vertx = Vertx.vertx()
    val server = vertx.createHttpServer()
    val router = Router.router(vertx)
    // 执行过滤器
    configFilter()
    // 配置路由
    configRouter(router)

    // 请求 handler
    server.requestHandler({ router.accept(it) })

    // 监听端口，校验启动成功还是失败，如失败，打印错误信息
    server.listen(8080, { response ->
        if (response.succeeded()) {
            logger.debug("启动成功！")
        } else {
            logger.debug("启动失败！" + response.cause())
        }
    })
}