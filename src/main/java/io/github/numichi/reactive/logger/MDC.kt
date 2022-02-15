package io.github.numichi.reactive.logger

import io.github.numichi.reactive.logger.DefaultValues.Companion.getInstance
import java.util.concurrent.ConcurrentHashMap

class MDC(contextKey: String?, mdcMap: Map<String, String>) : ConcurrentHashMap<String, String>() {
    val contextKey: String

    constructor() : this(null, mapOf())
    constructor(contextKey: String) : this(contextKey, mapOf())
    constructor(mdcMap: Map<String, String>) : this(null, mdcMap)

    init {
        this.putAll(mdcMap)
        this.contextKey = contextKey ?: getInstance().defaultReactorContextMdcKey
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MDC) return false
        if (!super.equals(other)) return false
        if (contextKey != other.contextKey) return false
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + contextKey.hashCode()
        return result
    }
}