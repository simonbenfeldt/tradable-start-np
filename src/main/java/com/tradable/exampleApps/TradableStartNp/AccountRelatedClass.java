package com.tradable.exampleApps.TradableStartNp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import com.tradable.api.entities.Account;
import com.tradable.api.entities.Order;
import com.tradable.api.entities.OrderStatus;
import com.tradable.api.entities.OrderType;
import com.tradable.api.entities.Position;
import com.tradable.api.entities.Trade;
import com.tradable.api.services.account.AccountUpdateEvent;
import com.tradable.api.services.account.CurrentAccountService;
import com.tradable.api.services.account.CurrentAccountServiceListener;

public class AccountRelatedClass implements CurrentAccountServiceListener {

	//========================================(1)========================================//
	//Declaring CurrentAccountService object and Account object. The CurrentAccountService
	//object has been instantiated in our factory. We declare it here too, in
	//order for it to be accessible in other methods of the AccountRelatedClass and not just the constructor.
	//We also declare an Account object which will be set whenever the accountUpdated method
	//is called by the container. Such events occur whenever the accounts listens onto 
	//any change in the account (may it be Orders, Positions Trades, or simply if the account was switched).
	//Upon instantiation the account object is set to the account that instantiated the apps Module.
	//The accountId value is found subsequently but has to be changed if an event set to accountUpdated
	//was a reset event i.e. the account was switched. The accountId is used for passing orders
	//==================================================================================
	
	private CurrentAccountService currentAccountService;
	private Account currentAccount;
	private int accountId;
	
	private JTextPane textPane;
	
	public AccountRelatedClass(CurrentAccountService currentAccountService, JTextPane textPane){
		
		this.textPane = textPane;
		
		//the "this" object is a CurrentAccountServiceListener object too, so we can add listeners 
		//this way. We also set the accountService object to the one Autowired in the factory 
		
		this.currentAccountService = currentAccountService;
		this.currentAccountService.addListener(this);
		
		currentAccount = currentAccountService.getCurrentAccount();
		if (currentAccount != null) 
			//only set accountId if service was ready, else, wait for listener to do so.
			accountId = currentAccount.getAccountId();
		
	}
	
	public void destroy() {
		currentAccountService.removeListener(this);
	}
	
	
	public int getAccountId(){
		return accountId;
	}
	
	public Account getCurrentAccount(){
		
		return currentAccount;
	}
	
	
	//========================================(1)========================================//
	//We recall that CurrentAccountServiceListener fires up an event whenever
	//an order is placed, a trade takes place, a position changed or if the account is switched. 
	//This method, on top of switching the accountId object when account is switched thus
	//also includes code that simply writes down whatever it sees happening in the textPane.
	//We note that currentAccount is set on every event, as this is currently the only way
	//to always have the latest information in the currentAccount object.
	//====================================================================================	


	@Override
    public void accountUpdated(AccountUpdateEvent event) {
	
		currentAccount = currentAccountService.getCurrentAccount();
        if (event.isReset()) {
        	
    		accountId = currentAccount.getAccountId();
        } 
        else {
        	
            Collection<Order> myChangedOrders = event.getChangedOrders();
            Collection<Trade> mychangedTrades = event.getChangedTrades();
            Collection<Position> mychangedPositions = event.getChangedPositions();
            
            try {
            
                
                for (Order order : myChangedOrders){
                	
                	if (order.getStatus() == OrderStatus.ACCEPTED){
                		textPane.getDocument().insertString(textPane.getDocument().getLength() ,
     					"Order for " + order.getQuantity() + " " + order.getInstrument().getSymbol()
     					+ " accepted \n", null);
     					break;
                	}
                	
                	else if (order.getStatus() == OrderStatus.COMPLETED){
                		textPane.getDocument().insertString(textPane.getDocument().getLength() ,
                		"Order for " + order.getQuantity() + " " + order.getInstrument().getSymbol()
                		+ " completed \n", null);
        				break;
                	}
                	
                	else if (order.getStatus() == OrderStatus.CANCELED){
                		textPane.getDocument().insertString(textPane.getDocument().getLength() ,
                		"Order for " + order.getQuantity() + " " + order.getInstrument().getSymbol()
                		+ " canceled \n", null);
        				break;
                	}
                	
                	else if (order.getStatus() == OrderStatus.EXPIRED){
                		textPane.getDocument().insertString(textPane.getDocument().getLength() ,
                		"Order for " + order.getQuantity() + " " + order.getInstrument().getSymbol()
                		+ " expired \n", null);
        				break;
                	}
                	
                	else if (order.getStatus() == OrderStatus.NEW){
                		textPane.getDocument().insertString(textPane.getDocument().getLength() ,
                		"Order for " + order.getQuantity() + " " + order.getInstrument().getSymbol()
                		+ " new \n", null);
        				break;
                	}
                	
                	else if (order.getStatus() == OrderStatus.REJECTED){
                		textPane.getDocument().insertString(textPane.getDocument().getLength() ,
                		"Order for " + order.getQuantity() + " " + order.getInstrument().getSymbol()
                		+ " rejected \n", null);
        				break;
                	}
                	
                	
                	else if (order.getStatus() == OrderStatus.REPLACED){
                		textPane.getDocument().insertString(textPane.getDocument().getLength() ,
                		"Order for " + order.getQuantity() + " " + order.getInstrument().getSymbol()
                		+ " replaced \n", null);
        				break;
                	}
                	
                	else if (order.getStatus() == OrderStatus.WAITING){
                		textPane.getDocument().insertString(textPane.getDocument().getLength() ,
                		"Order for " + order.getQuantity() + " " + order.getInstrument().getSymbol()
                		+ " waiting \n", null);
        				break;
                	}
                	
                	else if (order.getStatus() == OrderStatus.WORKING){
                		
                		if (order.getType() == OrderType.LIMIT){
    	            		textPane.getDocument().insertString(textPane.getDocument().getLength() ,
    	    	            "Order for " + order.getQuantity() + " " + order.getInstrument().getSymbol()
    	    	            + " limit order is working \n", null);
    	    	            break;
                		}
                		
                		else if (order.getType() == OrderType.MARKET){
    	            		textPane.getDocument().insertString(textPane.getDocument().getLength() ,
    	    	            "Order for " + order.getQuantity() + " " + order.getInstrument().getSymbol()
    	    	            + " market order is working \n", null);
    	    	            break;
                		}
                		
                		else if (order.getType() == OrderType.STOP){
    	            		textPane.getDocument().insertString(textPane.getDocument().getLength() ,
    	    	            "Order for " + order.getQuantity() + " " + order.getInstrument().getSymbol()
    	    	            + " stop order is working \n", null);
    	    	            break;
                		}
                    	
                		else{
    	            		textPane.getDocument().insertString(textPane.getDocument().getLength() ,
    	    	            "Order for " + order.getQuantity() + " " + order.getInstrument().getSymbol()
    	    	            + " is working \n", null);
    	            		break;
                		}
                			
                	}
                	
                }
                
                
                for (Trade trade : mychangedTrades){
                	
    				if (trade.getQuantity() > 0){
    					textPane.getDocument().insertString(textPane.getCaretPosition() , 
    							" You just went long " + trade.getQuantity() + " "
    							+ trade.getInstrument().getSymbol() 
    							+ " at the price of " + trade.getPrice() + "\n", null);
    				}
    				else if (trade.getQuantity() < 0){
    					textPane.getDocument().insertString(textPane.getCaretPosition() , 
    							" You just went short (" + Math.abs(trade.getQuantity()) + ") " 
    							+ trade.getInstrument().getSymbol() 
    							+ " at the price of " + trade.getPrice() + "\n", null);
    				}
    				else{
    					textPane.getDocument().insertString(textPane.getCaretPosition() , 
    							"You apparently traded nothing, this should never happen. This is a bug."
    							+ "\n" , null);
    				}
        	
            	
                }
                	
                
                for (Position position : mychangedPositions){
                		

    				if (position.getQuantity() > 0){
    					textPane.getDocument().insertString(textPane.getCaretPosition() , 
    							" You are now long " + position.getQuantity() + " " 
    							+ position.getInstrument().getSymbol()+ "\n" , null);
    				}
    				else if (position.getQuantity() < 0){
    					textPane.getDocument().insertString(textPane.getCaretPosition() ,
    							" You are now short (" + Math.abs(position.getQuantity()) + ") " 
    							+ position.getInstrument().getSymbol()+ "\n" , null);
    				}
    				else{
    					textPane.getDocument().insertString(textPane.getCaretPosition() ,
    							" You are now flat on " + position.getInstrument().getSymbol()
    							+ "\n" , null);
    				}
                	            	
                }
                
                
    			textPane.getDocument().insertString(textPane.getCaretPosition() , "\n" , null);
            
    		} 
            
            catch (BadLocationException e) {
    			e.printStackTrace();
    		}   		

        }       

    }	
    //====================================================================================
    //====================================================================================	
	
	public Order getOrder(String clientOrderId){
		List<Order> accountOrders = currentAccount.getOrders();
		Order searchedForOrder = null;
		for (Order order : accountOrders){
			if (order.getClientOrderId().equals(clientOrderId)){
				searchedForOrder = order;
			}
		}
		
		return searchedForOrder;
	}
	
	
	//this method is not used in the current iteration of the example, 
	//but it might still be of use for some people.
	public List<Order> getWorkingOrdersList(){

		//to get the list of all working orders, I.e. orders that i can actually modify. 
		List<Order> workingOrders = new ArrayList<Order>(); 
		for (Order order : currentAccount.getOrders()){
			if (order.getStatus() == OrderStatus.WORKING){
				workingOrders.add(order);
			}
		}
		
		if (workingOrders.size() > 0) 
			return workingOrders;
		else
			return null;
	}
	

}
