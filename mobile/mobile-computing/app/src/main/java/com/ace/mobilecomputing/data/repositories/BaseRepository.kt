package com.ace.mobilecomputing.data.repositories

import com.ace.mobilecomputing.data.data_sources.BaseDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


open class BaseRepository<Domain, Data> @Inject constructor(
    private val source: BaseDataSource<Domain, Data>
) {
    open suspend fun addLocal(obj: Data): Long = source.addLocal(obj)
    open suspend fun upsertLocal(obj: Data): Long = source.upsert(obj)
    open suspend fun addLocal(objs: List<Data>) = source.addLocal(objs)
    open suspend fun upsertLocal(objs: List<Data>) = source.upsert(objs)
    open suspend fun updateLocal(obj: Data) = source.updateLocal(obj)
    open suspend fun updateLocal(objs: List<Data>) = source.updateLocal(objs)
    open suspend fun deleteLocal(obj: Data) = source.deleteLocal(obj)
    open suspend fun deleteLocal(objs: List<Data>) = source.deleteLocal(objs)

    suspend fun <A, B> Iterable<A>.parallelMap(f: suspend (A) -> B): List<B> = coroutineScope {
        map { async { f(it) } }.awaitAll()
    }
}