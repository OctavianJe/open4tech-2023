package com.ace.mobilecomputing.data.data_sources

import com.ace.mobilecomputing.LOGGING_TAG_NAME
import com.ace.mobilecomputing.data.dao.BaseDao
import com.ace.mobilecomputing.domain.network.ApiRequestResponse
import com.google.gson.Gson
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import kotlin.reflect.KSuspendFunction0
import kotlin.reflect.KSuspendFunction1
import kotlin.reflect.KSuspendFunction2


open class BaseDataSource<Domain, Data> @Inject constructor(
    private val dao: BaseDao<Data>
) {
    protected suspend fun <RET : Any> call(
        function: KSuspendFunction0<RET>,
        error: String = "Error making the request"
    ): ApiRequestResponse<RET> {
        val response = try {
            val response = function()

            Timber.tag(LOGGING_TAG_NAME).d(
                "BaseDataSource: ${
                    function.toString().substringBefore(':').substringAfter(' ')
                }: API call JSON response: \n%s",
                Gson().toJson(response).toString()
            )

            ApiRequestResponse.Success(response)
        } catch (e: Throwable) {
            val ioe = IOException("$e: $error", e)
            Timber.tag(LOGGING_TAG_NAME).w(
                "BaseDataSource: ${
                    function.toString().substringBefore(':').substringAfter(' ')
                }: Error occurred: \n%s",
                Gson().toJson(ioe).toString()
            )
            ApiRequestResponse.Error(ioe)
        }

        return response
    }

    private fun loggableParameter(objects: Any): String {
        return when (objects) {
            else -> Gson().toJson(objects)
        }.toString()
    }

    protected suspend fun <P : Any, RET : Any> call(
        function: KSuspendFunction1<P, RET>,
        parameter: P,
        error: String = "Error making the request"
    ): ApiRequestResponse<RET> {
        val response = try {
            Timber.tag(LOGGING_TAG_NAME).d(
                "BaseDataSource: ${
                    function.toString().substringBefore(':').substringAfter(' ')
                }: API call JSON parameter: \n%s",
                loggableParameter(parameter)
            )

            val response = function(parameter)

            Timber.tag(LOGGING_TAG_NAME).d(
                "BaseDataSource: ${
                    function.toString().substringBefore(':').substringAfter(' ')
                }: API call JSON response: \n%s",
                Gson().toJson(response).toString()
            )

            ApiRequestResponse.Success(response)
        } catch (e: Throwable) {
            val ioe = IOException("$e: $error", e)
            Timber.tag(LOGGING_TAG_NAME).w(
                "BaseDataSource: ${
                    function.toString().substringBefore(':').substringAfter(' ')
                }: Error occurred: \n%s",
                Gson().toJson(ioe).toString()
            )
            ApiRequestResponse.Error(ioe)
        }

        return response
    }

    protected suspend fun <P0 : Any, P1 : Any, RET : Any> call(
        function: KSuspendFunction2<P0, P1, RET>,
        parameter0: P0,
        parameter1: P1,
        error: String
    ): ApiRequestResponse<RET> {
        val response = try {
            Timber.tag(LOGGING_TAG_NAME).d(
                "BaseDataSource: ${
                    function.toString().substringBefore(':').substringAfter(' ')
                }: API call JSON parameters: \n%s, \n%s",
                loggableParameter(parameter0),
                loggableParameter(parameter1)
            )

            val response = function(parameter0, parameter1)

            Timber.tag(LOGGING_TAG_NAME).d(
                "BaseDataSource: ${
                    function.toString().substringBefore(':').substringAfter(' ')
                }: API call JSON response: \n%s",
                Gson().toJson(response).toString()
            )

            ApiRequestResponse.Success(response)
        } catch (e: Throwable) {
            val ioe = IOException("$e: $error", e)
            Timber.tag(LOGGING_TAG_NAME).w(
                "BaseDataSource: ${
                    function.toString().substringBefore(':').substringAfter(' ')
                }: Error occurred: \n%s",
                Gson().toJson(ioe).toString()
            )
            ApiRequestResponse.Error(ioe)
        }

        return response
    }

    open suspend fun addLocal(obj: Data): Long {
        Timber.tag(LOGGING_TAG_NAME).d(
            "BaseDataSource: addLocal(): Add local JSON: \n%s",
            Gson().toJson(obj).toString()
        )

        return dao.add(obj)
    }

    open suspend fun upsert(obj: Data): Long {
        Timber.tag(LOGGING_TAG_NAME).d(
            "BaseDataSource: upsert(): Upsert local JSON: \n%s",
            Gson().toJson(obj).toString()
        )

        return dao.upsert(obj)
    }

    open suspend fun addLocal(objs: List<Data>) {
        Timber.tag(LOGGING_TAG_NAME).d(
            "BaseDataSource: addLocal()(List): Add local JSONs: \n%s",
            Gson().toJson(objs).toString()
        )

        dao.add(objs)
    }

    open suspend fun upsert(objs: List<Data>) {
        Timber.tag(LOGGING_TAG_NAME).d(
            "BaseDataSource: upsert()(List): Upsert local JSONs: \n%s",
            Gson().toJson(objs).toString()
        )

        dao.upsert(objs)
    }

    open suspend fun updateLocal(obj: Data) {
        Timber.tag(LOGGING_TAG_NAME).d(
            "BaseDataSource: updateLocal(): Update local JSON: \n%s",
            Gson().toJson(obj).toString()
        )

        dao.update(obj)
    }

    open suspend fun updateLocal(objs: List<Data>) {
        Timber.tag(LOGGING_TAG_NAME).d(
            "BaseDataSource: updateLocal()(List): Update local JSONs: \n%s",
            Gson().toJson(objs).toString()
        )

        dao.update(objs)
    }

    open suspend fun deleteLocal(obj: Data) {
        Timber.tag(LOGGING_TAG_NAME).d(
            "BaseDataSource: deleteLocal(): Delete local JSON: \n%s",
            Gson().toJson(obj).toString()
        )

        dao.delete(obj)
    }

    open suspend fun deleteLocal(objs: List<Data>) {
        Timber.tag(LOGGING_TAG_NAME).d(
            "BaseDataSource: deleteLocal()(List): Delete local JSONs: \n%s",
            Gson().toJson(objs).toString()
        )

        dao.delete(objs)
    }
}