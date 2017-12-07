[![](https://jitpack.io/v/roadmaptravel/Curry.svg)](https://jitpack.io/#roadmaptravel/Curry)

# Curry
Curry is a currency conversion library for Android. It's build on top of the [Fixer api](http://fixer.io/). 
Fixer is a free API for current and historical foreign exchange rates published by the European Central Bank.
The rates are updated daily around 4PM CET every working day. 

## Download
### Gradle
Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency
```
dependencies {
    compile 'com.github.roadmaptravel:Curry:0.1.2'
}

```

## Usage

This library makes use of RxAndroid for its calls.

```kotlin
CurryApiClient(this).service.getLatestRates()
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
```

**`getLatest(currencyCode: String)`** returns an `Single` which emits the `ForExRates` data. The data contains the latest foreign exchange reference rates.

**`getHistoricalRates(date: String)`** returns an `Single` which emits the `ForExRates` data. The data contains rates for any day since 1999. Choose a day by setting the `date` in your request. The format is `2000-01-03`.

**`getSpecificRates(symbols: String)`** returns an `Single` which emits the `ForExRates` data. The data contains the latest foreign exchange reference rates for the chosen `symbols`. 


All rates are quoted against the Euro by default. Quote against a different currency by setting the base `currencyCode` as a second parameter. 
e.g. ```getHistoricalRates("2000-01-03", "USD") ```
All currency codes are int the [ISO 4217](https://en.wikipedia.org/wiki/ISO_4217) format.


## License

    Copyright 2017 Roadmap
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
