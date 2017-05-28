package cn.yingnote.wanying.router

import cn.yingnote.wanying.handler.homeIndexHandler
import cn.yingnote.wanying.handler.initTemplateResolver
import cn.yingnote.wanying.handler.markdownHandler
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.StaticHandler


/**
 * Created by guolei on 2017/5/25.
 * URL 路由
 */
fun configRouter(router: Router) {
    // 初始化模板引擎的配置，设置模板的相关配置参数
    initTemplateResolver()

    // This body handler will be called for all routes, 获取表单数据需要使用 BodyHandler
    router.route().handler(BodyHandler.create())

    // 处理首页
    router.route("/").handler(homeIndexHandler)

    // 处理预览 markdown
    router.route("/preview").handler(markdownHandler)

    // 处理静态资源
    router.route("/*").handler(StaticHandler.create())
}