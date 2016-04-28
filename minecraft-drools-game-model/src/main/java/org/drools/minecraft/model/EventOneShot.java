package org.drools.minecraft.model;

import java.util.HashMap;

public class EventOneShot extends Event
{
	public static HashMap<String, Boolean> events;
	public EventOneShot(String id)
	{
		super(id);
		if(events == null)
		{
			events = new HashMap<String, Boolean>();
		}
		if(!events.containsKey(id))
		{
			events.put(id, false);
		}
	}
	
	@Override
	public boolean getHasFired()
	{
		return events.get(id);
	}
	
	@Override
	public void setHasFired(boolean hasFired) {
		this.hasFired = hasFired;
		events.put(id, hasFired);
	}
}
