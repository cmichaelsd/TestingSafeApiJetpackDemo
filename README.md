# Android Safe API Jetpack Demo
> The project demonstrates a MVP for using modern Jetpack Architecture and testing API calls with databinding enabled.
> There is magic happening with the OkHttp3IdlingResource the only reason it works is because "android.enableJetifier=true" is set in grable.properties.
> Make sure to enter a Giantbomb API key in MyInterceptor as constant API_TOKEN.

> Espresso does not know how to wait for asynchronous calls so these idling resources are created to inform the test when it can proceed.

Using code from:
- https://github.com/douglasiacovelli/safe-api-caller -> Safe API caller resource
- https://github.com/michaelbukachi/CoroutinesIdlingResourcesDemo -> Coroutines IdlingResource
- https://github.com/android/architecture-components-samples/tree/master/LiveDataSample -> DataBinding IdlingResource
