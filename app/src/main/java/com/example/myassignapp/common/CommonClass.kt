package com.example.myassignapp.common

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Madhuri Patil on 3/14/2024.
 */


interface IPreferenceDataStoreAPI {
    suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> getFirstPreference(key: Preferences.Key<T>, defaultValue: T): T
    suspend fun <T> putPreference(key: Preferences.Key<T>, value: T)
    suspend fun <T> removePreference(key: Preferences.Key<T>)
    suspend fun clearAllPreference()
}

object PreferenceDataStoreConstants {
    val ACCESS_TOKEN_KEY = stringPreferencesKey("ACCESS_TOKEN_KEY")
    val REFRESH_TOKEN = stringPreferencesKey("REFRESH_TOKEN")

    // Samples
    val INT_KEY = intPreferencesKey("INT_KEY")
    val LONG_KEY = longPreferencesKey("LONG_KEY")
}

/** This custom class ensures that observers are notified of a value change only once.
 *  It uses an AtomicBoolean to keep track of whether there's a pending event or not */
class SingleEventLiveData<T> : MutableLiveData<T>() {
    // AtomicBoolean to track if there's a pending event
    private val pending = AtomicBoolean(false)

    // Override the observe function to handle single events
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) { value ->
            // Check if there's a pending event
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(value) // Notify the observer with the value
            }
        }
    }

    // Override the setValue function to set the value and mark it as pending
    override fun setValue(value: T) {
        pending.set(true) // Mark the event as pending
        super.setValue(value) // Set the value in the parent class (MutableLiveData)
    }
}