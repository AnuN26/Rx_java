package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var  btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.button)
        btn.setOnClickListener(View.OnClickListener {
            val animals = ArrayList<Any>()
            animals.add("Tiger")
            animals.add("Lion")
            animals.add("Cat")
            animals.add("Elephant")
            io.reactivex.Observable.just(animals)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : io.reactivex.Observer<ArrayList<Any>>{
                    override fun onSubscribe(d: Disposable) {
                        Log.d("onSubscribe","subscribed:$d")
                    }
                    override fun onNext(t: ArrayList<Any>) {
                        //handling the result
                        Log.d("Test", "In onNext():$animals")
                    }
                    override fun onError(e: Throwable) {
                        Log.d("onError()", "In Error():$e")
                    }
                    override fun onComplete() {
                        Log.d("onCompleted()","In Complete:$")
                    }
                })
            rxJavaObservable()
        })


        val observable = io.reactivex.Observable.just(1,2,3)
        observable.subscribe(object : io.reactivex.Observer<Int> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                Log.d("Test", "In onNext():$t")
            }

            override fun onError(e: Throwable) {
                Log.d("Test", "In onError()")
            }

            override fun onComplete() {
                Log.d("Test", "In onCompleted()")
            }

        })

    }


    private fun rxJavaObservable() {

        val list = ArrayList<Int>()
        list.add(1)
        list.add(2)
        list.add(3)
        list.add(4)
        list.add(5)
        val list2 = ArrayList<Int>()
        list2.add(10)
        list2.add(20)
        list2.add(30)
        list2.add(40)
        list2.add(50)
        io.reactivex.Observable.fromArray(list + 1 + "abc" + "awa" + list2)
            .subscribe(object : io.reactivex.Observer<Any> {
                override fun onComplete() {
                    Log.d("completed", "onComplete :")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d("Subscription", "Subscription : $d")
                }

                override fun onNext(t: Any) {
                    Log.d("onNext", "onNext : $t")
                }

                override fun onError(e: Throwable) {
                    Log.d("onError", "onError : $e")
                }

            })

    }

}