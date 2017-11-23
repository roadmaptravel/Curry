package com.getroadmap.curry

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.getroadmap.lib.models.ForExRates
import com.getroadmap.lib.request.CurryApiClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rateAdapter = RateAdapter(this, emptyList(), {
            Log.d("DEBUG", it.toString())
        })

        recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerview.adapter = rateAdapter

        CurryApiClient(this).service.getLatestRates()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<ForExRates> {
                    override fun onError(e: Throwable) {
                        Log.w("DEBUG", e.toString())
                    }

                    override fun onSuccess(t: ForExRates) {
                        Log.d("DEBUG", t.toString())
                        rateAdapter.updateRates(t.rates.getRates())
                        base.setText("1 ${t.base} = ")
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                })
    }
}
