package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.aead.AeadKeyTemplates
import com.google.crypto.tink.config.TinkConfig
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TinkConfig.register()
        AeadConfig.register()
        val keysetHandle = KeysetHandle.generateNew(AeadKeyTemplates.AES128_EAX)
        val aead = keysetHandle.getPrimitive(Aead::class.java)
        val encData = aead.encrypt("abc".toByteArray(), "".toByteArray())
        val s1 = Gson().toJson(encData)
        val s2 = Gson().fromJson(s1, ByteArray::class.java)
        Log.e("rick", "rick s1:$s1")
        Log.e("rick", "rick s2:$s2")
        val s3 = aead.decrypt(s2, byteArrayOf())
        Log.e("rick", "rick s3:${String(s3)}")
    }
}
