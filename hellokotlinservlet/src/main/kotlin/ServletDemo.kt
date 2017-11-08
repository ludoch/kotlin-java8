// See https://github.com/JetBrains/kotlin-examples/blob/master/LICENSE
package com.google.appengine.demo.kotlinservlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "Hello", value = "/")
class ServletDemo : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        res.writer.write("""
            <b>Hello, World! I am a Servlet 3.1 running on
            Java8 App Engine Standard, and written in Kotlin...</b>
            """)


        res.writer.write("""
        <pre>

    import javax.servlet.annotation.WebServlet
    import javax.servlet.http.HttpServlet
    import javax.servlet.http.HttpServletRequest
    import javax.servlet.http.HttpServletResponse

    @WebServlet(name = "Hello", value = "/")
    class ServletDemo : HttpServlet() {
        override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
            res.writer.write(""${'"'}
                &lt;b&gt;Hello, World! I am a Servlet 3.1 running on
                Java8 App Engine Standard, and written in Kotlin...&lt;/b&gt;
                ""${'"'})
        }
    }
    </pre>
            """)
    }
}
