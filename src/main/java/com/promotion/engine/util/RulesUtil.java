package com.promotion.engine.util;

import java.util.List;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.promotion.engine.domain.Product;


/**
 * Singleton class that wraps
 * the Drools KieSession and is responsible
 * for loading rules and firing rules.
 */
@Component
public class RulesUtil {

    private KieServices ks;
    private KieContainer kContainer;
    private KieSession kieSession;

    //@Bean("RulesUtil")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RulesUtil getRulesService() {
        return new RulesUtil();
    }

    /**
     * Function to initialize the kieSession.
     * This will be initialized on
     * application startup.
     */
    public void initializeRules() {

        this.ks = KieServices.Factory.get();
        this.kContainer = ks.getKieClasspathContainer();
        //this.kieSession =  kContainer.newKieSession();
    }

    public int fireRules(List<Product> products) {
    	this.kieSession =  kContainer.newKieSession();
    	for(Product product: products) {
    		this.kieSession.insert(product);
    	}
    	//List<String> skuCodes =Arrays.asList("A","B");
    	//this.kieSession.insert(skuCodes);
    	
        int fired = this.kieSession.fireAllRules();
        this.kieSession.dispose();
        return fired;

    }
}