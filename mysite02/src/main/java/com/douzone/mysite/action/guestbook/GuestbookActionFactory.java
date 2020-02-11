package com.douzone.mysite.action.guestbook;

import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch(actionName) {
			case "list" : return new GuestbookFormAction();
			case "deleteform" : return new GuestbookDeleteAction();
			case "deleteAdd" : return new GuestbookDeleteAdd();
			case "insert" : return new GuestbookInsertAdd();
			default : return new GuestbookFormAction();
		}
	}
}