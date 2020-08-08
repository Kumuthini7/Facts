package com.example.facts.network

/**
 * Created by Kumuthini.N on 08-08-2020
 */
class Resource<T>(
    val status: Status,
    var data: T?
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }

        val resource = other as Resource<*>?

        if (status !== resource!!.status) {
            return false
        }

        return if (data != null) data == resource?.data else resource?.data == null
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", data=" + data +
                '}'
    }

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(data: T?): Resource<T> {
            return Resource(Status.ERROR, data)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data)
        }
    }
}