tradable-start-np
=================
A starting point for creating tradable apps. Just clone and start hacking away. This starting point  includes code that:

* Places a market order
* Sends a pending order and watches for its execution
* Monitors the change in position once an order is placed
* Changes a pending order
* Send an OCO (one cancels other) order

It presents this in a small app that includes a button, two fields containing current prices of an 
instrument and a small output window. When clicked, the button passes hard-coded arbitrarily set orders for trades.

* The first click randomly selects the instrument to use from the list of available instruments
* The second click sends a short market order that should be filled immediately
* The third click will send a long limit order that shouldn't be filled (at 85% of the latest ask price)
* The fourth click will change that unfilled working order and send a new long limit order for a different amount which should be filled as it is slightly above the ask price (101% of ask price)
* The fifth click will Set an OCO order on an open position on the selected instrument.
* You can monitor the latest quotes for the selected instrument right below the button in the 2 text fields. 
Furthermore, you can monitor the execution of your orders in the window right below those fields.


If you want to get started with tradable Apps using this project as a base for your code, go check out the 
README included in the tradable project to see how to open the project in Eclipse.
