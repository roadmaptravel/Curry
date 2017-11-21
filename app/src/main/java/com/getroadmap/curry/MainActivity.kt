package com.getroadmap.curry

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.getroadmap.lib.models.ForExRates
import com.getroadmap.lib.request.CurryApiClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CurryApiClient(this).service.getLatest()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<ForExRates> {
                    override fun onError(e: Throwable) {
                        Log.w("DEBUG", e.toString())
                    }

                    override fun onSuccess(t: ForExRates) {
                        Log.d("DEBUG", t.toString())
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                })
    }
}
