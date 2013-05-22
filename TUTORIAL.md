tradable start-np TUTORIAL
===========================

In this tutorial, you will learn how this project was written. Namely you will learn:

 * How to gather and use account details
 * How to deal with instruments and Market data
 * How to use the previous two points to place all sorts of different orders
 
Let's get right into it then shall we?

Part 1: Using the tradable account related API
----------------------------------------------

The code snippets included in this part are almost all found in the code from the AccountRelatedClass file. 
We will here see how the code was written and how it works by taking a closer look at the 
*com.tradable.api.services.account* API. This API essentially allows us to monitor orders trades 
and positions on the current account. Tradable service API's usually have 3 to 4 main parts:

 1. A service
 2. A listener
 3. An event
 4. and occasionally: subscription.

The *com.tradable.api.services.account* is no exception to that and implements the three former. 
The way this API works (and all the other service APIs with no subscription) is the following: 
Using spring, we use the *@Autowired* annotation within our factory class to instantiate a 
*CurrentAccountService* object. In this situation,  The container or "application context" 
injects an instance of *CurrentAccountService* into our factory. This is what is done in our factory
when we write: 
```java
@Autowired
CurrentAccountService accountSubscriptionService;
```
Note: there is no need to use the *new* qualifier as spring takes care of this
for us. This notation works by placing an instance of the CurrentAccountService class
bean into our factory (which is a spring bean). The @Autowired notation only works
if both the calling and the called interfaces/classes are beans. This means that all 
the services we use in our factory class have been defined in the tradable API as being beans. Of course, the
*TradableStartNpFactory* class is also a bean as we specify it in app-context.xml.

Once our *CurrentAccountService* object is created within our factory, we pass it on to our Module class
within the factorie's *createModule()* method.
