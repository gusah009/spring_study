package com.example.tobyspringboot

import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

class MySpringApplication {

    companion object {
        fun run(applicationClass: Class<*>, args: Array<String>) {
            object : AnnotationConfigWebApplicationContext() {
                override fun onRefresh() {
                    super.onRefresh()

                    val serverFactory: ServletWebServerFactory = this.getBean(ServletWebServerFactory::class.java)
                    val dispatcherServlet = this.getBean(DispatcherServlet::class.java)
                    // dispatcherServlet.setApplicationContext(this)
                    // 톰캣뿐만 아니라 다른 서블릿 컨테이너도 받을 수 있게 추상화 해놓은게 webServer
                    val webServer = serverFactory.getWebServer(ServletContextInitializer { servletContext ->
                        servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                            .addMapping("/*")
                    })
                    webServer.start() // 톰캣 서블릿 컨테이너 실행
                }
            }.apply {
                register(applicationClass)
                refresh()
            }
        }
    }
}
