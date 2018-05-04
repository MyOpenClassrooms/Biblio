
<nav class="navbar navbar-default ">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<s:url namespace="/" action="index" var="lien2"></s:url>
			<s:a class="navbar-brand" href="%{lien2}">
				<i class="glyphicon glyphicon-home"></i>
			</s:a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
			<li><s:url namespace="/" action="ouvrage" var="lienOu"></s:url>
					<s:a href="%{lienOu}">Ouvrage</s:a></li>
<!-- 				<li class="dropdown"><a class="dropdown-toggle" -->
<!-- 					data-toggle="dropdown" href="">Exemplaire <span class="caret"></span></a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li class=""><a href="">Ajouter Exmeplaire</a></li> -->
<!-- 						<li class=""><a href="">Liste des exemplaires</a></li> -->
<!-- 					</ul></li> -->
<!-- 				<li class="dropdown"><a class="dropdown-toggle" -->
<!-- 					data-toggle="dropdown" href="">Prêt <span class="caret"></span></a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li class=""><a href="">Faire un prêt </a></li> -->
<!-- 						<li class=""><a href="">Prolonger prêt</a></li> -->
<!-- 						<li class=""><a href="">Mes prêts</a></li> -->
<!-- 					</ul></li> -->
					<li><s:url namespace="/" action="pret" var="lienPret"></s:url>
					<s:a href="%{lienPret}">Pret</s:a></li>
					
				<li><s:url namespace="/" action="inscription" var="lien1"></s:url>
					<s:a href="%{lien1}">S'inscrire</s:a></li>
				<s:if test="#session.utilisateur">
					<li><s:url namespace="/" action="logout" var="lienlogout"></s:url>
						<s:a href="%{lienlogout}">Déconnexion &nbsp; &nbsp; 
           Bonjour <s:property value="#session.utilisateur.prenom" />
							<s:property value="#session.utilisateur.nom" />

						</s:a></li>
				</s:if>
			</ul>
			<s:else>
				<ul class="nav navbar-nav navbar-right">
					<li><s:url namespace="/" action="connexion" var="lienCon"></s:url>
						<s:a href="%{lienCon}">
							<span class="glyphicon glyphicon-log-in"></span>
						Connexion
				</s:a></li>
				</ul>
			</s:else>
		</div>
	</div>
</nav>
