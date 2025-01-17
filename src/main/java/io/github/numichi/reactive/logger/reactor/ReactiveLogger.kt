package io.github.numichi.reactive.logger.reactor

import io.github.numichi.reactive.logger.Configuration
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.core.scheduler.Scheduler

class ReactiveLogger(
    override val logger: Logger,
    mdcContextKey: String,
    scheduler: Scheduler,
) : AReactive(logger, mdcContextKey, scheduler),
    IReactorLogger {

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        scheduler: Scheduler = Configuration.defaultScheduler,
        mdcContextKey: String = Configuration.defaultReactorContextMdcKey,
        logger: Logger = LoggerFactory.getLogger(ReactiveLogger::class.java)
    ) : AReactive.Builder<Logger, ReactiveLogger>(logger, scheduler, mdcContextKey) {
        override fun build(): ReactiveLogger {
            return ReactiveLogger(logger, mdcContextKey, scheduler)
        }
    }
}
