/*************************************************************************************
 * This program is to be used as a learning tool and as a basis for creating
 * bigger more elaborate tradable apps. The use of the different tradable APIs is 
 * heavily commented and the different services and listeners used are segmented
 * in an obvious manner so as to allow developers to remove and keep only the
 * code samples of interest to them. 
 * 
 *  For the README on how to to start working on your own project using this 
 *  code, please go to: https://github.com/john-dwuarin/tradable-start-np
 *************************************************************************************/

package com.tradable.exampleApps.TradableStartNp;

//==Will be imported in most of your apps in order to use the @Autowired notation==//

import org.springframework.beans.factory.annotation.Autowired;


//========= (0) component API, has to be imported in any project==========//
//====================================================================================
import com.tradable.api.component.WorkspaceModule;
import com.tradable.api.component.WorkspaceModuleCategory;
import com.tradable.api.component.WorkspaceModuleFactory;
//====================================================================================
//====================================================================================


//========= (1) Import if App will be using the CurrentAccountService API==========//
//This will be the case for most apps as account changes are quite important to listen to
//====================================================================================
import com.tradable.api.services.account.CurrentAccountService;


//========= (2) Import if App will be using the InstrumentService API==========//
//We also import QuoteTickService to use a live feed of the markets prices.
import com.tradable.api.services.instrument.InstrumentService;
import com.tradable.api.services.marketdata.QuoteTickService;
import com.tradable.api.services.preferences.PreferenceService;


//========= (3) Import if App will be using the TradingRequestExecutor API==========//
//Import this if you want your App to be able to place, change or cancel any trades.
//====================================================================================
import com.tradable.api.services.executor.TradingRequestExecutor;



public class TradableStartNpFactory implements WorkspaceModuleFactory{

	//========= (1) CurrentAccountService object has to be instantiated here.==========//
	//The container or "application context" injects an instance of CurrentAccountService 
	//We note that there is no need to use the new qualifier as spring takes care of this 
	//for us. This notation only works by placing an instance of the CurrentAccountService
	//bean into the HtCreateNewPositionFactory bean. The @Autowired notation only works
	//if both interfaces/classes are beans. This means that the all the services we use
	//here have been defined in the tradable API as being beens. Of course, the 
	//HtCreateNewPositionFactory class is also a been as we specify it in app-context.xml
	//====================================================================================
	@Autowired
	CurrentAccountService accountSubscriptionService;
	
	
	//========= (2) InstrumentService object has to be instantiated here.==========//
	//We also instantiate a QuoteTickService object which will allow us to monitor in
	//real time the price of a selected instrument.
	//====================================================================================
	@Autowired
	InstrumentService instrumentService;
	
	@Autowired
	private QuoteTickService quoteTickService; 
	//====================================================================================
	//====================================================================================
	
	
	
	
	//========= (3) TradingRequestExecutor object has to be instantiated here.==========//
	//The TradingRequestExecutor is also a bean and is the equivalent of ou services when
	//it comes to executing stuff rather than listening for stuff to happen.
	//====================================================================================
	@Autowired
	private TradingRequestExecutor executor; 
	//====================================================================================
	//====================================================================================
	
	@Autowired
	private PreferenceService preferenceService;
	
	
	//== (0) just the 4 interfaces that have to be implemented as per our component API=//
	//====================================================================================
	
	@Override
	public WorkspaceModule createModule() {

		return new TradableStartNpModule(executor, accountSubscriptionService, 
				instrumentService, quoteTickService, preferenceService);
	}

	@Override
	public WorkspaceModuleCategory getCategory() {
		return WorkspaceModuleCategory.MISCELLANEOUS;
	}

	@Override
	public String getDisplayName() {
		//rename me
		return "tradable-start-np";
	}

	@Override
	public String getFactoryId() {
		//don't forget to change me!!!
		return "com.tradable.exampleApps.TradableStartNp";
	}
	
	//====================================================================================
	//====================================================================================
	
}
