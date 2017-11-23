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
    compile 'com.github.roadmaptravel:Curry:0.1'
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
