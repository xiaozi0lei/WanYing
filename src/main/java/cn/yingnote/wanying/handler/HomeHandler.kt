package cn.yingnote.wanying.handler

import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.templ.ThymeleafTemplateEngine
import nz.net.ultraq.thymeleaf.LayoutDialect
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

/**
 * Created by guolei on 2017/5/25.
 * 主页 Handler
 */
var counter = 0
// 创建 html 模板引擎
val templateEngine = ThymeleafTemplateEngine.create()!!
// 配置模板引擎的 resolver
val resolver = ClassLoaderTemplateResolver()

fun initTemplateResolver() {
    resolver.prefix = "templates"
    resolver.suffix = ".html"
    resolver.setTemplateMode("HTML")
    // 设置模板引擎的 resolver
    templateEngine.thymeleafTemplateEngine.setTemplateResolver(resolver)
    // 加载方言插件
    templateEngine.thymeleafTemplateEngine.addDialect(LayoutDialect())
}

// 主页处理器
var homeIndexHandler: (routingContext: RoutingContext) -> Unit = {
    routingContext ->
    counter++
    // 设置返回 view 视图时带上的对象属性，主要是键值对
    routingContext.put("msg", "DynamicReference.")
    // 模板引擎渲染对应的模板，带上 routingContext 中包含所有的上下文信息，第二个参数代表要渲染的 html 文件的名字
    // 通过 initTemplateResolver()，设置了对应的模板前缀和后缀，拼接起来构成模板地址
    templateEngine.render(routingContext, "/homeIndex", { response ->
        if (response.succeeded()) {
            println(counter.toString() + " time")

            routingContext.response().putHeader("Content-Type", "text/html").end(response.result())
        } else {
            routingContext.fail(response.cause())
        }
    })
}

// markdown 预览视图处理器
var markdownHandler: (routingContext: RoutingContext) -> Unit = {
    routingContext ->
    counter++
    val textInput: String = routingContext.request().getParam("text-input")
    println(routingContext.request().getParam("text-input"))
    routingContext.put("text_input", textInput)
    templateEngine.render(routingContext, "/test/preview", { response ->
        if (response.succeeded()) {
            println(counter.toString() + " time")

            routingContext.response().putHeader("Content-Type", "text/html").end(response.result())
        } else {
            routingContext.fail(response.cause())
        }
    })
}