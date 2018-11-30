package fr.khady.webAppliBiblio.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import fr.khady.wsBiblioClient.Utilisateur;

public class AuthInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 7995153741671857846L;

	@Override
	public String intercept(ActionInvocation pInvocation) throws Exception {

		Map<String, Object> session = pInvocation.getInvocationContext().getSession();
		if (session != null && session.containsKey("utilisateur")) {
			Utilisateur user = (Utilisateur) session.get("utilisateur");
			if(user == null) {
				return Action.LOGIN;
			}else {
				Action action = (Action) pInvocation.getAction();
				return pInvocation.invoke();
			}
		}else {
			return Action.LOGIN;
		}
		
	}
}
