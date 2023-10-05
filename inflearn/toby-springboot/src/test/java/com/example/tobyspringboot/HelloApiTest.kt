package com.example.tobyspringboot

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.MediaType

class HelloApiTest {
    @Test
    @Disabled
    fun helloApi() {
        val rest = TestRestTemplate()

        val result = rest.getForEntity("http://localhost:8080/hello?name={name}", String::class.java, "Spring")

        Assertions.assertThat(result.statusCodeValue).isEqualTo(200)
        Assertions.assertThat(result.headers.contentType!!.toString()).startsWith(MediaType.TEXT_PLAIN_VALUE)
        Assertions.assertThat(result.body).isEqualTo("Hello Spring")
    }
}
