package com.sample.perform.app.data.api.base
import java.net.URL

object UrlSplitter {
    fun splitToBaseUrlAndEndpoint(fullUrl: String): Pair<String, String> {
        val url = URL(fullUrl)
        var baseUrl = url.protocol + "://" + url.host
        if (url.port != -1) {
            baseUrl = url.protocol + "://" + url.host + ":" + url.port
        }
        val endpoint = url.path
        return Pair(baseUrl, endpoint)
    }
}