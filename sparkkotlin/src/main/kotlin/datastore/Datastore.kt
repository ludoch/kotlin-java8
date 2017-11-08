package datastore

import com.google.appengine.api.datastore.DatastoreService
import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.Transaction
import com.google.appengine.api.datastore.TransactionOptions


operator fun Entity.get(key: String): Any {
    return getProperty(key)
}

operator fun Entity.set(key: String, value: Any) {
    setProperty(key, value)
}

inline fun DatastoreService.transaction(block: DatastoreService.(txn: Transaction) -> Unit) {
    transaction(block, TransactionOptions.Builder.withDefaults())
}

inline fun DatastoreService.transaction(
        block: DatastoreService.(txn: Transaction) -> Unit,
        options: TransactionOptions) {
    val txn = beginTransaction(options)
    var ok = false
    try {
        this.block(txn)
        ok = true
    } finally {
        if (ok) {
            txn.commit()
        } else {
            txn.rollback()
        }
    }
}
