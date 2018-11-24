
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
				<li class="dropdown">
<!-- 				<a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a> -->
				<a  role="button" " href="#">Réservation <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li class=""><s:url namespace="/" action="ouvrage" var="lienOu"></s:url>
					<s:a href="%{lienOu}">Faire une réservation</s:a></li>
						<li class=""><s:url namespace="/" action="listerResa" var="lienListResa"></s:url>
						<s:a href="%{lienListResa}">Liste réservations</s:a></li>
					</ul></li>
					
					
<!-- 				<li class="dropdown"><a class="dropdown-toggle" -->
<!-- 					data-toggle="dropdown" href="">Prêt <span class="caret"></span></a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li class=""><a href="">Faire un prêt </a></li> -->
<!-- 						<li class=""><a href="">Prolonger prêt</a></li> -->
<!-- 						<li class=""><a href="">Mes prêts</a></li> -->
<!-- 					</ul></li> -->
					<li><s:url namespace="/" action="pret" var="lienPret"></s:url>
					<s:a href="%{lienPret}">Pret</s:a></li>
					</ul>
				
				<s:if test="#session.utilisateur">
				
					<ul class="nav navbar-nav navbar-right">
				
					<li><s:url namespace="/" action="logout" var="lienlogout"></s:url>
							 Bonjour <s:property value="#session.utilisateur.prenom" />
							<s:property value="#session.utilisateur.nom" />
						<s:a href="%{lienlogout}">
							<span class="glyphicon glyphicon-log-out"></span>
						Déconnexion
				</s:a></li>
				</ul>
				
			
				</s:if>
			
			<s:else>
				<ul class="nav navbar-nav navbar-right">
					<li><s:url namespace="/" action="connexion" var="lienCon"></s:url>
						<s:a href="%{lienCon}">
							<span class="glyphicon glyphicon-log-in"></span>
						Connexion
				</s:a></li>
				</ul>
							<ul class="nav navbar-nav navbar-right">
					<li><s:url namespace="/" action="inscription" var="lien1"></s:url>
						<s:a href="%{lien1}">
							<span class="glyphicon glyphicon-user"></span>
						S'inscrire
				</s:a></li>
				</ul>
			</s:else>
		</div>
	</div>
</nav>
