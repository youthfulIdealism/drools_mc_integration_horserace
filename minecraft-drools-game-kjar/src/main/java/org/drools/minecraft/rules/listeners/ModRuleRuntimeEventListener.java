/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.rules.listeners;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

/**
 *
 * @author salaboy
 */
public class ModRuleRuntimeEventListener implements RuleRuntimeEventListener {

    @Override
    public void objectInserted(ObjectInsertedEvent oie) {
        System.out.println(oie);
    }

    @Override
    public void objectUpdated(ObjectUpdatedEvent oue) {
        System.out.println(oue);
    }

    @Override
    public void objectDeleted(ObjectDeletedEvent ode) {
        System.out.println(ode);
    }

}
