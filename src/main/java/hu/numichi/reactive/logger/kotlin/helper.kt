package hu.numichi.reactive.logger.kotlin

import hu.numichi.reactive.logger.annotations.JacocoSkipGeneratedReport
import hu.numichi.reactive.logger.MDC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.reactor.ReactorContext
import kotlinx.coroutines.reactor.asCoroutineContext
import reactor.util.context.Context
import kotlin.coroutines.coroutineContext

@JacocoSkipGeneratedReport
internal suspend fun reactorContextOrEmpty(): Context {
    return rectorContext() ?: Context.empty()
}

internal suspend fun rectorContext(): Context? {
    return coroutineContext[ReactorContext]?.context
}

internal fun mdcCollectionIntoContext(context: Context, iterator: Iterator<MDC>): Context {
    var writableContext = context
    iterator.forEach {
        writableContext = writableContext.put(it.contextKey, it.asMap())
    }
    return writableContext
}

internal suspend fun <T> withContextBlock(context: Context, iterator: Iterator<MDC>, block: suspend CoroutineScope.() -> T): T {
    return kotlinx.coroutines.withContext(mdcCollectionIntoContext(context, iterator).asCoroutineContext()) {
        block()
    }
}