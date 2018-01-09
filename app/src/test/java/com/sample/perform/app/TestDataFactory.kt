package com.sample.perform.app

import com.sample.perform.app.data.model.ArticlesItem
import com.sample.perform.app.data.model.Response
import java.util.*
import kotlin.collections.ArrayList


object  TestDataFactory{

    val newsUrl = "www.mock.com"

    fun makeResponseList(number: Int): Response {
        return Response(number.toString(),makeArticleItems(number))
    }

    fun makeArticleItems(number: Int): List<ArticlesItem>{
        var articleItems = ArrayList<ArticlesItem>()
        for (i in  0..number.dec()){
            articleItems.add(makeArticleItem())
        }
        return articleItems
    }

    fun makeArticleItem() : ArticlesItem {
        return ArticlesItem(
                publishedTime = 1515438840000,
                headline = "AMERICAN SPORTS: Stars and Bites: LaVar Ball claims Walton has lost Lakers locker room",
                id = "1hbzuyez30v8d1wq7egeigjitm",
                teaser = "",
                        language = "en",
                lastUpdateTime = 1515438811000)
    }

}