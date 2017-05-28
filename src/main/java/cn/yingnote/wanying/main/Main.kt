package cn.yingnote.wanying.main

import cn.yingnote.wanying.filter.configFilter
import cn.yingnote.wanying.router.configRouter
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

/**
 * Created by guolei on 2017/5/25.
 * 主函数入口
 */
fun main(args: Array<String>) {
    val vertx: Vertx = Vertx.vertx()
    val server = vertx.createHttpServer()
    val router = Router.router(vertx)
    // 执行过滤器
    configFilter()
    // 配置路由
    configRouter(router)

    server.requestHandler({ router.accept(it) })
    server.listen(8080, { response ->
        if (response.succeeded()) {
            println("启动成功！")
        } else {
            println("启动失败！" + response.cause())
        }
    })
}