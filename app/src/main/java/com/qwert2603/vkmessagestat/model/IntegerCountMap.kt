package com.qwert2603.vkmessagestat.model

import java.util.*

class IntegerCountMap : HashMap<Int, Int>() {

    var totalSum = 0

    fun add(key: Int, value: Int = 1) {
        if (!containsKey(key)) {
            put(key, 0)
        }
        val prev = get(key)
        if (prev != null) {
            put(key, prev.plus(value))
        }
        totalSum += value
    }

    fun addAll(anth: IntegerCountMap): IntegerCountMap {
        anth.forEach { k, v -> add(k, v) }
        return this
    }

    fun notEmpty() = !isEmpty()

    fun keysAsList() = keys.toList()

    fun getPercent(key: Int) : Double {
        val v = get(key)
        if (v != null) {
            return v * 100.0 / totalSum
        } else {
            return 0.0;
        }
    }


}