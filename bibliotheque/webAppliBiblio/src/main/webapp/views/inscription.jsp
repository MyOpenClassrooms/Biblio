<%@ include file="taglib.jsp"%>
<body>

	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="panel panel-default centre">
			<div class="panel-heading">
				<h4>
					<b>Inscrivez vous c'est gratuit!!!</b>
				</h4>
			</div>
			<div class="panel-body">
			<s:actionerror/>
			 <s:actionmessage/>
				<s:form action="ajouterUtilisateur" method="post" theme="bootstrap" cssClass="form-horizontal">
					<s:textfield label="Nom" name="utilisateur.nom" requiredLabel="true" placeholder="Entrez votre nom" />
					<s:textfield label="Prénom" name="utilisateur.prenom"
						placeholder="Entrez votre prénom" />
					<s:textfield label="Adresse" name="utilisateur.adress"
						placeholder="Entrez votre adresse" />
					<s:textfield label="Email" name="utilisateur.email"
						placeholder="Entrez votre email" />
					<s:password label="Mot de passe" name="utilisateur.password"
						placeholder="Entrez votre mot de passe" />
					<s:file label="Photo" name="utilisateur.photo" />
					<s:submit cssClass="btn btn-primary" value="Valider" />
					<s:reset cssClass="btn btn-danger" value="Annuler" />
				</s:form>
				

			</div>
		</div>
	
	</div>
</body>
</html>