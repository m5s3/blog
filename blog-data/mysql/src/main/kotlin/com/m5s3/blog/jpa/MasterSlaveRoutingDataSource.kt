package com.m5s3.blog.jpa

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager

class MasterSlaveRoutingDataSource :
    AbstractRoutingDataSource()
{
    override fun determineCurrentLookupKey(): Any {
        return if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            "slave"
        } else {
            "master"
        }
    }
}