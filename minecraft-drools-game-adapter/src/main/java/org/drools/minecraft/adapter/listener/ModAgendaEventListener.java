/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drools.minecraft.adapter.listener;

import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;

/**
 *
 * @author salaboy
 */
public class ModAgendaEventListener implements AgendaEventListener {

    @Override
    public void matchCreated(MatchCreatedEvent mce) {
        System.out.println(mce);
    }

    @Override
    public void matchCancelled(MatchCancelledEvent mce) {
        System.out.println(mce);
    }

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent bmfe) {
        System.out.println(bmfe);
    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent amfe) {
        System.out.println(amfe);
    }

    @Override
    public void agendaGroupPopped(AgendaGroupPoppedEvent agpe) {
        System.out.println(agpe);
    }

    @Override
    public void agendaGroupPushed(AgendaGroupPushedEvent agpe) {
        System.out.println(agpe);
    }

    @Override
    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent rfgae) {
        System.out.println(rfgae);
    }

    @Override
    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent rfgae) {
        System.out.println(rfgae);
    }

    @Override
    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent rfgde) {
        System.out.println(rfgde);
    }

    @Override
    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent rfgde) {
        System.out.println(rfgde);
    }

}
