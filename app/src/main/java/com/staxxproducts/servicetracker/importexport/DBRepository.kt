@file:Suppress("BlockingMethodInNonBlockingContext", "BlockingMethodInNonBlockingContext")

package com.staxxproducts.servicetracker.importexport

import android.content.Context
import android.net.Uri
import com.staxxproducts.servicetracker.data.VehicleDatabase
import com.staxxproducts.servicetracker.db
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class DBRepository(
  private val context: Context,
  private val appScope: CoroutineScope) {


  private val mutex = Mutex()
  private val dispatcher =
    Executors.newSingleThreadExecutor().asCoroutineDispatcher()


  suspend fun export(uri: Uri) {
    withContext(dispatcher + appScope.coroutineContext) {
      mutex.withLock {
        db.close()
        context.contentResolver.openOutputStream(uri)?.use {
          VehicleDatabase.copyTo(context, it)
        }
      }
    }
  }

  suspend fun import(uri: Uri) {
    withContext(dispatcher + appScope.coroutineContext) {
      mutex.withLock {
        db.close()
        context.contentResolver.openInputStream(uri)?.use {
          VehicleDatabase.copyFrom(context, it)
        }
      }
    }
  }


}