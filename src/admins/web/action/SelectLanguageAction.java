package admins.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;

public class SelectLanguageAction extends  DispatchAction{

	
	 
	
	
	public ActionForward SelectLanguage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
		
		
	ActionRedirect redirect = new ActionRedirect(mapping.findForward("login_success"));
		
		return redirect;
	}
}
