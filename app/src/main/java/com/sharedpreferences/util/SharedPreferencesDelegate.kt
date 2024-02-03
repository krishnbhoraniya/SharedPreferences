package com.sharedpreferences.util

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *@param context
 *
 * @property key The name of the preference.
 * @property defaultValue The value to be returned if the preference does not exist
 * @property getter The getter method to be used to retrieve the preference
 * @property setter The setter method to be used to store the preference
 */
class SharedPreferencesDelegate<T>(
    private val context: Context,
    private val key: String,
    private val defaultValue: T,
    private val getter: SharedPreferences.(String, T) -> T?,
    private val setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor
) : ReadWriteProperty<Any?, T?> {

    // Name of the preference file under data/data/application_preference package
    private val sharedPreferences by lazy {
        context.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
    }

    /**
     * Retrieves the value of the preference.
     *
     * @param thisRef Reference to the delegating object.
     * @param property Reference to the delegating property.
     *
     * @return The value of the preference.
     */
    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return if (sharedPreferences.contains(key)) {
            sharedPreferences.getter(key, defaultValue)
        } else {
            null
        }
    }

    /**
     * Stores the value of the preference.
     *
     * @param thisRef Reference to the delegating object.
     * @param property Reference to the delegating property.
     * @param value The value of the preference.
     */
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        val editor = sharedPreferences.edit()
        if (value != null) editor.setter(key, value)
        else editor.remove(key)
        editor.apply()
    }
}

/**
 * Creates a new instance of [SharedPreferencesDelegate]
 * for an Int preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [SharedPreferencesDelegate] for an Int preference.
 */
fun Context.intSP(key: String) = SharedPreferencesDelegate<Int>(
    this, key, 0, SharedPreferences::getInt, SharedPreferences.Editor::putInt
)

/**
 * Creates a new instance of [SharedPreferencesDelegate]
 * for a String preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [SharedPreferencesDelegate] for a String preference.
 */
fun Context.stringSP(key: String) = SharedPreferencesDelegate<String>(
    this, key, "", SharedPreferences::getString, SharedPreferences.Editor::putString
)

/**
 * Creates a new instance of [SharedPreferencesDelegate]
 * for a String Set preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [SharedPreferencesDelegate] for a String Set preference.
 */
fun Context.stringSetSP(key: String) = SharedPreferencesDelegate<Set<String>>(
    this,
    key,
    emptySet<String>(),
    SharedPreferences::getStringSet,
    SharedPreferences.Editor::putStringSet
)

/**
 * Creates a new instance of [SharedPreferencesDelegate]
 * for a Boolean preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [SharedPreferencesDelegate] for a Boolean preference.
 */
fun Context.booleanSP(key: String) =
    SharedPreferencesDelegate<Boolean>(
        this,
        key,
        false,
        SharedPreferences::getBoolean,
        SharedPreferences.Editor::putBoolean
    )

/**
 * Creates a new instance of [SharedPreferencesDelegate]
 * for a Float preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [SharedPreferencesDelegate] for a Float preference.
 */
fun Context.floatSP(key: String) =
    SharedPreferencesDelegate<Float>(
        this,
        key,
        0f,
        SharedPreferences::getFloat,
        SharedPreferences.Editor::putFloat
    )

/**
 * Creates a new instance of [SharedPreferencesDelegate]
 * for a Double preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [SharedPreferencesDelegate] for a Double preference.
 */
fun Context.doubleSP(key: String) =
    SharedPreferencesDelegate<Double>(
        this, key, 0.0,
        SharedPreferences::getDouble,
        SharedPreferences.Editor::putDouble
    )

/**
 * Creates a new instance of [SharedPreferencesDelegate]
 * for a Long preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [SharedPreferencesDelegate] for a Long preference.
 */
fun Context.longSP(key: String) =
    SharedPreferencesDelegate<Long>(
        this,
        key,
        0L,
        SharedPreferences::getLong,
        SharedPreferences.Editor::putLong
    )

/**
 * Retrieves a Double preference.
 *
 * @param key The name of the preference.
 * @param defaultValue The value to be returned if the preference does not exist.
 *
 * @return The value of the preference if it exists, or [defaultValue].
 */
private fun SharedPreferences.getDouble(key: String, defaultValue: Double): Double =
    if (contains(key)) java.lang.Double.longBitsToDouble(getLong(key, 0L))
    else defaultValue

/**
 * Stores a Double preference value in the [SharedPreferences.Editor].
 *
 * @param key The name of the preference.
 * @param value The new value of the preference.
 *
 * @return The same [SharedPreferences.Editor] object.
 */
private fun SharedPreferences.Editor.putDouble(
    key: String,
    value: Double
): SharedPreferences.Editor {
    putLong(key, java.lang.Double.doubleToRawLongBits(value))
    return this
}