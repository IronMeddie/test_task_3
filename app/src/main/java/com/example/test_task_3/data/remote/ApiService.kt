package com.example.test_task_3.data.remote

import com.example.test_task_3.domain.constance.Constance
import com.example.test_task_3.domain.models.MyItem
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    @GET("/api/ppp/item/")
    suspend fun getItem(@Query("id") id: Int): MyItem


    @GET("/api/ppp/index/")
    suspend fun getData(
        @Query("search") search: String? = null,
        @Query("offset") offSet: Int,
        @Query("limit") limit: Int = Constance.PAGE_SIZE
    ): List<MyItem>

}