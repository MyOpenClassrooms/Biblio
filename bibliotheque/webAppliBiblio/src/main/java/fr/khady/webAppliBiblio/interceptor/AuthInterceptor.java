package fr.khady.webAppliBiblio.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 7995153741671857846L;

    @Override
    public String intercept(ActionInvocation pInvocation) throws Exception {
        String vResult;
        if (pInvocation.getInvocationContext().getSession().get("utilisateur") != null) {
        	System.out.println("usersession  " +pInvocation.getInvocationContext().getSession().get("utilisateur"));
            vResult = pInvocation.invoke();
        } else {
            vResult = "error-forbidden";
        }
        return vResult;
    }
}
