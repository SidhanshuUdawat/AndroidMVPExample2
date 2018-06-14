package com.scout24.network

/**
 * Created by Sid on 14/06/2018.
 */

interface NetworkMonitorProvider {
    fun isConnected(): Boolean
}