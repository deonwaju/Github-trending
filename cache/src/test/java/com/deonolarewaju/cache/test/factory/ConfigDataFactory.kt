package com.deonolarewaju.cache.test.factory

import com.deonolarewaju.cache.model.Config

object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }
}