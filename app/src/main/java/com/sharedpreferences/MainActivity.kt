package com.sharedpreferences

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sharedpreferences.ui.EditProfile
import com.sharedpreferences.util.SPKeys
import com.sharedpreferences.util.booleanSP
import com.sharedpreferences.util.doubleSP
import com.sharedpreferences.util.floatSP
import com.sharedpreferences.util.intSP
import com.sharedpreferences.util.longSP
import com.sharedpreferences.util.stringSP
import com.sharedpreferences.util.stringSetSP

class MainActivity : AppCompatActivity() {

    private lateinit var fabNext: FloatingActionButton

    private var id by intSP(SPKeys.ID)
    private var firstName by stringSP(SPKeys.FIRST_NAME)
    private var lastName by stringSP(SPKeys.LAST_NAME)
    private var dateOfBirth by longSP(SPKeys.DOB)
    private var children by stringSetSP(SPKeys.CHILDREN)
    private var isDev by booleanSP(SPKeys.IS_DEV)
    private var charge by floatSP(SPKeys.CHARGE)
    private var balance by doubleSP(SPKeys.BALANCE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initSharedPreferenceVariable()
    }

    private fun initView() {
        fabNext = findViewById(R.id.fabNext)
        fabNext.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }
    }

    /**
     *This is how we can set value easily in shared preference variable
     */
    private fun initSharedPreferenceVariable() {
        id = 7
        firstName = "John"
        lastName = "Walker"
        dateOfBirth = 11607356L //May 15, 1970 8:15:56 AM
        children = setOf("Milly", "Niu")
        isDev = true
        charge = 70.0f
        balance = 25659.72
    }
}