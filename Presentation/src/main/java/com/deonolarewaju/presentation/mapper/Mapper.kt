package com.deonolarewaju.presentation.mapper

import java.lang.Exception

interface Mapper<out V, in D> {

    fun mapToView(type: D): V

}