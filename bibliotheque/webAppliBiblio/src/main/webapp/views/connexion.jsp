<%@ include file="taglib.jsp"%>
<body>

	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="panel panel-default centre">
			<div class="panel-heading">
				<h4>
					<b>Connectez-vous!!!</b>
				</h4>
			</div>
			<div class="panel-body">
			<s:actionerror />
			<s:actionmessage/>
				<s:form action="login" method="post" theme="bootstrap" cssClass="form-horizontal">
					
					<s:textfield label="Email" name="utilisateur.email"
						placeholder="Entrez votre email" />
<%-- 							<s:fielderror/> --%>
					<s:password label="Mot de passe" name="utilisateur.password"
						placeholder="Entrez votre mot de passe" />
					
					<s:submit cssClass="btn btn-primary" value="Connexion" />
					<s:reset cssClass="btn btn-danger" value="Annuler" />
				</s:form>

			</div>
		</div>
	
	</div>
</body>
</html>