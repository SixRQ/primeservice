# Prime Service

A RESTful service that takes a number as input and calculates all prime numbers up to and including the passed number.

The service supports both JSON and XML return types as specified in the request header.

### Running the application

The application can be run on a Linux operating system using the gradle task bootRun

```./gradlew bootRun```

alternatively using java

```./gradlew build && java -jar build/libs/primeservice-0.1.0.jar```

The URL to access the service is in the form ```http://localhost:8080/primes/<integer value>``` where *<integer value>* is the passed value to calculate up to.

If you are running the service as detailed above this URL [http://localhost:8080/primes/10](http://localhost:8080/primes/10) should provide the following response.

```
<PrimesResult xmlns="">
  <initial>10</initial>
  <primes>
    <primes>2</primes>
    <primes>3</primes>
    <primes>5</primes>
    <primes>7</primes>
  </primes>
</PrimesResult>
```

or

```
{
  "initial":10,
  "primes":[2,3,5,7]
}
```

### Error handling

Errors are returned as an error code, with details in the response body as follows 

```
<ErrorResponse xmlns="">
  <status>400</status>
  <message>NumberFormatException: For input string: "10.5"</message>
  <stackTrace>org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: "10.5"
	at org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver.resolveArgument(AbstractNamedValueMethodArgumentResolver.java:131)
	at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:124)
	at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:161)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:131)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:866)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:635)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:790)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1459)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:745)
    Caused by: java.lang.NumberFormatException: For input string: "10.5"
	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
	at java.lang.Integer.parseInt(Integer.java:580)
	at java.lang.Integer.valueOf(Integer.java:766)
	at org.springframework.util.NumberUtils.parseNumber(NumberUtils.java:210)
	at org.springframework.beans.propertyeditors.CustomNumberEditor.setAsText(CustomNumberEditor.java:115)
	at org.springframework.beans.TypeConverterDelegate.doConvertTextValue(TypeConverterDelegate.java:466)
	at org.springframework.beans.TypeConverterDelegate.doConvertValue(TypeConverterDelegate.java:439)
	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:192)
	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:99)
	at org.springframework.beans.TypeConverterSupport.doConvert(TypeConverterSupport.java:73)
	at org.springframework.beans.TypeConverterSupport.convertIfNecessary(TypeConverterSupport.java:52)
	at org.springframework.validation.DataBinder.convertIfNecessary(DataBinder.java:692)
	at org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver.resolveArgument(AbstractNamedValueMethodArgumentResolver.java:123)
	... 51 more
  </stackTrace>
</ErrorResponse>
```

or 
```
{
  "status":400,
  "message":"NumberFormatException: For input string: \"10.5\"",
  "stackTrace":"org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: \"10.5\"\n\tat org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver.resolveArgument(AbstractNamedValueMethodArgumentResolver.java:131)\n\tat org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:124)\n\tat org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:161)\n\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:131)\n\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)\n\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)\n\tat org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:866)\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:635)\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496)\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:790)\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1459)\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\n\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\n\tat java.lang.Thread.run(Thread.java:745)\nCaused by: java.lang.NumberFormatException: For input string: \"10.5\"\n\tat java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)\n\tat java.lang.Integer.parseInt(Integer.java:580)\n\tat java.lang.Integer.valueOf(Integer.java:766)\n\tat org.springframework.util.NumberUtils.parseNumber(NumberUtils.java:210)\n\tat org.springframework.beans.propertyeditors.CustomNumberEditor.setAsText(CustomNumberEditor.java:115)\n\tat org.springframework.beans.TypeConverterDelegate.doConvertTextValue(TypeConverterDelegate.java:466)\n\tat org.springframework.beans.TypeConverterDelegate.doConvertValue(TypeConverterDelegate.java:439)\n\tat org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:192)\n\tat org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:99)\n\tat org.springframework.beans.TypeConverterSupport.doConvert(TypeConverterSupport.java:73)\n\tat org.springframework.beans.TypeConverterSupport.convertIfNecessary(TypeConverterSupport.java:52)\n\tat org.springframework.validation.DataBinder.convertIfNecessary(DataBinder.java:692)\n\tat org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver.resolveArgument(AbstractNamedValueMethodArgumentResolver.java:123)\n\t... 51 more\n"
}
```

## Comments/Assumptions for the exercise

* Although the sample JSON provided in instructions has names starting with an upper case I decided to follow the camel case convention that is standard for JSON names
* The MockMvc class does not support custom error handlers so an explicit test was added to call the /error endpoint to check the json returned. Although this test does not return a bad request, passing an invalid initial to the primes endpoint correctly sets the request status in addition to returning the error json. This was tested with Postman
* Performance tests are included and some rudimentary optimisation has been performed by caching results and splitting requests into chunks and running those chunks in multiple threads. Results from a sample of tests are as follows:
    * Primes to 100,000
      * 1 thread and no chuncking took approx 2 seconds
      * 100 threads and chuncking to 1,000 took approx 1.5 seconds
      * 30 sequential requests with 100 threads and chuncking to 1,000 without caching took 12 seconds
      * 30 sequential requests with 100 threads and chuncking to 1,000 with caching took 1 second
    * Primes to 1,000,000
      * 1 thread and no chunking too approx 2 minutes
      * 100 threads and chuncking to 1,000 took approx 1 minute
      * 100 threads and chuncking to 10,000 took approx 30 seconds
      * A second called with cached results returns in 59 miliseconds
* Logging was added to the service but this was found have a significant impact on run times so has been removed.  