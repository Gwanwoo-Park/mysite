package com.douzone.mysite.action.board;

import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch(actionName) {
			case "list" : return new BoardFormAction();
			case "writeform" : return new BoardWriteFormAction();
			case "writeInsert" : return new BoardWriteInsertAction();
			case "viewform" : return new BoardViewFormAction();
			case "delete" : return new BoardDeleteAction();
			case "modifyform" : return new BoardModifyFormAction();
			case "modifyAdd" : return new BoardModifyAdd();
			case "replyAdd" : return new BoardReplyAdd();
			default : return new BoardFormAction();
		}
	}
}