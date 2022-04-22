package ru.nikitae57.data.translation.gettranslation

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface TranslationApi {
    @POST("/translate/v2/translate")
    fun translate(
        @Body body: TextToTranslateDataModel
    ): Single<TranslationsDataModel>
}